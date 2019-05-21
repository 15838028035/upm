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

import com.thinkit.cloud.upm.bean.UpmPermission;
import com.thinkit.cloud.upm.service.UpmPermissionService;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.DateUtil;

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
	private UpmPermissionService upmPermissionService;
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmPermission", method = RequestMethod.GET)
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmPermissionService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@RequestMapping(value = "/api/UpmPermission",method=RequestMethod.POST)
		public RestAPIResult2 create(@ModelAttribute UpmPermission upmPermission,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmPermission.setCreateUserId(createBy);
					upmPermission.setCreateUserName(getUserName(request));
					upmPermission.setCreateTime(new Date());
					upmPermissionService.insertSelective(upmPermission);
					
				}catch(Exception e) {
					logger.error("[权限信息表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@RequestMapping(value="/api/UpmPermission/{id}",method=RequestMethod.PUT)
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmPermission upmPermission,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmPermission.setUpdateUserId(createBy);
					upmPermission.setUpdateByUname(getUserName(request));
					upmPermission.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
					upmPermissionService.updateByPrimaryKeySelective(upmPermission);
					
				}catch(Exception e) {
					logger.error("[权限信息表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@RequestMapping(value="/api/UpmPermission/{id}", method = RequestMethod.GET)
	public UpmPermission show(@PathVariable("id") java.lang.Long id )  {
		UpmPermission upmPermission =upmPermissionService.selectByPrimaryKey(id);
		if(upmPermission== null) {
			upmPermission = new UpmPermission();
		}
		return upmPermission;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@RequestMapping(value="/api/UpmPermission/{id}",method=RequestMethod.DELETE)
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmPermission upmPermission = upmPermissionService.selectByPrimaryKey(id);
		 upmPermission.setEnableFlag("0");//失效
		 upmPermissionService.updateByPrimaryKey(upmPermission);
			
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@RequestMapping(value="/api/UpmPermission/showInfo/{id}", method = RequestMethod.GET)
	public  HashMap<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		HashMap<String,Object> retMap =new HashMap<>();
		UpmPermission upmPermission =upmPermissionService.selectByPrimaryKey(id);
		if(upmPermission== null) {
			upmPermission = new UpmPermission();
		}
		
		retMap.put("upmPermission", upmPermission);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmPermission/queryList", method = RequestMethod.GET)
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmPermission> list = upmPermissionService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}

