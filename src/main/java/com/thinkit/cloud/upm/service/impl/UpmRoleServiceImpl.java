package com.thinkit.cloud.upm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.bean.UpmRole;
import com.thinkit.cloud.upm.dao.UpmRoleMapper;
import com.thinkit.cloud.upm.service.UpmRoleService;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmRoleServiceImpl  implements UpmRoleService{
	
	@Autowired
	private UpmRoleMapper upmRoleMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return upmRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmRole upmRole){
		return upmRoleMapper.insert(upmRole);
	}

	@Override
	public java.lang.Long insertSelective(UpmRole upmRole) {
		return upmRoleMapper.insertSelective(upmRole);
	}

	@Override
	public UpmRole selectByPrimaryKey(java.lang.Long id) {
		return upmRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmRole upmRole) {
		return upmRoleMapper.updateByPrimaryKeySelective(upmRole);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmRole upmRole) {
		return upmRoleMapper.updateByPrimaryKey(upmRole);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = upmRoleMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmRole> selectByExample(Query query) {
		return upmRoleMapper.selectByExample(query);
	}

}
