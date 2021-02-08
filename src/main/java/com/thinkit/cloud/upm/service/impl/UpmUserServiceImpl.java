package com.thinkit.cloud.upm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.bean.UpmUser;
import com.thinkit.cloud.upm.dao.UpmUserMapper;
import com.thinkit.cloud.upm.service.UpmUserService;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmUserServiceImpl  implements UpmUserService{
	
	@Autowired
	private UpmUserMapper UpmUserMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return UpmUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmUser UpmUser){
		return UpmUserMapper.insert(UpmUser);
	}

	@Override
	public java.lang.Long insertSelective(UpmUser UpmUser) {
		return UpmUserMapper.insertSelective(UpmUser);
	}

	@Override
	public UpmUser selectByPrimaryKey(java.lang.Long id) {
		return UpmUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmUser UpmUser) {
		return UpmUserMapper.updateByPrimaryKeySelective(UpmUser);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmUser UpmUser) {
		return UpmUserMapper.updateByPrimaryKey(UpmUser);
	}

	@Override
	 public LayUiTableResultResponse<UpmUser> selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = UpmUserMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmUser> selectByExample(Query query) {
		return UpmUserMapper.selectByExample(query);
	}

}
