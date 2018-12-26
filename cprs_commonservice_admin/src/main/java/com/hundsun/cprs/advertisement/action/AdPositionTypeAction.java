package com.hundsun.cprs.advertisement.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hundsun.cprs.advertisement.request.AdPositionRequest;
import com.hundsun.cprs.common.enums.EnumBussinessCode;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;
import com.sun.corba.se.pept.broker.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionType;
import com.hundsun.cprs.commonservice.advertisement.domain.query.AdPositionTypeQuery;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionTypeService;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;
@Controller
public class AdPositionTypeAction extends BaseAction{
	
	@Autowired
	private AdPositionTypeService adPositionTypeService;

	@Autowired
	private AdPositionService  adPositionService;
	
	@RequestMapping("adPositionType/list.htm")
	public void queryBypage(ModelMap model,@ModelAttribute("query")AdPositionTypeQuery query){
		if(query.getName()!=null){
			query.setName(query.getName().trim());
		}
		adPositionTypeService.queryByPage(query);
	}
	
	@RequestMapping(value="adPositionType/add.htm", method=RequestMethod.GET)
	public String addInit(ModelMap model){
		return "adPositionType/add";
	}
	
	@RequestMapping(value="adPositionType/add.htm",method=RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("adPositionType")AdPositionType adPositionType){
		ServiceResult result=adPositionTypeService.createAdPositionType(adPositionType);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/advertisement/helpIndex");
			model.addAttribute("message", "广告位分类添加成功");
			return "success";
		}		
	}
	
	@RequestMapping(value="adPositionType/edit.htm", method=RequestMethod.GET)
	public String editInit(ModelMap model,@RequestParam("id")String id){
		AdPositionType adPositionType=adPositionTypeService.selectAdPositionTypeById(Long.parseLong(id));
		model.addAttribute("adPositionType", adPositionType);
		return "adPositionType/edit";
	}
	
	@RequestMapping(value="adPositionType/edit.htm",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("adPositionType")AdPositionType adPositionType){
		ServiceResult result=adPositionTypeService.editAdPositionType(adPositionType);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/advertisement/helpIndex");
			model.addAttribute("message", "广告位分类修改成功");
			return "success";
		}		
	}


	/**
	 * 异步查询广告分类夏是否有广告位存在
	 * @return
	 */
	@RequestMapping(value="ajax/existAdposition.json")
	public @ResponseBody
	JSONObject existAdposition(@RequestParam("adpositionTypeCode") String adpositionTypeCode){
		JSONObject obj = new JSONObject();
		try {

			AdPositionRequest  request = new AdPositionRequest();
			request.setAdPositionTypeCode(adpositionTypeCode);

			List<AdPosition> list = adPositionService.selectAdPositionBycodeOrType(request);
			if(list != null && list.size() > 0){
				obj.put("message", "广告分类下面还存在广告位");
				return obj;
			}
		} catch (Exception e) {
			log.error("查询广告分类信息失败！",e);
			obj.put("message", "查询广告分类信息失败！");
		}
		return obj;
	}



	@RequestMapping(value="adPositionType/delete.htm")
	public String delete(ModelMap model,@RequestParam("id")String id){
		AdPositionType adPositionType=adPositionTypeService.selectAdPositionTypeById(Long.parseLong(id));
		//先校验是否为系统创建分类，系统分类不允许用户删除
		//再校验该分类下是否存在内容,有内容的分类不允许删除
		if(adPositionType!=null){
			if(adPositionType.getDefinyType().equals(EnumDefinyType.SYSTEM_TYPE.getType())){
				model.addAttribute("message", EnumBussinessCode.SYSTEMTYPE_DELETE_FAILED.getValue());
				return "error";
			}
			List<AdPosition> adPositionList= adPositionService.selectAdPositionByAdPositionTypeId(adPositionType.getCode());
			if(adPositionList != null && adPositionList.size() > 0){
				model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_DELETE_FAILED.getValue());
				return "error";
			}
		}
		ServiceResult result=adPositionTypeService.deleteAdPositionType(Long.parseLong(id));
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/advertisement/helpIndex");
			model.addAttribute("message", "广告位分类删除成功");
			return "success";
		}	
	}

	@RequestMapping(value = "adPositionType/view.htm")
	public String viewAdpositionType(ModelMap model,@RequestParam("id")String id){
		AdPositionType adPositionType = adPositionTypeService.selectAdPositionTypeById(Long.parseLong(id));
		model.addAttribute("adPositionType",adPositionType);
		return "adPositionType/view";
	}
	/*
	 * 异步校验广告位编码是否重复
	 * 
	 * */
	@RequestMapping(value = "/adPositionType/ajax/validateCode.htm")
	public void validateGoodsCode(@RequestParam("code") String code,ModelMap model, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		List<AdPositionType> adPositionList=adPositionTypeService.selectAdPositionTypeByCode(code);
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
