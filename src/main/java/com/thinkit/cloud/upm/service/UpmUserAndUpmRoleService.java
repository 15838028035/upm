package com.thinkit.cloud.upm.service;

import java.util.List;

import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmUserAndUpmRoleService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmUserAndUpmRole upmUserAndUpmRole);

    public java.lang.Long insertSelective(UpmUserAndUpmRole upmUserAndUpmRole);

    public UpmUserAndUpmRole selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmUserAndUpmRole upmUserAndUpmRole);

    public java.lang.Long updateByPrimaryKey(UpmUserAndUpmRole upmUserAndUpmRole);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmUserAndUpmRole> selectByExample(Query query);
}
