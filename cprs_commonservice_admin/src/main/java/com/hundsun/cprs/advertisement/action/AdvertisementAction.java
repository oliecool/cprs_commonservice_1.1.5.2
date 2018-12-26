package com.hundsun.cprs.advertisement.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionJson;
import com.hundsun.cprs.commonservice.advertisement.service.AdpositionJsonService;
import com.hundsun.cprs.commonservice.plate.domain.PlateJson;
import com.hundsun.cprs.commonservice.plate.enums.EnumIsDeleteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.util.FileUploadUtil;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdvertisementQuery;
import com.hundsun.cprs.commonservice.advertisement.enums.EnumAdvertisementType;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;
import com.hundsun.cprs.commonservice.advertisement.service.AdvertisementService;

@Controller
public class AdvertisementAction extends BaseAction{
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private AdPositionService adPositionService;
	@Autowired
	private AdpositionJsonService adpositionJsonService;
	@Resource(name = "imageUploadUtil")
	private FileUploadUtil fileUploadUtil;
	
	@RequestMapping("advertisement/list.htm")
	public String queryPage(ModelMap model,@ModelAttribute("query")AdvertisementQuery query){
		return "advertisement/listIndex";
	}

	@RequestMapping("advertisement/advertisementLeft")
	public String getLeftPage(ModelMap model){
		return "advertisement/leftIndex";
	}

	@RequestMapping("advertisement/helpIndex")
	public String gethelpPage(ModelMap model){
		return "advertisement/helpIndex";
	}

	@RequestMapping("advertisement/advertisementRight")
	public String getRightPage(ModelMap model){
		return "advertisement/rightIndex";
	}

	@RequestMapping("advertisement/addWhat")
	public String addWhat(ModelMap model,@RequestParam("id")String id){
		model.addAttribute("adpositionId",id);
		return "advertisement/switchModel";
	}
	/**
	 * 广告位二级分类树的展示
	 * @return
	 */
	@RequestMapping(value = "/ajax/getAdpositionTypeTree")
	public @ResponseBody List<AdPositionJson> getAdpositionTypeTree() {
		return adpositionJsonService.getAdPositionJson();
	}

	@RequestMapping(value = "/view/advertisementList")
	public String viewAdvertisementList(ModelMap model, @ModelAttribute("query")AdvertisementQuery query){
		if(query.getTitle()!=null){
			query.setTitle(query.getTitle().trim());
		}
		List<AdPosition> AdPositionList=adPositionService.selectAdPositionList();
		model.addAttribute("AdPositionList", AdPositionList);
		model.addAttribute("AdvertisementTypeMap",EnumAdvertisementType.toMap());
		advertisementService.queryBypage(query);
		return "advertisement/list";
	}
	
	@RequestMapping(value="advertisement/add.htm",method=RequestMethod.GET)
	public void addInit(ModelMap model,@RequestParam("adpositionId")String adpositionId){
		//List<AdPosition> AdPositionList=adPositionService.selectAdPositionList();
		//model.addAttribute("AdPositionList", AdPositionList);
		AdPosition adPosition = adPositionService.selectAdPositionById(Long.parseLong(adpositionId));
		model.addAttribute("adposition",adPosition);
		model.addAttribute("AdvertisementTypeMap",EnumAdvertisementType.toMap());
	}
	
	@RequestMapping(value="advertisement/add.htm",method=RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("advertisement")Advertisement advertisement,@RequestParam(value = "file", required = false)MultipartFile file){
		if(file.getOriginalFilename()!=""){
			String path=fileUploadUtil.uploadFile(file, "advertisement");
			advertisement.setPath(path);
		}
		ServiceResult result=advertisementService.createAdvertisement(advertisement);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			AdvertisementQuery query = new AdvertisementQuery();
			query.setAdPositionId(advertisement.getAdPositionId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/view/advertisementList");
			model.addAttribute("message", "广告添加成功");
			return "success";
		}	
	}
	
	@RequestMapping(value="advertisement/delete.htm")
	public String delete(ModelMap model,@RequestParam("id")String id){
		Advertisement advertisement = advertisementService.selectAdvertisementById(Long.parseLong(id));
		ServiceResult result=advertisementService.deleteAdvertisement(Long.parseLong(id));
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			AdvertisementQuery query = new AdvertisementQuery();
			query.setAdPositionId(advertisement.getAdPositionId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/view/advertisementList");
			model.addAttribute("message", "广告删除成功");
			return "success";
		}	
	}
	
	@RequestMapping(value="advertisement/edit.htm",method=RequestMethod.GET)
	public void editInit(ModelMap model,@RequestParam("id")String id){
		List<AdPosition> AdPositionList=adPositionService.selectAdPositionList();
		model.addAttribute("AdPositionList", AdPositionList);
		model.addAttribute("AdvertisementTypeMap",EnumAdvertisementType.toMap());
		Advertisement advertisement=advertisementService.selectAdvertisementById(Long.parseLong(id));
		model.addAttribute("advertisement", advertisement);
	}
	
	@RequestMapping(value="advertisement/edit.htm",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("advertisement")Advertisement advertisement,@RequestParam("oldAdPositionId")String oldAdPositionId,@RequestParam(value = "file", required = false)MultipartFile file){
		if(file != null){
			String path=fileUploadUtil.uploadFile(file, "advertisement");
			advertisement.setPath(path);
		}
		if(advertisement.getBeginDate().after(advertisement.getEndDate())){
			model.addAttribute("message", "修改失败，广告开始时间不能大于结束时间");
			return "error";
		}
		ServiceResult result=advertisementService.editAdvertisement(advertisement);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			AdvertisementQuery query = new AdvertisementQuery();
			query.setAdPositionId(Long.parseLong(oldAdPositionId));
			model.addAttribute("query", query);
			model.addAttribute("url", "/view/advertisementList");
			model.addAttribute("message", "广告修改成功");
			return "success";
		}	
	}
	
	@RequestMapping(value="advertisement/view.htm")
	public void view(ModelMap model,@RequestParam("id")String id){
		Advertisement advertisement=advertisementService.selectAdvertisementById(Long.parseLong(id));
		AdPosition adPosition=adPositionService.selectAdPositionById(advertisement.getAdPositionId());
		model.addAttribute("adPosition", adPosition);
		model.addAttribute("advertisement", advertisement);
		model.addAttribute("AdvertisementTypeMap",EnumAdvertisementType.toMap());
	}
	
	/*
	 * 异步校验广告位编码是否重复
	 * 
	 * */
	@RequestMapping(value = "/advertisement/ajax/validateOrders.htm")
	public void validateGoodsCode(@RequestParam("orders") String orders,@RequestParam("adPositionId") String adPositionId,ModelMap model, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		List<Advertisement> advertisementList=advertisementService.selectAdvertisementByorders(Integer.parseInt(orders),Long.parseLong(adPositionId));
		if(advertisementList.isEmpty()){
			json.put("info", "Yes");
		}else{
			json.put("info", "No");
		}			
		try {
			response.getWriter().print(json);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	@Override
	protected void initBinder(WebDataBinder binder) {
		// 注册默认的日期格式化类型: yyyy-MM-dd
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
