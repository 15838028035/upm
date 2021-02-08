package com.thinkit.cloud.upm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinkit.cloud.upm.bean.UpmUser;
import com.thinkit.cloud.upm.service.UpmUserService;
import com.thinkit.cloud.upm.util.Md5Util;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.MapAndObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *系统用户表管理
 */
@Api(value = "系统用户表服务", tags = "系统用户表服务接口")
@RestController()
public class UpmUserController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UpmUserService UpmUserService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmUser")
	public LayUiTableResultResponse<UpmUser> page(@RequestParam UpmUser UpmUser ) {
		    Map<String,Object> map =new HashMap<>();
			Map map2 = new MapAndObject(map,UpmUser);
			Query query= new Query(map2);
			return UpmUserService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmUser")
		public RestApiResult2 create(@ModelAttribute UpmUser UpmUser,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					UpmUser.setCreateUserId(createBy);
					UpmUser.setCreateUserName(getUserName(request));
					UpmUser.setCreateTime(new Date());
					UpmUser.setUserPass(Md5Util.md5(UpmUser.getUserPass()));
					UpmUserService.insertSelective(UpmUser);
					
				}catch(Exception e) {
					logger.error("[系统用户表]-->新增失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmUser/{id}")
		public RestApiResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmUser UpmUser,HttpServletRequest request)  {
			try {
					UpmUser user = UpmUserService.selectByPrimaryKey(id);
		            if (!user.getUserPass().equals(UpmUser.getUserPass())){
		            	UpmUser.setUserPass(Md5Util.md5(UpmUser.getUserPass()));
		            }
	            
					Long createBy = getLoginId(request);
					UpmUser.setUpdateUserId(createBy);
					UpmUser.setUpdateUserName(getUserName(request));
					UpmUser.setUpdateTime(new Date());
					UpmUserService.updateByPrimaryKeySelective(UpmUser);
					
				}catch(Exception e) {
					logger.error("[系统用户表]-->更新失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmUser/{id}")
	public UpmUser show(@PathVariable("id") java.lang.Long id )  {
		UpmUser UpmUser =UpmUserService.selectByPrimaryKey(id);
		if(UpmUser== null) {
			UpmUser = new UpmUser();
		}
		return UpmUser;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/UpmUser/{id}")
	public RestApiResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmUserService.deleteByPrimaryKey(id);
		return new RestApiResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmUser/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmUser UpmUser =UpmUserService.selectByPrimaryKey(id);
		if(UpmUser== null) {
			UpmUser = new UpmUser();
		}
		
		retMap.put("UpmUser", UpmUser);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmUser/queryList")
	public RestApiResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmUser> list = UpmUserService.selectByExample(query);
			return new RestApiResult2().respData(list);
	}
}

