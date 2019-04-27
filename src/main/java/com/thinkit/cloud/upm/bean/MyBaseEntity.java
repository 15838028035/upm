package com.thinkit.cloud.upm.bean;

import com.zhongkexinli.micro.serv.common.base.entity.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 自定义基础base实体类
 *
 */
public class MyBaseEntity extends BaseEntity{

	/**
	 * 创建人ID  create_User_Id
	 */
	@ApiModelProperty(value = "创建人ID")
	private Long createUserId;
	
	/**
	 * 修改人ID  update_User_Id
	 */
	@ApiModelProperty(value = "修改人ID")
	private Long updateUserId;
	
	/**
	 * 创建时间  create_Time
	 */
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	
	 /**
	 * 创建时间Begin
	 */
	private String  createTimeBegin;
	/**
	 * 创建时间End
	 */
	private String createTimeEnd;
	/**
	 * 更新时间  modifiy_Time
	 */
	@ApiModelProperty(value = "更新时间")
	private java.util.Date modifiyTime;
	
	 /**
	 * 更新时间Begin
	 */
	private String  modifiyTimeBegin;
	/**
	 * 更新时间End
	 */
	private String modifiyTimeEnd;
	
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public java.util.Date getModifiyTime() {
		return modifiyTime;
	}
	public void setModifiyTime(java.util.Date modifiyTime) {
		this.modifiyTime = modifiyTime;
	}
	public String getModifiyTimeBegin() {
		return modifiyTimeBegin;
	}
	public void setModifiyTimeBegin(String modifiyTimeBegin) {
		this.modifiyTimeBegin = modifiyTimeBegin;
	}
	public String getModifiyTimeEnd() {
		return modifiyTimeEnd;
	}
	public void setModifiyTimeEnd(String modifiyTimeEnd) {
		this.modifiyTimeEnd = modifiyTimeEnd;
	}

}
