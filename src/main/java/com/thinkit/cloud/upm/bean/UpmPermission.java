package com.thinkit.cloud.upm.bean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
*权限信息表
*/
@ApiModel(value = "权限信息表")
public class UpmPermission extends MyBaseEntity{
	
	/**
	 * ID  id
	 */
	@ApiModelProperty(value = "ID")
	private java.lang.Long id;
	
	/**
	 * 应用ID  app_id
	 */
	@ApiModelProperty(value = "应用ID")
	private String appId = "";
	
	/**
	 * 父ID  parent_Id
	 */
	@ApiModelProperty(value = "父ID")
	private java.lang.Long parentId;
	
	/**
	 * 权限名称  per_name
	 */
	@ApiModelProperty(value = "权限名称")
	private String perName = "";
	
	/**
	 * 权限类型  per_type
	 */
	@ApiModelProperty(value = "权限类型")
	private String perType = "";
	
	/**
	 * URL  url
	 */
	@ApiModelProperty(value = "URL")
	private String url = "";
	
	/**
	 * 编码  code
	 */
	@ApiModelProperty(value = "编码")
	private String code = "";
	
	/**
	 * 编码key  key_code
	 */
	@ApiModelProperty(value = "编码key")
	private String keyCode = "";
	
	/**
	 * 1:正常，0：加锁  state
	 */
	@ApiModelProperty(value = "1:正常，0：加锁")
	private String state = "";
	
	/**
	 * 备注  remark
	 */
	@ApiModelProperty(value = "备注")
	private String remark = "";
	
	/**
	 * 排序编号  sort_no
	 */
	@ApiModelProperty(value = "排序编号")
	private java.lang.Long sortNo;

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setAppId(String value) {
		this.appId = value;
	}
	
	public String getAppId() {
		return this.appId;
	}
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
	}
	
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	public void setPerName(String value) {
		this.perName = value;
	}
	
	public String getPerName() {
		return this.perName;
	}
	public void setPerType(String value) {
		this.perType = value;
	}
	
	public String getPerType() {
		return this.perType;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setKeyCode(String value) {
		this.keyCode = value;
	}
	
	public String getKeyCode() {
		return this.keyCode;
	}
	public void setState(String value) {
		this.state = value;
	}
	
	public String getState() {
		return this.state;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setSortNo(java.lang.Long value) {
		this.sortNo = value;
	}
	
	public java.lang.Long getSortNo() {
		return this.sortNo;
	}
	
}

