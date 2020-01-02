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
	private UpmUserService upmUserService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmUser")
	public LayUiTableResultResponse<UpmUser> page(@RequestParam UpmUser upmUser ) {
		    Map<String,Object> map =new HashMap<>();
			Map map2 = new MapAndObject(map,upmUser);
			Query query= new Query(map2);
			return upmUserService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmUser")
		public RestApiResult2 create(@ModelAttribute UpmUser upmUser,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmUser.setCreateUserId(createBy);
					upmUser.setCreateUserName(getUserName(request));
					upmUser.setCreateTime(new Date());
					upmUser.setUserPass(Md5Util.md5(upmUser.getUserPass()));
					upmUserService.insertSelective(upmUser);
					
				}catch(Exception e) {
					logger.error("[系统用户表]-->新增失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmUser/{id}")
		public RestApiResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmUser upmUser,HttpServletRequest request)  {
			try {
					UpmUser user = upmUserService.selectByPrimaryKey(id);
		            if (!user.getUserPass().equals(upmUser.getUserPass())){
		            	upmUser.setUserPass(Md5Util.md5(upmUser.getUserPass()));
		            }
	            
					Long createBy = getLoginId(request);
					upmUser.setUpdateUserId(createBy);
					upmUser.setUpdateUserName(getUserName(request));
					upmUser.setUpdateTime(new Date());
					upmUserService.updateByPrimaryKeySelective(upmUser);
					
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
		UpmUser upmUser =upmUserService.selectByPrimaryKey(id);
		if(upmUser== null) {
			upmUser = new UpmUser();
		}
		return upmUser;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/UpmUser/{id}")
	public RestApiResult2 delete(@PathVariable("id") java.lang.Long id ) {
		upmUserService.deleteByPrimaryKey(id);
		return new RestApiResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmUser/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmUser upmUser =upmUserService.selectByPrimaryKey(id);
		if(upmUser== null) {
			upmUser = new UpmUser();
		}
		
		retMap.put("upmUser", upmUser);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmUser/queryList")
	public RestApiResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmUser> list = upmUserService.selectByExample(query);
			return new RestApiResult2().respData(list);
	}
}

