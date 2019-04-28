package com.thinkit.cloud.upm.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmRole;


public interface UpmRoleMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmRole upmRole);

    public java.lang.Long insertSelective(UpmRole upmRole);

    public UpmRole selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmRole upmRole);

    public java.lang.Long updateByPrimaryKey(UpmRole upmRole);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<UpmRole> selectByExample(Object mapAndObject);

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
    public List<Integer> findRolePermissionIds(Map<String, Integer> condition);
}
