package com.hundsun.cprs.commonservice.advertisement.domain;

import com.hundsun.cprs.commonservice.advertisement.common.DomainBase;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;

import java.util.Date;

/*
 * 广告位分类实体
 * 
 * */
public class AdPositionType extends DomainBase{

	private static final long serialVersionUID = 1L;
	private Long id;//主键id
	private String name;//广告位分类名称
	private String code;//广告位分类代码
	private String description;//广告位分类描述
	private String definyType = EnumDefinyType.CLIENT_TYEP.getType();//客户定义类型
	private Date gmtCreate;//创建时间
	private Date gmtModify;//修改时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getDefinyType() {
		return definyType;
	}

	public void setDefinyType(String definyType) {
		this.definyType = definyType;
	}
}
