package com.thinkit.cloud.upm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.dao.UpmUserMapper;
import com.thinkit.cloud.upm.service.UpmUserService;
import com.thinkit.cloud.upm.bean.UpmUser;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmUserServiceImpl  implements UpmUserService{
	
	@Autowired
	private UpmUserMapper upmUserMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return upmUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmUser upmUser){
		return upmUserMapper.insert(upmUser);
	}

	@Override
	public java.lang.Long insertSelective(UpmUser upmUser) {
		return upmUserMapper.insertSelective(upmUser);
	}

	@Override
	public UpmUser selectByPrimaryKey(java.lang.Long id) {
		return upmUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmUser upmUser) {
		return upmUserMapper.updateByPrimaryKeySelective(upmUser);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmUser upmUser) {
		return upmUserMapper.updateByPrimaryKey(upmUser);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = upmUserMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmUser> selectByExample(Query query) {
		return upmUserMapper.selectByExample(query);
	}

}
