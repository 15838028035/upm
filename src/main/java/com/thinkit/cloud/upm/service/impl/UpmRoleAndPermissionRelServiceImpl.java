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
	private UpmRoleAndPermissionRelMapper UpmRoleAndPermissionRelMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return UpmRoleAndPermissionRelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmRoleAndPermissionRel UpmRoleAndPermissionRel){
		return UpmRoleAndPermissionRelMapper.insert(UpmRoleAndPermissionRel);
	}

	@Override
	public java.lang.Long insertSelective(UpmRoleAndPermissionRel UpmRoleAndPermissionRel) {
		return UpmRoleAndPermissionRelMapper.insertSelective(UpmRoleAndPermissionRel);
	}

	@Override
	public UpmRoleAndPermissionRel selectByPrimaryKey(java.lang.Long id) {
		return UpmRoleAndPermissionRelMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmRoleAndPermissionRel UpmRoleAndPermissionRel) {
		return UpmRoleAndPermissionRelMapper.updateByPrimaryKeySelective(UpmRoleAndPermissionRel);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmRoleAndPermissionRel UpmRoleAndPermissionRel) {
		return UpmRoleAndPermissionRelMapper.updateByPrimaryKey(UpmRoleAndPermissionRel);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = UpmRoleAndPermissionRelMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmRoleAndPermissionRel> selectByExample(Query query) {
		return UpmRoleAndPermissionRelMapper.selectByExample(query);
	}

}
