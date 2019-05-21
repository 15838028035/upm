package com.thinkit.cloud.upm.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*用户角色关联表
*/
@ApiModel(value = "用户角色关联表")
public class UpmUserAndUpmRole extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 用户ID  user_id
	 */
	@ApiModelProperty(value = "用户ID")
	private java.lang.Long userId;
	
	/**
	 * 角色ID  role_id
	 */
	@ApiModelProperty(value = "角色ID")
	private java.lang.Long roleId;

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	
}

