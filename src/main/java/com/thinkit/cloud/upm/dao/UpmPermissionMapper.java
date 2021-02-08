package com.thinkit.cloud.upm.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmPermission;


public interface UpmPermissionMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmPermission UpmPermission);

    public java.lang.Long insertSelective(UpmPermission UpmPermission);

    public UpmPermission selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmPermission UpmPermission);

    public java.lang.Long updateByPrimaryKey(UpmPermission UpmPermission);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<UpmPermission> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
