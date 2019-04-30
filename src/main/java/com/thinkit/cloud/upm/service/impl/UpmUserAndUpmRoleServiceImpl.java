package com.thinkit.cloud.upm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.dao.UpmUserAndUpmRoleMapper;
import com.thinkit.cloud.upm.service.UpmUserAndUpmRoleService;
import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmUserAndUpmRoleServiceImpl  implements UpmUserAndUpmRoleService{
	
	@Autowired
	private UpmUserAndUpmRoleMapper upmUserAndUpmRoleMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return upmUserAndUpmRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmUserAndUpmRole upmUserAndUpmRole){
		return upmUserAndUpmRoleMapper.insert(upmUserAndUpmRole);
	}

	@Override
	public java.lang.Long insertSelective(UpmUserAndUpmRole upmUserAndUpmRole) {
		return upmUserAndUpmRoleMapper.insertSelective(upmUserAndUpmRole);
	}

	@Override
	public UpmUserAndUpmRole selectByPrimaryKey(java.lang.Long id) {
		return upmUserAndUpmRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmUserAndUpmRole upmUserAndUpmRole) {
		return upmUserAndUpmRoleMapper.updateByPrimaryKeySelective(upmUserAndUpmRole);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmUserAndUpmRole upmUserAndUpmRole) {
		return upmUserAndUpmRoleMapper.updateByPrimaryKey(upmUserAndUpmRole);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = upmUserAndUpmRoleMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmUserAndUpmRole> selectByExample(Query query) {
		return upmUserAndUpmRoleMapper.selectByExample(query);
	}

}
