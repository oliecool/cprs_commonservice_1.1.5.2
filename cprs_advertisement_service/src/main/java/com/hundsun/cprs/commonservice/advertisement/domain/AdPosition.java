package com.hundsun.cprs.commonservice.advertisement.domain;

import com.hundsun.cprs.commonservice.advertisement.common.DomainBase;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;

import java.util.Date;

/*
 * 广告位实体
 * 
 * */
public class AdPosition extends DomainBase{

	private static final long serialVersionUID = 1L;
	private	Long id;//主键id
	private String name;//广告位名称
	private String code;//广告位编码
	private String systemtype;//所属系统类型
	private Long adPositionTypeId; //所属分类id
	private String description;//广告位描述
	private String template;//广告位模板
	private String definyType = EnumDefinyType.CLIENT_TYEP.getType();//客户定义类型
	private Date gmtCreate;//创建时间
	private Date gmtModify;//修改时间
	private String adPositionTypeName;//所属分类名称
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
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public Long getAdPositionTypeId() {
		return adPositionTypeId;
	}
	public void setAdPositionTypeId(Long adPositionTypeId) {
		this.adPositionTypeId = adPositionTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
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
	public String getAdPositionTypeName() {
		return adPositionTypeName;
	}
	public void setAdPositionTypeName(String adPositionTypeName) {
		this.adPositionTypeName = adPositionTypeName;
	}

	public String getDefinyType() {
		return definyType;
	}

	public void setDefinyType(String definyType) {
		this.definyType = definyType;
	}
}
