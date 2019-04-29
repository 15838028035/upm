package com.thinkit.cloud.upm.controller;

import com.thinkit.cloud.upm.bean.UpmRoleAndPermissionRel;
import com.thinkit.cloud.upm.service.UpmRoleAndPermissionRelService;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *角色权限关联表管理
 */
@Api(value = "角色权限关联表服务", tags = "角色权限关联表服务接口")
@RestController()
public class UpmRoleAndPermissionRelController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UpmRoleAndPermissionRelService upmRoleAndPermissionRelService;
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmRoleAndPermissionRel", method = RequestMethod.GET)
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmRoleAndPermissionRelService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@RequestMapping(value = "/api/UpmRoleAndPermissionRel",method=RequestMethod.POST)
		public RestAPIResult2 create(@ModelAttribute UpmRoleAndPermissionRel upmRoleAndPermissionRel,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmRoleAndPermissionRel.setCreateUserId(createBy);
					upmRoleAndPermissionRel.setCreateUserName(getUserName(request));
					upmRoleAndPermissionRel.setCreateTime(new Date());
					upmRoleAndPermissionRelService.insertSelective(upmRoleAndPermissionRel);
					
				}catch(Exception e) {
					logger.error("[角色权限关联表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@RequestMapping(value="/api/UpmRoleAndPermissionRel/{id}",method=RequestMethod.PUT)
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmRoleAndPermissionRel upmRoleAndPermissionRel,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmRoleAndPermissionRel.setUpdateUserId(createBy);
					upmRoleAndPermissionRel.setUpdateByUname(getUserName(request));
					upmRoleAndPermissionRel.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
					upmRoleAndPermissionRelService.updateByPrimaryKeySelective(upmRoleAndPermissionRel);
					
				}catch(Exception e) {
					logger.error("[角色权限关联表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@RequestMapping(value="/api/UpmRoleAndPermissionRel/{id}", method = RequestMethod.GET)
	public UpmRoleAndPermissionRel show(@PathVariable("id") java.lang.Long id )  {
		UpmRoleAndPermissionRel upmRoleAndPermissionRel =upmRoleAndPermissionRelService.selectByPrimaryKey(id);
		if(upmRoleAndPermissionRel== null) {
			upmRoleAndPermissionRel = new UpmRoleAndPermissionRel();
		}
		return upmRoleAndPermissionRel;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@RequestMapping(value="/api/UpmRoleAndPermissionRel/{id}",method=RequestMethod.DELETE)
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmRoleAndPermissionRel upmRoleAndPermissionRel = upmRoleAndPermissionRelService.selectByPrimaryKey(id);
		 upmRoleAndPermissionRel.setEnableFlag("0");//失效
		 upmRoleAndPermissionRelService.updateByPrimaryKey(upmRoleAndPermissionRel);
			
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@RequestMapping(value="/api/UpmRoleAndPermissionRel/showInfo/{id}", method = RequestMethod.GET)
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap();
		UpmRoleAndPermissionRel upmRoleAndPermissionRel =upmRoleAndPermissionRelService.selectByPrimaryKey(id);
		if(upmRoleAndPermissionRel== null) {
			upmRoleAndPermissionRel = new UpmRoleAndPermissionRel();
		}
		
		retMap.put("upmRoleAndPermissionRel", upmRoleAndPermissionRel);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmRoleAndPermissionRel/queryList", method = RequestMethod.GET)
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmRoleAndPermissionRel> list = upmRoleAndPermissionRelService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}

