package com.hundsun.cprs.commonservice.advertisement.service;

import java.util.List;

import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdvertisementQuery;

public interface AdvertisementService {
	/*
	 * 新增广告
	 * @param Advertisement
	 * */
	public ServiceResult createAdvertisement(Advertisement advertisement);
	
	/*
	 * 分页查询广告
	 * @param AdvertisementQuery
	 * */
	public void queryBypage(AdvertisementQuery query);
	
	/*
	 * 修改广告
	 * @param Advertisement
	 * */
	public ServiceResult editAdvertisement(Advertisement advertisement);
	
	/*
	 * 删除广告
	 * @param Long
	 * */
	public ServiceResult deleteAdvertisement(Long id);
	
	/*
	 * 根据id查询广告
	 * @param Long
	 * */
	public Advertisement selectAdvertisementById(Long id);
	
	/*
	 * 根据广告位code查询所有的广告
	 * @param Long,String
	 * */
	public List<Advertisement> selectListByTypeAndCode(String adPositionCode);
	
	/*
	 * 根据orders和adPositionId查询广告
	 * @param Long
	 * */
	public List<Advertisement> selectAdvertisementByorders(int orders,Long adPositionId);
}
