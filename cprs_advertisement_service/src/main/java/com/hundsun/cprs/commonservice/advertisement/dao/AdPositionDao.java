package com.hundsun.cprs.commonservice.advertisement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionQuery;

public interface AdPositionDao {
	/*
	 * 创建广告位
	 * @param AdPosition
	 * 
	 * */
	public void createAdPosition(AdPosition news);
	
	/*
	 * 分页查询
	 * @param AdPositionQuery
	 * 
	 * */
	public int selectAdPositionCount(AdPositionQuery query);
	
	/*
	 * 分页查询
	 * @param AdPositionQuery
	 * 
	 * */
	public List<AdPosition> selectAdPositionByPage(@Param("query") AdPositionQuery query,@Param("start")int startIndex,@Param("end")int pageNum);

	/*
	 * 删除广告位
	 * @param Long
	 * 
	 * */
	public void deleteAdPosition(Long id);
	
	/*
	 * 修改广告位
	 * @param Long
	 * 
	 * */
	public void editAdPosition(AdPosition adPosition);
	
	/*
	 * 根据id查询广告位
	 * @param Long
	 * 
	 * */
	public AdPosition selectAdPositionById(Long id);
	
	/*
	 * 查询广告位列表
	 * 
	 * */
	public List<AdPosition> selectAdPositionList();
	
	/*
	 * 根据code查询广告位
	 * @param String
	 * 
	 * */
	public List<AdPosition> selectAdPositionBycode(String code);
	
	/*
	 * 根据广告位code或者广告位分类code查询广告位列表
	 * @param String
	 * 
	 * */
	public List<AdPosition> selectAdPositionBycodeOrType(@Param("adPositioncode")String adPositioncode,@Param("adPositionTypeCode")String adPositionTypeCode);
}
