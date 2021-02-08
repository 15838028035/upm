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
	private UpmPermissionMapper UpmPermissionMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return UpmPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmPermission UpmPermission){
		return UpmPermissionMapper.insert(UpmPermission);
	}

	@Override
	public java.lang.Long insertSelective(UpmPermission UpmPermission) {
		return UpmPermissionMapper.insertSelective(UpmPermission);
	}

	@Override
	public UpmPermission selectByPrimaryKey(java.lang.Long id) {
		return UpmPermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmPermission UpmPermission) {
		return UpmPermissionMapper.updateByPrimaryKeySelective(UpmPermission);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmPermission UpmPermission) {
		return UpmPermissionMapper.updateByPrimaryKey(UpmPermission);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = UpmPermissionMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmPermission> selectByExample(Query query) {
		return UpmPermissionMapper.selectByExample(query);
	}
	
	/**
	   * 根据appId查询permissionId
	   * 
	   * @param appId 应用 ID
	   * @return 根据appId查询permissionId
	   */
	  public Long findRootPermissionIdByAppId(String appId)  {
		  List<UpmPermission> list =  UpmPermissionMapper.selectByExample(new Query().putFilter("appId", appId).putFilter("state", "1"));
		  
		  if(list.isEmpty()){
			  return null;
		  }
	    return list.get(0).getId();
	}

}
