package com.thinkit.cloud.upm.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.thinkit.cloud.upm.filter.LogInterceptor;

/**
 * WebMvc配置
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }
}
