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

import com.thinkit.cloud.upm.bean.UpmRoleAndPermissionRel;
import com.thinkit.cloud.upm.service.UpmRoleAndPermissionRelService;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

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
	private UpmRoleAndPermissionRelService UpmRoleAndPermissionRelService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRoleAndPermissionRel")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  UpmRoleAndPermissionRelService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmRoleAndPermissionRel")
		public RestApiResult2 create(@ModelAttribute UpmRoleAndPermissionRel UpmRoleAndPermissionRel,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					UpmRoleAndPermissionRel.setCreateUserId(createBy);
					UpmRoleAndPermissionRel.setCreateUserName(getUserName(request));
					UpmRoleAndPermissionRel.setCreateTime(new Date());
					UpmRoleAndPermissionRelService.insertSelective(UpmRoleAndPermissionRel);
					
				}catch(Exception e) {
					logger.error("[角色权限关联表]-->新增失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmRoleAndPermissionRel/{id}")
		public RestApiResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmRoleAndPermissionRel UpmRoleAndPermissionRel,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					UpmRoleAndPermissionRel.setUpdateUserId(createBy);
					UpmRoleAndPermissionRel.setUpdateUserName(getUserName(request));
					UpmRoleAndPermissionRel.setUpdateTime(new Date());
					UpmRoleAndPermissionRelService.updateByPrimaryKeySelective(UpmRoleAndPermissionRel);
					
				}catch(Exception e) {
					logger.error("[角色权限关联表]-->更新失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmRoleAndPermissionRel/{id}")
	public UpmRoleAndPermissionRel show(@PathVariable("id") java.lang.Long id )  {
		UpmRoleAndPermissionRel UpmRoleAndPermissionRel =UpmRoleAndPermissionRelService.selectByPrimaryKey(id);
		if(UpmRoleAndPermissionRel== null) {
			UpmRoleAndPermissionRel = new UpmRoleAndPermissionRel();
		}
		return UpmRoleAndPermissionRel;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@DeleteMapping(value="/api/UpmRoleAndPermissionRel/{id}")
	public RestApiResult2 delete(@PathVariable("id") java.lang.Long id ) {
		 UpmRoleAndPermissionRelService.deleteByPrimaryKey(id);
		return new RestApiResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmRoleAndPermissionRel/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmRoleAndPermissionRel UpmRoleAndPermissionRel =UpmRoleAndPermissionRelService.selectByPrimaryKey(id);
		if(UpmRoleAndPermissionRel== null) {
			UpmRoleAndPermissionRel = new UpmRoleAndPermissionRel();
		}
		
		retMap.put("UpmRoleAndPermissionRel", UpmRoleAndPermissionRel);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRoleAndPermissionRel/queryList")
	public RestApiResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmRoleAndPermissionRel> list = UpmRoleAndPermissionRelService.selectByExample(query);
			return new RestApiResult2().respData(list);
	}
}

