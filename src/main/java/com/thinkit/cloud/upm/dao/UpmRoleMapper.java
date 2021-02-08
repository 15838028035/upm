package com.thinkit.cloud.upm.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmRole;


public interface UpmRoleMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmRole UpmRole);

    public java.lang.Long insertSelective(UpmRole UpmRole);

    public UpmRole selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmRole UpmRole);

    public java.lang.Long updateByPrimaryKey(UpmRole UpmRole);

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

}
