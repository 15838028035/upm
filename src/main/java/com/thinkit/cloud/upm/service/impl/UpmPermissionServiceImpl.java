package com.thinkit.cloud.upm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.bean.UpmPermission;
import com.thinkit.cloud.upm.dao.UpmPermissionMapper;
import com.thinkit.cloud.upm.service.UpmPermissionService;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmPermissionServiceImpl  implements UpmPermissionService{
	
	@Autowired
	private UpmPermissionMapper upmPermissionMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return upmPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmPermission upmPermission){
		return upmPermissionMapper.insert(upmPermission);
	}

	@Override
	public java.lang.Long insertSelective(UpmPermission upmPermission) {
		return upmPermissionMapper.insertSelective(upmPermission);
	}

	@Override
	public UpmPermission selectByPrimaryKey(java.lang.Long id) {
		return upmPermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmPermission upmPermission) {
		return upmPermissionMapper.updateByPrimaryKeySelective(upmPermission);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmPermission upmPermission) {
		return upmPermissionMapper.updateByPrimaryKey(upmPermission);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = upmPermissionMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmPermission> selectByExample(Query query) {
		return upmPermissionMapper.selectByExample(query);
	}
	
	/**
	   * 根据appId查询permissionId
	   * 
	   * @param appId 应用 ID
	   * @return 根据appId查询permissionId
	   */
	  public Long findRootPermissionIdByAppId(String appId) throws Exception {
		  List<UpmPermission> list =  upmPermissionMapper.selectByExample(new Query().putFilter("appId", appId).putFilter("state", "1"));
		  
		  if(list.isEmpty()){
			  return null;
		  }
	    return list.get(0).getId();
	}

}
