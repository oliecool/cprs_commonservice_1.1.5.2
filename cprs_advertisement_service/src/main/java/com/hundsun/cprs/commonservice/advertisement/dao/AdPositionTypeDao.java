package com.hundsun.cprs.commonservice.advertisement.dao;

import java.util.List;

import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionType;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionTypeQuery;

public interface AdPositionTypeDao {
	/*
	 * 创建广告位分类
	 * @param AdPositionType
	 * */
	public void createAdPositionType(AdPositionType adPositionType);
	
	/*
	 * 分页查询广告位分类
	 * @param AdPositionTypeQuery
	 * */
	public List<AdPositionType> queryByPage(AdPositionTypeQuery query);
	
	/*
	 * 删除广告位分类
	 * @param Long
	 * 
	 * */
	public void deleteAdPositionType(Long id);
	
	/*
	 * 修改广告位分类
	 * @param AdPositionType
	 * 
	 * */
	public void editAdPositionType(AdPositionType adPositionType);
	
	/*
	 * 根据id查询广告位分类
	 * @param Long
	 * 
	 * */
	public AdPositionType selectAdPositionTypeById(Long id);
	
	/*
	 * 查询广告位分类list
	 * 
	 * */
	public List<AdPositionType> selectAdPositionTypeList();
	
	/*
	 * 根据code查询广告位分类
	 * @param String
	 * 
	 * */
	public List<AdPositionType> selectAdPositionTypeByCode(String code);
}
