package com.thinkit.cloud.upm.service;

import java.util.List;

import com.thinkit.cloud.upm.bean.UpmUserAndUpmRole;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface UpmUserAndUpmRoleService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(UpmUserAndUpmRole UpmUserAndUpmRole);

    public java.lang.Long insertSelective(UpmUserAndUpmRole UpmUserAndUpmRole);

    public UpmUserAndUpmRole selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(UpmUserAndUpmRole UpmUserAndUpmRole);

    public java.lang.Long updateByPrimaryKey(UpmUserAndUpmRole UpmUserAndUpmRole);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<UpmUserAndUpmRole> selectByExample(Query query);
}
