package com.hundsun.cprs.advertisement.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.common.enums.EnumBussinessCode;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.service.AdvertisementService;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionType;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionQuery;
import com.hundsun.cprs.commonservice.advertisement.enums.EnumSystemType;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionTypeService;
@Controller
public class AdPositionAction extends BaseAction{

	@Autowired
	private AdvertisementService  advertisementService;
	@Autowired
	private AdPositionService adPositionService;
	@Autowired
	private AdPositionTypeService adPositionTypeService;
	
	@RequestMapping("adposition/list.htm")
	public String queryList(ModelMap model,@ModelAttribute("query") AdPositionQuery query){
		if(query.getName()!=null){
			query.setName(query.getName().trim());
		}
		adPositionService.queryAdPositionByPage(query);
		model.addAttribute("systemTypeMap",EnumSystemType.toMap());
		List<AdPositionType> adPositionTypeList=adPositionTypeService.selectAdPositionTypeList();
		model.addAttribute("adPositionTypeList", adPositionTypeList);
		return "adposition/list";
	}
	
	@RequestMapping(value="adposition/add.htm",method=RequestMethod.GET)
	public String addInit(ModelMap model,@RequestParam(value="adpositionTypeId",required = false)String adpositionTypeId){
		if (adpositionTypeId==null){
			List<AdPositionType> adPositionTypeList=adPositionTypeService.selectAdPositionTypeList();
			model.addAttribute("adPositionTypeList", adPositionTypeList);
		}else{
			AdPositionType adPositionType = adPositionTypeService.selectAdPositionTypeById(Long.parseLong(adpositionTypeId));
			model.addAttribute("adpositionType",adPositionType);
		}
		model.addAttribute("systemTypeMap",EnumSystemType.toMap());
		return "adposition/add";
	}
	
	@RequestMapping(value="adposition/add.htm",method=RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("adPosition")AdPosition adPosition){
		try {
			adPositionService.createAdPosition(adPosition);
		} catch (ServiceCodeException e) {
			model.addAttribute("message", e.getMessage());
			return "error";
		}
		model.addAttribute("url", "/advertisement/helpIndex");
		model.addAttribute("message", "广告位添加成功");
		return "success";
	}
	
	@RequestMapping(value="adposition/edit.htm",method=RequestMethod.GET)
	public String editInit(ModelMap model,@RequestParam("id")String id){
		List<AdPositionType> adPositionTypeList=adPositionTypeService.selectAdPositionTypeList();
		model.addAttribute("adPositionTypeList", adPositionTypeList);
		AdPosition adPosition = adPositionService.selectAdPositionById(Long.parseLong(id));
		model.addAttribute("adPosition", adPosition);
		model.addAttribute("systemTypeMap",EnumSystemType.toMap());
		return "adposition/edit";
	}
	
	@RequestMapping(value="adposition/edit.htm",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("adPosition")AdPosition adPosition){
		ServiceResult result=adPositionService.editAdPosition(adPosition);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/advertisement/helpIndex");
			model.addAttribute("message", "广告位修改成功");
			return "success";
		}		
	}

	/**
	 * 异步查询广告分类夏是否有广告位存在
	 * @return
	 */
	@RequestMapping(value="ajax/existAdertisement.json")
	public @ResponseBody
	JSONObject existAdertisement(@RequestParam("adpositionCode") String adpositionCode){
		JSONObject obj = new JSONObject();
		try {

			List<Advertisement> list = advertisementService.selectListByTypeAndCode(adpositionCode);

			if(list != null && list.size() > 0){
				obj.put("message", "广告位下面还存在广告");
				return obj;
			}
		} catch (Exception e) {
			log.error("查询广告位信息失败！",e);
			obj.put("message", "查询广告位信息失败！");
		}
		return obj;
	}


	@RequestMapping(value="adposition/delete.htm")
	public String delete(ModelMap model,@RequestParam("id")String id){
		AdPosition adposition = adPositionService.selectAdPositionById(Long.parseLong(id));
		//先校验是否为系统创建分类，系统分类不允许用户删除
		//再校验该分类下是否存在内容,有内容的分类不允许删除
		if(adposition != null){
			if(adposition.getDefinyType().equals(EnumDefinyType.SYSTEM_TYPE.getType())){
				model.addAttribute("message", EnumBussinessCode.SYSTEMTYPE_DELETE_FAILED.getValue());
				return "error";
			}
			List<Advertisement> list = advertisementService.selectListByTypeAndCode(adposition.getCode());
			if(list != null && list.size() > 0){
				model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_DELETE_FAILED.getValue());
				return "error";
			}
		}
		
		ServiceResult result=adPositionService.deleteAdPosition(Long.parseLong(id));
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/advertisement/helpIndex");
			model.addAttribute("message", "广告位删除成功");
			return "success";
		}	
	}

	@RequestMapping(value = "adposition/view.htm")
	public String view(ModelMap model,@RequestParam("id")String id){
		AdPosition adPosition = adPositionService.selectAdPositionById(Long.parseLong(id));
		model.addAttribute("adposition",adPosition);
		AdPositionType adPositionType = adPositionTypeService.selectAdPositionTypeById(adPosition.getAdPositionTypeId());
		model.addAttribute("adpositionTypeName",adPositionType.getName());
		return "adposition/view";
	}
	/*
	 * 异步校验广告位编码是否重复
	 * 
	 * */
	@RequestMapping(value = "/adPosition/ajax/validateCode.htm")
	public void validateGoodsCode(@RequestParam("code") String code,ModelMap model, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		List<AdPosition> adPositionList=adPositionService.selectAdPositionBycode(code);
		if(adPositionList.isEmpty()){
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
}
