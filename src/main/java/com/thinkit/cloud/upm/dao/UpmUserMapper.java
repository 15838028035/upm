package com.thinkit.cloud.upm.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.upm.bean.UpmUser;


public interface UpmUserMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmUser UpmUser);

    public java.lang.Long insertSelective(UpmUser UpmUser);

    public UpmUser selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmUser UpmUser);

    public java.lang.Long updateByPrimaryKey(UpmUser UpmUser);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<UpmUser> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
