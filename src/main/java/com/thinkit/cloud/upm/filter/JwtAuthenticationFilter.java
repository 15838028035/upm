package com.thinkit.cloud.upm.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkit.cloud.upm.config.JwtUtil;
import com.zhongkexinli.micro.serv.common.util.StringUtil;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
	private JwtUtil jwtUtil;
    
    /**
	 * 开启过滤器
	 */
	@Value("${isOpenFilter:true}")
	private Boolean isOpenFilter;
    
    /**
     * token黑名单
     */
    private  static final Map<String,String> TOKEN_BLACK_MAP = new HashMap<>();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if(isProtectedUrl(request)) {
        	   String token = "";
        	   
        	   Cookie[] cookies =request.getCookies();
               if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equalsIgnoreCase("TOKEN")){
                        	token = cookie.getValue();
                        }
                    }
               }
               
               if (StringUtil.isBlank(token)) {
                   token = request.getHeader("TOKEN");
               }
               
               if (StringUtil.isBlank(token)) {
                   token = request.getParameter("TOKEN");
               }
                  
               if(TOKEN_BLACK_MAP.get(token)!=null) {
            	   response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "当前token在黑名单中，无法进行请求");
                   return;
               }
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
               jwtUtil.validateToken(token);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
      
      //sql,xss过滤
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        logger.info("CrosXssFilter.......orignal url:{},ParameterMap:{}",request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper=new XssHttpServletRequestWrapper(
                httpServletRequest);
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(xssHttpServletRequestWrapper, response);
        ObjectMapper mapper = new ObjectMapper();
        
        String jsonStr = "";
        try {
             jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(xssHttpServletRequestWrapper.getParameterMap());
        }catch( Exception  ex) {
        }
        
        logger.info("CrosXssFilter..........doFilter url:{},ParameterMap:{}",xssHttpServletRequestWrapper.getRequestURI(), jsonStr);
    }

    //我们只对地址 /api 开头的api检查jwt. 不然的话登录/login也需要jwt
    private boolean isProtectedUrl(HttpServletRequest request) {
    	if(isOpenFilter) {
	    	if(request.getServletPath().contains("/api/UpmUser/login")){//登陆不校验
	    		return false;
	    	}
	    	
	        return pathMatcher.match("/api/**", request.getServletPath());
	    	}
    	
    	return false;
    }

    /**
     * 添加token到黑名单中
     * @param token
     */
    public static void addToken(String token) {
    	TOKEN_BLACK_MAP.put(token, token);
    }
}