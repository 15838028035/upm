package com.thinkit.cloud.upm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.upm.bean.UpmPermission;
import com.thinkit.cloud.upm.bean.UpmRole;
import com.thinkit.cloud.upm.dao.UpmRoleMapper;
import com.thinkit.cloud.upm.service.UpmPermissionService;
import com.thinkit.cloud.upm.service.UpmRoleService;
import com.thinkit.cloud.upm.tree.BootStrapTreeView;
import com.thinkit.cloud.upm.tree.BootStrapTreeViewCheck;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class UpmRoleServiceImpl  implements UpmRoleService{
	
	@Autowired
	private UpmRoleMapper upmRoleMapper;
	
	@Autowired
	private UpmPermissionService upmPermissionService;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return upmRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(UpmRole upmRole){
		return upmRoleMapper.insert(upmRole);
	}

	@Override
	public java.lang.Long insertSelective(UpmRole upmRole) {
		return upmRoleMapper.insertSelective(upmRole);
	}

	@Override
	public UpmRole selectByPrimaryKey(java.lang.Long id) {
		return upmRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(UpmRole upmRole) {
		return upmRoleMapper.updateByPrimaryKeySelective(upmRole);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(UpmRole upmRole) {
		return upmRoleMapper.updateByPrimaryKey(upmRole);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = upmRoleMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<UpmRole> selectByExample(Query query) {
		return upmRoleMapper.selectByExample(query);
	}
	
	/**
	   * @Description : 查询权限菜单树（没有复选框）
	   * 
	   * @param roleId 角色ID
	   * @param appId 应用Id
	   * @return 查询权限菜单树
	   * @throws Exception 异常
	   */
	  public String getPermissionTreeDataJson(Integer roleId, String appId, Integer operatorId) throws Exception {
	    Query query = new Query().putFilter("appId", appId).putFilter("operatorId", operatorId).putFilter("parentId", 0).putFilter("sortName", " parent_id id sort_no");
	    List<UpmPermission> list = upmPermissionService.selectByExample(query);

	    if (null != list && !list.isEmpty()) {
	      List<Integer> permissionIds = getRolePermissionIds(roleId);
	      List<BootStrapTreeView> treeNodeList = new ArrayList();
	      for (int i = 0; i < list.size(); i++) {
	        UpmPermission up = list.get(i);
	        String id = up.getId() + "";
	        String text = up.getPerName();
	        Boolean checked = false;
	        for (int j = 0; j < permissionIds.size(); j++) {
	          Integer tmpId = permissionIds.get(j);
	          if (up.getId().equals(tmpId)) {
	            checked = true;
	            break;
	          }
	        }
	        String parentId = up.getParentId().intValue() + "";
	        treeNodeList.add(BootStrapTreeViewCheck.createNew(id, text, checked, parentId));
	      }

	      Long rootId = upmPermissionService.findRootPermissionIdByAppId(appId);
	      return BootStrapTreeViewCheck.valueOfString(treeNodeList, rootId.toString());
	    }
	    return null;
	}

	  /**
	   * 查找角色对应的权限
	   * 
	   * @param roleId 角色ID
	   * @return 权限ID列表
	   */
	  public List<Integer> getRolePermissionIds(Integer roleId) {
	    Map<String, Integer> condition = new HashMap<String, Integer>();
	    condition.put("roleId", roleId);
	    List<Integer> permissionIds = upmRoleMapper.findRolePermissionIds(condition);
	    return permissionIds;
	  }

}
