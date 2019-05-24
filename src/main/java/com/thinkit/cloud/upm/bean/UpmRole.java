package com.thinkit.cloud.upm.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*角色信息表
*/
@ApiModel(value = "角色信息表")
public class UpmRole extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 角色编码  role_code
	 */
	@ApiModelProperty(value = "角色编码")
	private String roleCode;
	
	/**
	 * 应用ID  app_id
	 */
	@ApiModelProperty(value = "应用ID")
	private String appId;
	
	/**
	 * 角色名称  role_name
	 */
	@ApiModelProperty(value = "角色名称")
	private String roleName;
	
	/**
	 * 角色描述  role_desc
	 */
	@ApiModelProperty(value = "角色描述")
	private String roleDesc;
	
	/**
	 * 加锁状态,1:加锁，0:未加锁  lock_Status
	 */
	@ApiModelProperty(value = "加锁状态,1:加锁，0:未加锁")
	private String lockStatus;
	
	/**
	 * 权限信息ids
	 */
	 private String permissions;

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setRoleCode(String value) {
		this.roleCode = value;
	}
	
	public String getRoleCode() {
		return this.roleCode;
	}
	public void setAppId(String value) {
		this.appId = value;
	}
	
	public String getAppId() {
		return this.appId;
	}
	public void setRoleName(String value) {
		this.roleName = value;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleDesc(String value) {
		this.roleDesc = value;
	}
	
	public String getRoleDesc() {
		return this.roleDesc;
	}
	public void setLockStatus(String value) {
		this.lockStatus = value;
	}
	
	public String getLockStatus() {
		return this.lockStatus;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}

