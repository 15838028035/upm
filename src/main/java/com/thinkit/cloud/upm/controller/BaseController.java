package com.thinkit.cloud.upm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.thinkit.cloud.upm.config.JwtUtil;

/**
 * 
 * 基类
 *
 */
public class BaseController {

	@Autowired
	private JwtUtil jwtUtil;
	
	public Long getLoginId(HttpServletRequest request) {
		Map<String, Object> map = jwtUtil.validateTokenAndGetClaims(request);
		return Long.valueOf((String) map.get("USER_ID"));
	}

	public String getUserName(HttpServletRequest request) {
		Map<String, Object> map = jwtUtil.validateTokenAndGetClaims(request);
		return (String) map.get("USER_NAME");
	}

	/**
	 * 获取文件拓展名
	 * 
	 */
	public static String getExt(String path) {

		if (path == null || path.equals("") || !path.contains(".")) {
			return null;
		} else {
			return path.substring(path.lastIndexOf('.') + 1, path.length());
		}

	}

}
