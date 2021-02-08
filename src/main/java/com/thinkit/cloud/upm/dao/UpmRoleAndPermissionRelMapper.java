package com.thinkit.cloud.upm.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmRoleAndPermissionRel;


public interface UpmRoleAndPermissionRelMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public java.lang.Long insertSelective(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public UpmRoleAndPermissionRel selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

    public java.lang.Long updateByPrimaryKey(UpmRoleAndPermissionRel UpmRoleAndPermissionRel);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<UpmRoleAndPermissionRel> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);

    /**
	   * 查找角色对应的权限
	   * 
	   * @param roleId 角色ID
	   * @return 权限ID列表
	   */
    public List<Long> findRolePermissionIds(Integer roleId);

    /**
     * 根据roleId删除
     * @param roleId
     */
	public void deletePermissionById(Long roleId);
}
