package com.hundsun.cprs.commonservice.advertisement.service;

import java.util.List;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionQuery;

public interface AdPositionService {
	/*
	 * 新建广告位
	 * @param AdPosition
	 * */
	public void createAdPosition(AdPosition news);
	
	/*
	 * 分页查询广告位
	 * @param AdPosition
	 * */
	public void queryAdPositionByPage(AdPositionQuery query);
	
	/*
	 * 删除广告位
	 * @param Long
	 * 
	 * */
	public ServiceResult deleteAdPosition(Long id);
	
	/*
	 * 修改广告位
	 * @param Long
	 * 
	 * */
	public ServiceResult editAdPosition(AdPosition adPosition);
	
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
	public List<AdPosition> selectAdPositionBycodeOrType(AdPositionRequest request);
	
	/*
	 * 根据广告位分类id查询广告位列表
	 * @param String
	 * 
	 * */
	public List<AdPosition> selectAdPositionByAdPositionTypeId(String code);
}
