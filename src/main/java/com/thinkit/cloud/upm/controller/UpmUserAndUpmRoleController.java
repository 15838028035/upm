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

import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;
import com.thinkit.cloud.upm.service.UpmUserAndUpmRoleService;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *用户角色关联表管理
 */
@Api(value = "用户角色关联表服务", tags = "用户角色关联表服务接口")
@RestController()
public class UpmUserAndUpmRoleController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UpmUserAndUpmRoleService upmUserAndUpmRoleService;
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmUserAndUpmRole", method = RequestMethod.GET)
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmUserAndUpmRoleService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@RequestMapping(value = "/api/UpmUserAndUpmRole",method=RequestMethod.POST)
		public RestAPIResult2 create(@ModelAttribute UpmUserAndUpmRole upmUserAndUpmRole,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmUserAndUpmRole.setCreateUserId(createBy);
					upmUserAndUpmRole.setCreateUserName(getUserName(request));
					upmUserAndUpmRole.setCreateTime(new Date());
					upmUserAndUpmRoleService.insertSelective(upmUserAndUpmRole);
					
				}catch(Exception e) {
					logger.error("[用户角色关联表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@RequestMapping(value="/api/UpmUserAndUpmRole/{id}",method=RequestMethod.PUT)
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmUserAndUpmRole upmUserAndUpmRole,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmUserAndUpmRole.setUpdateUserId(createBy);
					upmUserAndUpmRole.setUpdateByUname(getUserName(request));
					upmUserAndUpmRole.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
					upmUserAndUpmRoleService.updateByPrimaryKeySelective(upmUserAndUpmRole);
					
				}catch(Exception e) {
					logger.error("[用户角色关联表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@RequestMapping(value="/api/UpmUserAndUpmRole/{id}", method = RequestMethod.GET)
	public UpmUserAndUpmRole show(@PathVariable("id") java.lang.Long id )  {
		UpmUserAndUpmRole upmUserAndUpmRole =upmUserAndUpmRoleService.selectByPrimaryKey(id);
		if(upmUserAndUpmRole== null) {
			upmUserAndUpmRole = new UpmUserAndUpmRole();
		}
		return upmUserAndUpmRole;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@RequestMapping(value="/api/UpmUserAndUpmRole/{id}",method=RequestMethod.DELETE)
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmUserAndUpmRole upmUserAndUpmRole = upmUserAndUpmRoleService.selectByPrimaryKey(id);
		 upmUserAndUpmRole.setEnableFlag("0");//失效
		 upmUserAndUpmRoleService.updateByPrimaryKey(upmUserAndUpmRole);
			
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@RequestMapping(value="/api/UpmUserAndUpmRole/showInfo/{id}", method = RequestMethod.GET)
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap<>();
		UpmUserAndUpmRole upmUserAndUpmRole =upmUserAndUpmRoleService.selectByPrimaryKey(id);
		if(upmUserAndUpmRole== null) {
			upmUserAndUpmRole = new UpmUserAndUpmRole();
		}
		
		retMap.put("upmUserAndUpmRole", upmUserAndUpmRole);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmUserAndUpmRole/queryList", method = RequestMethod.GET)
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmUserAndUpmRole> list = upmUserAndUpmRoleService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}

