package com.hundsun.cprs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.advertisement.response.AdPositionResponse;
import com.hundsun.cprs.advertisement.response.AdvertisementResponse;
import com.hundsun.cprs.advertisement.service.RemoteAdvertisementService;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;

@Controller
public class TestsAction {
	@Autowired
	private AdPositionService newsService;
	@Autowired
	private RemoteAdvertisementService remoteAdvertisementService;
	
	@RequestMapping("test/hah.htm")
	public String test(ModelMap model){
		/*AdPosition news = new AdPosition();
		news.setAdPositionTypeId(2L);
		news.setCode("600518");
		news.setName("首页-轮播图");
		news.setTemplate("dhjksahdkjhasjhkdhsa");
		news.setSystemtype("0");
		newsService.createNews(news);*/
		return "success";
	}
	
	@RequestMapping("T2/test.htm")
	public String testT2(ModelMap model){
		AdPositionRequest request = new AdPositionRequest();
		request.setAdPositionTypeCode("112233");		
		try{
			AdPositionResponse response=remoteAdvertisementService.getAdPositionList(request);
			AdPositionResponse response2=remoteAdvertisementService.getAdPosition("600518");
			AdvertisementResponse reponse3=remoteAdvertisementService.getAdListByAdPositionCode("114477");
			AdvertisementResponse response4=remoteAdvertisementService.getAdvertisement(2L);
			System.out.println(response4.getAdvertisementInfoVo());
		}catch (ServiceCodeException e) {
			model.addAttribute("message",e.getErrorDesc());
			return "error";
		}
		return "success";
	}
	
}
