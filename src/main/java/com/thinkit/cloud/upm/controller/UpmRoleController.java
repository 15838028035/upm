package com.thinkit.cloud.upm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.thinkit.cloud.upm.bean.UpmRole;
import com.thinkit.cloud.upm.service.UpmRoleService;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.DateUtil;
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
	@RequestMapping(value = "/api/UpmRole", method = RequestMethod.GET)
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmRoleService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@RequestMapping(value = "/api/UpmRole",method=RequestMethod.POST)
		public RestAPIResult2 create(@ModelAttribute UpmRole upmRole,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmRole.setCreateUserId(createBy);
					upmRole.setCreateUserName(getUserName(request));
					upmRole.setCreateTime(new Date());
					upmRoleService.insertSelective(upmRole);
					
				}catch(Exception e) {
					logger.error("[角色信息表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@RequestMapping(value="/api/UpmRole/{id}",method=RequestMethod.PUT)
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmRole upmRole,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmRole.setUpdateUserId(createBy);
					upmRole.setUpdateByUname(getUserName(request));
					upmRole.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
					upmRoleService.updateByPrimaryKeySelective(upmRole);
					
				}catch(Exception e) {
					logger.error("[角色信息表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@RequestMapping(value="/api/UpmRole/{id}", method = RequestMethod.GET)
	public UpmRole show(@PathVariable("id") java.lang.Long id )  {
		UpmRole upmRole =upmRoleService.selectByPrimaryKey(id);
		if(upmRole== null) {
			upmRole = new UpmRole();
		}
		return upmRole;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@RequestMapping(value="/api/UpmRole/{id}",method=RequestMethod.DELETE)
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmRole upmRole = upmRoleService.selectByPrimaryKey(id);
		 upmRole.setEnableFlag("0");//失效
		 upmRoleService.updateByPrimaryKey(upmRole);
			
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@RequestMapping(value="/api/UpmRole/showInfo/{id}", method = RequestMethod.GET)
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap();
		UpmRole upmRole =upmRoleService.selectByPrimaryKey(id);
		if(upmRole== null) {
			upmRole = new UpmRole();
		}
		
		retMap.put("upmRole", upmRole);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmRole/queryList", method = RequestMethod.GET)
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmRole> list = upmRoleService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
	
	@ApiOperation(value = "权限树")
	@RequestMapping(value = "/api/UpmRole/getPermissionTree", method = RequestMethod.GET)
	  public String getPermissionTree(String strRoleId, String appId,HttpServletRequest request) throws Exception {
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

