package com.hundsun.cprs.commonservice.advertisement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdvertisementQuery;

public interface AdvertisementDao {
	/*
	 * 新增广告
	 * @param Advertisement
	 * */
	public void createAdvertisement(Advertisement advertisement);
	
	/*
	 * 分页查询广告
	 * @param AdvertisementQuery
	 * */
	public List<Advertisement> queryBypage(AdvertisementQuery query);
	
	/*
	 * 修改广告
	 * @param Advertisement
	 * */
	public void editAdvertisement(Advertisement advertisement);
	
	/*
	 * 删除广告
	 * @param Long
	 * */
	public void deleteAdvertisement(Long id);
	
	/*
	 * 根据id查询广告
	 * @param Long
	 * */
	public Advertisement selectAdvertisementById(Long id);
	
	/*
	 * 根据广告位code查询所有的广告
	 * @param String
	 * */
	public List<Advertisement> selectListByTypeAndCode(Long adPositionId);
	
	/*
	 * 根据orders和adPositionId查询广告
	 * @param Long
	 * */
	public List<Advertisement> selectAdvertisementByorders(@Param("orders")int orders,@Param("adPositionId")Long adPositionId);
}
