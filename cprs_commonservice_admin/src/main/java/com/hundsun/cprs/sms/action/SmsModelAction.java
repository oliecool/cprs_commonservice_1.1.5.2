package com.hundsun.cprs.sms.action;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.commonservice.sms.common.ServiceResult;
import com.hundsun.cprs.commonservice.sms.common.exception.ServiceCodeException;
import com.hundsun.cprs.commonservice.sms.domain.SmsModel;
import com.hundsun.cprs.commonservice.sms.domain.query.ModelQuery;
import com.hundsun.cprs.commonservice.sms.enums.EnumSmsModelStatus;
import com.hundsun.cprs.commonservice.sms.service.SmsModelService;
@Controller
public class SmsModelAction extends BaseAction{
	@Autowired
	private SmsModelService smsModelService;
	
	@RequestMapping("smsModel/list.htm")
	public String queryByPage(ModelMap model,@ModelAttribute("query")ModelQuery query){
		smsModelService.querySmsModelByPage(query);
		model.addAttribute("EnumSmsModelStatusMap", EnumSmsModelStatus.toMap());
		return "sms/smsModel/list";
	}
	
	@RequestMapping("smsModel/view.htm")
	public String view(ModelMap model,@RequestParam("id")String id){
		SmsModel smsModel=smsModelService.selectSmsModelByPrimaryKey(Long.parseLong(id));
		model.addAttribute("smsModel", smsModel);
		model.addAttribute("EnumSmsModelStatusMap", EnumSmsModelStatus.toMap());
		return "sms/smsModel/view";
	}
	
	@RequestMapping(value="smsModel/add.htm",method=RequestMethod.GET)
	public String addInit(ModelMap model){
		model.addAttribute("EnumSmsModelStatusMap", EnumSmsModelStatus.toMap());
		return "/sms/smsModel/add";
	}
	
	@RequestMapping(value="smsModel/add.htm",method=RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("smsModel")SmsModel smsModel){
		try{
			ServiceResult result=smsModelService.createSmsModel(smsModel);
			if(result.getErrorNO()!=null){
				model.addAttribute("message", "新建模板失败,"+result.getErrorInfo());
				return "error";
			}
		}catch(ServiceCodeException e){
			log.error(e);
			model.addAttribute("message", "新建模板失败,"+e.getErrorDesc());
			return "error";
		}
		model.addAttribute("url", "/smsModel/list");
		model.addAttribute("message", "新建模板成功");
		return "success";
	}
	
	@RequestMapping(value="smsModel/edit.htm",method=RequestMethod.GET)
	public String editInit(ModelMap model,@RequestParam("id") String id){
		model.addAttribute("EnumSmsModelStatusMap", EnumSmsModelStatus.toMap());
		SmsModel smsModel=smsModelService.selectSmsModelByPrimaryKey(Long.parseLong(id));
		model.addAttribute("smsModel", smsModel);
		return "/sms/smsModel/edit";
	}
	
	@RequestMapping(value="smsModel/edit.htm",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("smsModel")SmsModel smsModel){
		try{
			ServiceResult result=smsModelService.editSmsModel(smsModel);
			if(result.getErrorNO()!=null){
				model.addAttribute("message", "编辑模板失败,"+result.getErrorInfo());
				return "error";
			}
		}catch(ServiceCodeException e){
			log.error(e);
			model.addAttribute("message", "编辑模板失败,"+e.getErrorDesc());
			return "error";
		}
		model.addAttribute("url", "/smsModel/list");
		model.addAttribute("message", "编辑模板成功");
		return "success";
	}
	
	@RequestMapping("smsModel/delete.htm")
	public String delete(ModelMap model,@RequestParam("id")String id){
		try{
			smsModelService.deleteSmsModel(Long.parseLong(id));
		}catch(ServiceCodeException e){
			model.addAttribute("message", e.getErrorDesc());
			return "error";
		}
		model.addAttribute("url", "/smsModel/list");
		model.addAttribute("message", "模板删除成功");
		return "success";
	}
	
	/*
	 * 异步校验短信模板编码是否重复
	 * 
	 * */
	@RequestMapping("smsModel/ajax/validateCode.htm")
	public void validateCode(@RequestParam("code")String code,HttpServletResponse response){
		JSONObject json = new JSONObject();
		SmsModel smsMmodel= smsModelService.selectSmsModelByCode(code);
		if(smsMmodel==null){
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
