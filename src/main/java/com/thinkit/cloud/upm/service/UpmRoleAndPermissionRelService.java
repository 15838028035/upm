package com.thinkit.cloud.upm.service;

import java.util.List;

import com.thinkit.cloud.upm.bean.UpmRoleAndPermissionRel;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmRoleAndPermissionRelService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public java.lang.Long insertSelective(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public UpmRoleAndPermissionRel selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public java.lang.Long updateByPrimaryKey(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmRoleAndPermissionRel> selectByExample(Query query);
}
