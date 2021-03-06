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

import com.thinkit.cloud.upm.bean.UpmRole;
import com.thinkit.cloud.upm.service.UpmRoleService;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *角色信息表管理
 */
@Api(value = "角色信息表服务", tags = "角色信息表服务接口")
@RestController()
public class UpmRoleController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UpmRoleService UpmRoleService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRole")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  UpmRoleService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmRole")
		public RestApiResult2 create(@ModelAttribute UpmRole UpmRole,String operate ,HttpServletRequest request)  {
			
			try {
				
					Long createBy = getLoginId(request);
					
					if("edit".equals(operate)) {
						UpmRole.setUpdateUserId(createBy);
						UpmRole.setUpdateUserName(getUserName(request));
						UpmRole.setUpdateTime(new Date());
						UpmRoleService.updateByPrimaryKeySelective(UpmRole);
					}else {
						UpmRole.setCreateUserId(createBy);
						UpmRole.setCreateUserName(getUserName(request));
						UpmRole.setCreateTime(new Date());
						UpmRoleService.insertSelective(UpmRole);
					}
					
					UpmRoleService.addPermissionToRole(UpmRole.getPermissions(), UpmRole.getAppId(), UpmRole.getId());
					
				}catch(Exception e) {
					logger.error("[角色信息表]-->新增失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmRole/{id}")
		public RestApiResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmRole UpmRole,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					UpmRole.setUpdateUserId(createBy);
					UpmRole.setUpdateUserName(getUserName(request));
					UpmRole.setUpdateTime(new Date());
					UpmRoleService.updateByPrimaryKeySelective(UpmRole);
					
				}catch(Exception e) {
					logger.error("[角色信息表]-->更新失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmRole/{id}")
	public UpmRole show(@PathVariable("id") java.lang.Long id )  {
		UpmRole UpmRole =UpmRoleService.selectByPrimaryKey(id);
		if(UpmRole== null) {
			UpmRole = new UpmRole();
		}
		return UpmRole;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@DeleteMapping(value="/api/UpmRole/{id}")
	public RestApiResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmRoleService.deleteByPrimaryKey(id);
		return new RestApiResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmRole/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmRole UpmRole =UpmRoleService.selectByPrimaryKey(id);
		if(UpmRole== null) {
			UpmRole = new UpmRole();
		}
		
		retMap.put("UpmRole", UpmRole);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRole/queryList")
	public RestApiResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmRole> list = UpmRoleService.selectByExample(query);
			return new RestApiResult2().respData(list);
	}
	
	@ApiOperation(value = "权限树")
	@GetMapping(value = "/api/UpmRole/getPermissionTree")
	  public String getPermissionTree(String strRoleId, String appId,HttpServletRequest request)  {
	    // 根据当前登录人员获取权限菜单树
	    if (StringUtil.isBlank(strRoleId)) {
	      strRoleId = "0";
	    }
	    String jsonData = UpmRoleService.getPermissionTreeDataJson(Integer.valueOf(strRoleId), appId,
	        this.getLoginId(request));

	    if (StringUtil.isBlank(jsonData)) {
	      jsonData = "";
	    }
	    return jsonData;
	}
	
}

