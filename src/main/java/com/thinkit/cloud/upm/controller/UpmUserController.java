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

import com.thinkit.cloud.upm.bean.UpmUser;
import com.thinkit.cloud.upm.service.UpmUserService;
import com.thinkit.cloud.upm.util.Md5Util;
import com.zhongkexinli.micro.serv.common.bean.RestAPIResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;
import com.zhongkexinli.micro.serv.common.util.DateUtil;

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
	@RequestMapping(value = "/api/UpmUser", method = RequestMethod.GET)
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmUserService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@RequestMapping(value = "/api/UpmUser",method=RequestMethod.POST)
		public RestAPIResult2 create(@ModelAttribute UpmUser upmUser,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmUser.setCreateUserId(createBy);
					upmUser.setCreateUserName(getUserName(request));
					upmUser.setCreateTime(new Date());
					upmUser.setUserPass(Md5Util.md5(upmUser.getUserPass()));
					upmUserService.insertSelective(upmUser);
					
				}catch(Exception e) {
					logger.error("[系统用户表]-->新增失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
	 
		@ApiOperation(value = "更新")
		@RequestMapping(value="/api/UpmUser/{id}",method=RequestMethod.PUT)
		public RestAPIResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmUser upmUser,HttpServletRequest request)  {
			try {
					UpmUser user = upmUserService.selectByPrimaryKey(id);
		            if (!user.getUserPass().equals(upmUser.getUserPass())){
		            	upmUser.setUserPass(Md5Util.md5(upmUser.getUserPass()));
		            }
	            
					Long createBy = getLoginId(request);
					upmUser.setUpdateUserId(createBy);
					upmUser.setUpdateByUname(getUserName(request));
					upmUser.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
					upmUserService.updateByPrimaryKeySelective(upmUser);
					
				}catch(Exception e) {
					logger.error("[系统用户表]-->更新失败" ,e);
					return new RestAPIResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestAPIResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@RequestMapping(value="/api/UpmUser/{id}", method = RequestMethod.GET)
	public UpmUser show(@PathVariable("id") java.lang.Long id )  {
		UpmUser upmUser =upmUserService.selectByPrimaryKey(id);
		if(upmUser== null) {
			upmUser = new UpmUser();
		}
		return upmUser;
	}
		
	/** 逻辑删除 */
	@ApiOperation(value = "逻辑删除")
	@RequestMapping(value="/api/UpmUser/{id}",method=RequestMethod.DELETE)
	public RestAPIResult2 delete(@PathVariable("id") java.lang.Long id ) {
		UpmUser upmUser = upmUserService.selectByPrimaryKey(id);
		 upmUser.setEnableFlag("0");//失效
		 upmUserService.updateByPrimaryKey(upmUser);
			
		return new RestAPIResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@RequestMapping(value="/api/UpmUser/showInfo/{id}", method = RequestMethod.GET)
	public  Map<String,Object> showInfo(@PathVariable("id") java.lang.Long id ){
		Map<String,Object> retMap =new HashMap();
		UpmUser upmUser =upmUserService.selectByPrimaryKey(id);
		if(upmUser== null) {
			upmUser = new UpmUser();
		}
		
		retMap.put("upmUser", upmUser);
		
		return retMap;
	}
	
	@ApiOperation(value = "列表")
	@RequestMapping(value = "/api/UpmUser/queryList", method = RequestMethod.GET)
	public RestAPIResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmUser> list = upmUserService.selectByExample(query);
			return new RestAPIResult2().respData(list);
	}
}

