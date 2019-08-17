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

import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;
import com.thinkit.cloud.upm.service.UpmUserAndUpmRoleService;
import com.zhongkexinli.micro.serv.common.bean.RestApiResult2;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

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
	@GetMapping(value = "/api/UpmUserAndUpmRole")
	public LayUiTableResultResponse page(@RequestParam(defaultValue = "10") int limit,
	      @RequestParam(defaultValue = "1") int offset,@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			return  upmUserAndUpmRoleService.selectByQuery(query);
	}
	 
		@ApiOperation(value = "新增")
		@PostMapping(value = "/api/UpmUserAndUpmRole")
		public RestApiResult2 create(@ModelAttribute UpmUserAndUpmRole upmUserAndUpmRole,HttpServletRequest request)  {
			
			try {
					Long createBy = getLoginId(request);
					upmUserAndUpmRole.setCreateUserId(createBy);
					upmUserAndUpmRole.setCreateUserName(getUserName(request));
					upmUserAndUpmRole.setCreateTime(new Date());
					upmUserAndUpmRoleService.insertSelective(upmUserAndUpmRole);
					
				}catch(Exception e) {
					logger.error("[用户角色关联表]-->新增失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("新增失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
	 
		@ApiOperation(value = "更新")
		@PutMapping(value="/api/UpmUserAndUpmRole/{id}")
		public RestApiResult2 update(@PathVariable("id") java.lang.Long id ,@ModelAttribute UpmUserAndUpmRole upmUserAndUpmRole,HttpServletRequest request)  {
			try {
					
					Long createBy = getLoginId(request);
					upmUserAndUpmRole.setUpdateUserId(createBy);
					upmUserAndUpmRole.setUpdateUserName(getUserName(request));
					upmUserAndUpmRole.setUpdateTime(new Date());
					upmUserAndUpmRoleService.updateByPrimaryKeySelective(upmUserAndUpmRole);
					
				}catch(Exception e) {
					logger.error("[用户角色关联表]-->更新失败" ,e);
					return new RestApiResult2().respCode(0).respMsg("更新失败 {}" ,e.getMessage());
				}
				
				return new RestApiResult2();
	}
		
		@ApiOperation(value = "批量保存")
		@PutMapping(value="/api/UpmUserAndUpmRole/{userId}/{multiSelected}")
		public RestApiResult2 doBatchSaveRel(@PathVariable("userId") java.lang.Long userId , @PathVariable("multiSelected") String multiSelected ,HttpServletRequest request)  {
		    String[] multiSelectedTmp;
		    if (multiSelected.indexOf(',') >= 0) {
		      multiSelectedTmp = multiSelected.split(",");
		    } else {
		      multiSelectedTmp = new String[] { multiSelected };
		    }
		    
		    Long createBy = getLoginId(request);
		    
		    for (int i = 0; i < multiSelectedTmp.length; i++) {
		      Long selectedId = Long.parseLong(multiSelectedTmp[i].trim());

		        List<UpmUserAndUpmRole> list = upmUserAndUpmRoleService.selectByExample(new Query().putFilter("userId", userId));
		        if (list.isEmpty()) {
			         UpmUserAndUpmRole upmUserAndUpmRole = new UpmUserAndUpmRole();
			         upmUserAndUpmRole.setUserId(userId);
			         upmUserAndUpmRole.setRoleId(selectedId);
		          
		          	upmUserAndUpmRole.setCreateUserId(createBy);
					upmUserAndUpmRole.setCreateUserName(getUserName(request));
					upmUserAndUpmRole.setCreateTime(new Date());
					upmUserAndUpmRoleService.insertSelective(upmUserAndUpmRole);

		        } else {
		        		UpmUserAndUpmRole upmUserAndUpmRole =  list.get(0);
				         upmUserAndUpmRole.setUserId(userId);
				         upmUserAndUpmRole.setRoleId(selectedId);
			          
			          	upmUserAndUpmRole.setUpdateUserId(createBy);
						upmUserAndUpmRole.setUpdateUserName(getUserName(request));
						upmUserAndUpmRole.setUpdateTime(new Date());
						upmUserAndUpmRoleService.updateByPrimaryKeySelective(upmUserAndUpmRole);
		        }
		        
		}
				
		return new RestApiResult2();
	}
		
	/** 显示 */
	@ApiOperation(value = "查看")
	@GetMapping(value="/api/UpmUserAndUpmRole/{id}")
	public UpmUserAndUpmRole show(@PathVariable("id") java.lang.Long id )  {
		UpmUserAndUpmRole upmUserAndUpmRole =upmUserAndUpmRoleService.selectByPrimaryKey(id);
		if(upmUserAndUpmRole== null) {
			upmUserAndUpmRole = new UpmUserAndUpmRole();
		}
		return upmUserAndUpmRole;
	}
		
	/** 物理删除 */
	@ApiOperation(value = "物理删除")
	@DeleteMapping(value="/api/UpmUserAndUpmRole/{id}")
	public RestApiResult2 delete(@PathVariable("id") java.lang.Long id ) {
		 upmUserAndUpmRoleService.deleteByPrimaryKey(id);
		return new RestApiResult2();
	}

	/** 显示 */
	@ApiOperation(value = "显示")
	@GetMapping(value="/api/UpmUserAndUpmRole/showInfo/{id}")
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
	@GetMapping(value = "/api/UpmUserAndUpmRole/queryList")
	public RestApiResult2 queryList(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmUserAndUpmRole> list = upmUserAndUpmRoleService.selectByExample(query);
			return new RestApiResult2().respData(list);
	}
	
	@ApiOperation(value = "列表")
	@GetMapping(value = "/api/UpmUserAndUpmRole/exist")
	public Boolean exist(@RequestParam Map<String, Object> params) {
			Query query= new Query(params);
			List<UpmUserAndUpmRole> list = upmUserAndUpmRoleService.selectByExample(query);
			return !list.isEmpty();
	}
}

