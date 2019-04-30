package com.thinkit.cloud.upm.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;


public interface UpmUserAndUpmRoleMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmUserAndUpmRole upmUserAndUpmRole);

    public java.lang.Long insertSelective(UpmUserAndUpmRole upmUserAndUpmRole);

    public UpmUserAndUpmRole selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmUserAndUpmRole upmUserAndUpmRole);

    public java.lang.Long updateByPrimaryKey(UpmUserAndUpmRole upmUserAndUpmRole);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<UpmUserAndUpmRole> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
