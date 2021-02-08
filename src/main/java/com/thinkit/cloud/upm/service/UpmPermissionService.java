package com.thinkit.cloud.upm.service;

import java.util.List;

import com.thinkit.cloud.upm.bean.UpmPermission;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmPermissionService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmPermission UpmPermission);

    public java.lang.Long insertSelective(UpmPermission UpmPermission);

    public UpmPermission selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmPermission UpmPermission);

    public java.lang.Long updateByPrimaryKey(UpmPermission UpmPermission);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmPermission> selectByExample(Query query);
    
    /**
     * 根据appId查询permissionId
     * 
     * @param appId 应用 ID
     * @return 根据appId查询permissionId
     */
  public Long findRootPermissionIdByAppId(String appId) ;
}
