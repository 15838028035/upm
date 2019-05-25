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
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
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
	private UpmRoleService upmRoleService;
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRole")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmRoleService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmRole")
		public RestAPIResult2 create(@ModelAttribute UpmRole upmRole,String operate ,HttpServletRequest request)  {
			
			try {
				
					Long createBy = getLoginId(request);
					
					if("edit".equals(operate)) {
						upmRole.setUpdateUserId(createBy);
						upmRole.setUpdateUserName(getUserName(request));
						upmRole.setUpdateTime(new Date());
						upmRoleService.updateByPrimaryKeySelective(upmRole);
					}else {
						upmRole.setCreateUserId(createBy);
						upmRole.setCreateUserName(getUserName(request));
						upmRole.setCreateTime(new Date());
						upmRoleService.insertSelective(upmRole);
					}
					
					upmRoleService.addPermissionToRole(upmRole.getPermissions(), upmRole.getAppId(), upmRole.getId());
					
				}catch(Exception e) {
					logger.error("[角色信息表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmRole/{id}")
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmRole upmRole,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmRole.setUpdateUserId(createBy);
					upmRole.setUpdateUserName(getUserName(request));
					upmRole.setUpdateTime(new Date());
					upmRoleService.updateByPrimaryKeySelective(upmRole);
					
				}catch(Exception e) {
					logger.error("[角色信息表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmRole/{id}")
	public UpmRole show(@PathVariable("id") java.lang.Long id )  {
		UpmRole upmRole =upmRoleService.selectByPrimaryKey(id);
		if(upmRole== null) {
			upmRole = new UpmRole();
		}
		return upmRole;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@DeleteMapping(value="/api/UpmRole/{id}")
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		upmRoleService.deleteByPrimaryKey(id);
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmRole/showInfo/{id}")
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmRole upmRole =upmRoleService.selectByPrimaryKey(id);
		if(upmRole== null) {
			upmRole = new UpmRole();
		}
		
		retMap.put("upmRole", upmRole);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmRole/queryList")
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmRole> list = upmRoleService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
	
	@ApiOperation(value = "权限树")
	@GetMapping(value = "/api/UpmRole/getPermissionTree")
	  public String getPermissionTree(String strRoleId, String appId,HttpServletRequest request)  {
	    // 根据当前登录人员获取权限菜单树
	    if (StringUtil.isBlank(strRoleId)) {
	      strRoleId = "0";
	    }
	    String jsonData = upmRoleService.getPermissionTreeDataJson(Integer.valueOf(strRoleId), appId,
	        this.getLoginId(request));

	    if (StringUtil.isBlank(jsonData)) {
	      jsonData = "";
	    }
	    return jsonData;
	}
	
}

