package com.thinkit.cloud.upm.service;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmRole;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmRoleService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmRole upmRole);

    public java.lang.Long insertSelective(UpmRole upmRole);

    public UpmRole selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmRole upmRole);

    public java.lang.Long updateByPrimaryKey(UpmRole upmRole);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmRole> selectByExample(Query query);
}
