package com.thinkit.cloud.upm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;
import com.thinkit.cloud.upm.dao.UpmUserAndUpmRoleMapper;
import com.thinkit.cloud.upm.service.UpmUserAndUpmRoleService;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmUserAndUpmRoleServiceImpl  implements UpmUserAndUpmRoleService{
	
	@Autowired
	private UpmUserAndUpmRoleMapper UpmUserAndUpmRoleMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return UpmUserAndUpmRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmUserAndUpmRole UpmUserAndUpmRole){
		return UpmUserAndUpmRoleMapper.insert(UpmUserAndUpmRole);
	}

	@Override
	public java.lang.Long insertSelective(UpmUserAndUpmRole UpmUserAndUpmRole) {
		return UpmUserAndUpmRoleMapper.insertSelective(UpmUserAndUpmRole);
	}

	@Override
	public UpmUserAndUpmRole selectByPrimaryKey(java.lang.Long id) {
		return UpmUserAndUpmRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmUserAndUpmRole UpmUserAndUpmRole) {
		return UpmUserAndUpmRoleMapper.updateByPrimaryKeySelective(UpmUserAndUpmRole);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmUserAndUpmRole UpmUserAndUpmRole) {
		return UpmUserAndUpmRoleMapper.updateByPrimaryKey(UpmUserAndUpmRole);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = UpmUserAndUpmRoleMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmUserAndUpmRole> selectByExample(Query query) {
		return UpmUserAndUpmRoleMapper.selectByExample(query);
	}

}
