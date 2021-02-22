package com.thinkit.cloud.upm.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;

@ControllerAdvice("com.thinkit.cloud.upm.controller")
public class NovaResponseBodyAdvice implements ResponseBodyAdvice {
	private final static String LOG_TRACE_ID = "logTraceId";
	
 @Override
 public boolean supports(MethodParameter returnType, Class converterType) {
     return true;
 }

 @Override
 public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
         Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
  
	 HttpServletRequest httpServletRequest = (HttpServletRequest)request;
	 String logTraceId = httpServletRequest.getHeader("traceId");
	 
	 if(logTraceId == null) {
		 String uuid = UUID.randomUUID().toString();
	     MDC.put(LOG_TRACE_ID, uuid);
	 }
     
	  if( body instanceof RestApiResult2) {
		  String traceId = MDC.get(LOG_TRACE_ID);
		  RestApiResult2 restApiResult2 = ((RestApiResult2)body);
		  restApiResult2.setTraceId(traceId);
	      return restApiResult2;
	  }
  
  MDC.remove(LOG_TRACE_ID);
  
  return body;
 }

}