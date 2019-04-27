package com.thinkit.cloud.upm.service;

import java.util.List;

import com.thinkit.cloud.upm.bean.UpmUser;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmUserService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmUser upmUser);

    public java.lang.Long insertSelective(UpmUser upmUser);

    public UpmUser selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmUser upmUser);

    public java.lang.Long updateByPrimaryKey(UpmUser upmUser);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmUser> selectByExample(Query query);
}
