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

import com.thinkit.cloud.upm.bean.UpmPermission;
import com.thinkit.cloud.upm.bean.UpmRoleAndPermissionRel;
import com.thinkit.cloud.upm.service.UpmPermissionService;
import com.thinkit.cloud.upm.service.UpmRoleAndPermissionRelService;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *权限信息表管理
 */
@Api(value = "权限信息表服务", tags = "权限信息表服务接口")
@RestController()
public class UpmPermissionController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UpmPermissionService UpmPermissionService;
	
	@Autowired
	private UpmRoleAndPermissionRelService UpmRoleAndPermissionRelService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmPermission")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  UpmPermissionService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmPermission")
		public RestApiResult2 create(@ModelAttribute UpmPermission UpmPermission,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					UpmPermission.setCreateUserId(createBy);
					UpmPermission.setCreateUserName(getUserName(request));
					UpmPermission.setCreateTime(new Date());
					UpmPermissionService.insertSelective(UpmPermission);
					
				}catch(Exception e) {
					logger.error("[权限信息表]-->新增失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmPermission/{id}")
		public RestApiResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmPermission UpmPermission,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					UpmPermission.setUpdateUserId(createBy);
					UpmPermission.setUpdateUserName(getUserName(request));
					UpmPermission.setUpdateTime(new Date());
					UpmPermissionService.updateByPrimaryKeySelective(UpmPermission);
					
				}catch(Exception e) {
					logger.error("[权限信息表]-->更新失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmPermission/{id}")
	public UpmPermission show(@PathVariable("id") java.lang.Long id )  {
		UpmPermission UpmPermission =UpmPermissionService.selectByPrimaryKey(id);
		if(UpmPermission== null) {
			UpmPermission = new UpmPermission();
		}
		return UpmPermission;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/UpmPermission/{id}")
	public RestApiResult2 delete(@PathVariable("id") java.lang.Long id ) {
		
		Query query= new Query().putFilter("permissionId", id);
		List<UpmRoleAndPermissionRel> list = UpmRoleAndPermissionRelService.selectByExample(query);
		
		if(!list.isEmpty()) {
			return new RestApiResult2().respCode(0).respMsg("对不起，存在有关联的{} 个角色,请先解除角色权限关系", String.valueOf(list.size()));
		}
		
		 UpmPermissionService.deleteByPrimaryKey(id);
		return new RestApiResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmPermission/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		HashMap<String,Object> retMap =new HashMap<>();
		UpmPermission UpmPermission =UpmPermissionService.selectByPrimaryKey(id);
		if(UpmPermission== null) {
			UpmPermission = new UpmPermission();
		}
		
		retMap.put("UpmPermission", UpmPermission);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmPermission/queryList")
	public RestApiResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmPermission> list = UpmPermissionService.selectByExample(query);
			return new RestApiResult2().respData(list);
	}
}

