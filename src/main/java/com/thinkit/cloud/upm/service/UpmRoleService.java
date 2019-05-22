package com.thinkit.cloud.upm.service;

import java.util.List;

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
    
    /**
     * @Description : 查询权限菜单树（没有复选框）
     * 
     * @param roleId 角色ID
     * @param appId 应用Id
     * @return 查询权限菜单树
     * @throws Exception 异常
     */
  public String getPermissionTreeDataJson(Integer roleId, String appId, Long operatorId) ;
  
  /**
   * 将权限分配给角色
   * 
   * @param permissions 权限
   * @param appId 应用Id
   * @param roleId 角色Id
   */
  public void addPermissionToRole(String permissions, String appId, Long roleId) throws Exception ;
}
