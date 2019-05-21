package com.thinkit.cloud.upm.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*角色权限关联表
*/
@ApiModel(value = "角色权限关联表")
public class UpmRoleAndPermissionRel extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 角色ID  role_id
	 */
	@ApiModelProperty(value = "角色ID")
	private java.lang.Long roleId;
	
	/**
	 * 权限ID  permission_id
	 */
	@ApiModelProperty(value = "权限ID")
	private java.lang.Long permissionId;

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	public void setPermissionId(java.lang.Long value) {
		this.permissionId = value;
	}
	
	public java.lang.Long getPermissionId() {
		return this.permissionId;
	}
	
}

