package com.thinkit.cloud.upm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.dao.UpmRoleAndPermissionRelMapper;
import com.thinkit.cloud.upm.service.UpmRoleAndPermissionRelService;
import com.thinkit.cloud.upm.bean.UpmRoleAndPermissionRel;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmRoleAndPermissionRelServiceImpl  implements UpmRoleAndPermissionRelService{
	
	@Autowired
	private UpmRoleAndPermissionRelMapper upmRoleAndPermissionRelMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return upmRoleAndPermissionRelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmRoleAndPermissionRel upmRoleAndPermissionRel){
		return upmRoleAndPermissionRelMapper.insert(upmRoleAndPermissionRel);
	}

	@Override
	public java.lang.Long insertSelective(UpmRoleAndPermissionRel upmRoleAndPermissionRel) {
		return upmRoleAndPermissionRelMapper.insertSelective(upmRoleAndPermissionRel);
	}

	@Override
	public UpmRoleAndPermissionRel selectByPrimaryKey(java.lang.Long id) {
		return upmRoleAndPermissionRelMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmRoleAndPermissionRel upmRoleAndPermissionRel) {
		return upmRoleAndPermissionRelMapper.updateByPrimaryKeySelective(upmRoleAndPermissionRel);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmRoleAndPermissionRel upmRoleAndPermissionRel) {
		return upmRoleAndPermissionRelMapper.updateByPrimaryKey(upmRoleAndPermissionRel);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = upmRoleAndPermissionRelMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmRoleAndPermissionRel> selectByExample(Query query) {
		return upmRoleAndPermissionRelMapper.selectByExample(query);
	}

}
