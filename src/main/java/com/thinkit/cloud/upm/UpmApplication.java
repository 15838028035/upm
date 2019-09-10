package com.thinkit.cloud.upm;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkit.cloud.upm.filter.JwtAuthenticationFilter;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;

import io.swagger.annotations.ApiOperation;

/**
 *启动类
 */
@SpringBootApplication
@RestController
@EnableCaching
public class UpmApplication {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 上传文件最大值
	 */
	@Value("${maxFileSize}")
	private String maxFileSize;
	
	/**
	 * 最大请求数
	 */
	@Value("${maxRequestSize}")
	private String maxRequestSize;
	
	@Bean
	public JwtAuthenticationFilter filter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public FilterRegistrationBean<Filter> jwtFilter() {
		final FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(filter());
		return registrationBean;
	}
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize(maxFileSize);
        config.setMaxRequestSize(maxRequestSize);
        return config.createMultipartConfig();
    }
	
	@ApiOperation(value = "添加token到黑名单中")
	@PostMapping(value = "/api/addTokenToBlack/{token}")
	public RestApiResult2 addTokenToBlack(@PathVariable("token") java.lang.String token,HttpServletRequest request)  {
		
		try {
				
			JwtAuthenticationFilter.addToken(token);
			}catch(Exception e) {
				logger.error("添加token到黑名单中失败" ,e);
				return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
			}
			
			return new RestApiResult2();
	}
	
	 /**
	   * 用于接受 shutdown 事件
	   */
	  @Bean
	  public GracefulShutdown gracefulShutdown() {
	    return new GracefulShutdown();
	  }
	 
	  /**
	   * 配置tomcat
	   *
	   * @return
	   */
	  @Bean
	  public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
	    tomcat.addConnectorCustomizers(gracefulShutdown());
	    return tomcat;
	  }
	 
	  /**
	   * 优雅关闭 Spring Boot。容器必须是 tomcat
	   */
	  private class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
	    private final Logger log = LoggerFactory.getLogger(GracefulShutdown.class);
	    private volatile Connector connector;
	    private static final int WAIT_TIME = 10;
	 
	    @Override
	    public void customize(Connector connector) {
	      this.connector = connector;
	    }
	 
	    @Override
	    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
	      this.connector.pause();
	      Executor executor = this.connector.getProtocolHandler().getExecutor();
	      if (executor instanceof ThreadPoolExecutor) {
	        try {
	          ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
	          log.info("Tomcat 进程在 {} s 进行结束shutdown操作",WAIT_TIME);
	          threadPoolExecutor.shutdown();
	          if (!threadPoolExecutor.awaitTermination(WAIT_TIME, TimeUnit.SECONDS)) {
	            log.warn("Tomcat 进程在{}s内无法结束，尝试强制结束",WAIT_TIME);
	          }
	        } catch (InterruptedException ex) {
	          Thread.currentThread().interrupt();
	        }
	      }
	    }
	  }
	
    public static void main(String[] args) {
        SpringApplication.run(UpmApplication.class, args);
    }
}
