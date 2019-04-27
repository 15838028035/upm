package com.thinkit.cloud.upm.service;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmPermission;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmPermissionService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmPermission upmPermission);

    public java.lang.Long insertSelective(UpmPermission upmPermission);

    public UpmPermission selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmPermission upmPermission);

    public java.lang.Long updateByPrimaryKey(UpmPermission upmPermission);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmPermission> selectByExample(Query query);
}
