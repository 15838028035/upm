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
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
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
	private UpmRoleAndPermissionRelService upmRoleAndPermissionRelService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRoleAndPermissionRel")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmRoleAndPermissionRelService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmRoleAndPermissionRel")
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
		@PutMapping(value="/api/UpmRoleAndPermissionRel/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmRoleAndPermissionRel upmRoleAndPermissionRel,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmRoleAndPermissionRel.setUpdateUserId(createBy);
					upmRoleAndPermissionRel.setUpdateUserName(getUserName(request));
					upmRoleAndPermissionRel.setUpdateTime(new Date());
					upmRoleAndPermissionRelService.updateByPrimaryKeySelective(upmRoleAndPermissionRel);
					
				}catch(Exception e) {
					logger.error("[角色权限关联表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmRoleAndPermissionRel/{id}")
	public UpmRoleAndPermissionRel show(@PathVariable("id") java.lang.Long id )  {
		UpmRoleAndPermissionRel upmRoleAndPermissionRel =upmRoleAndPermissionRelService.selectByPrimaryKey(id);
		if(upmRoleAndPermissionRel== null) {
			upmRoleAndPermissionRel = new UpmRoleAndPermissionRel();
		}
		return upmRoleAndPermissionRel;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@DeleteMapping(value="/api/UpmRoleAndPermissionRel/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		 upmRoleAndPermissionRelService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmRoleAndPermissionRel/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmRoleAndPermissionRel upmRoleAndPermissionRel =upmRoleAndPermissionRelService.selectByPrimaryKey(id);
		if(upmRoleAndPermissionRel== null) {
			upmRoleAndPermissionRel = new UpmRoleAndPermissionRel();
		}
		
		retMap.put("upmRoleAndPermissionRel", upmRoleAndPermissionRel);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRoleAndPermissionRel/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmRoleAndPermissionRel> list = upmRoleAndPermissionRelService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}

