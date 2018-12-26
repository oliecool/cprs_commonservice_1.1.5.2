package com.hundsun.cprs.advertisement.vo;

public class AdPositionInfoVo {
	private	Long id;//广告位id
	private String name;//广告位名称
	private String code;//广告位编码
	private Long adPositionTypeId; //所属分类id
	private String description;//广告位描述
	private String template;//广告位模板
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
	
}
