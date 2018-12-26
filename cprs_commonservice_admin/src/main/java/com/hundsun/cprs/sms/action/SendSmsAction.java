package com.hundsun.cprs.sms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.commonservice.sms.domain.SendSms;
import com.hundsun.cprs.commonservice.sms.domain.query.SendQuery;
import com.hundsun.cprs.commonservice.sms.enums.EnumSendSmsStatus;
import com.hundsun.cprs.commonservice.sms.service.SendService;
@Controller
public class SendSmsAction extends BaseAction{
	@Autowired
	private SendService sendService;
	
	@RequestMapping("smsRecord/list.htm")
	public String queryByPage(ModelMap model,@ModelAttribute("query")SendQuery query){
		sendService.querySendSmsByPage(query);
		model.addAttribute("EnumSendSmsStatusMap", EnumSendSmsStatus.toMap());
		return "sms/smsRecord/list";
	}
	
	@RequestMapping("smsRecord/view.htm")
	public String view(ModelMap model,@RequestParam("id")String id){
		SendSms sendSms=sendService.selectSmsById(Long.parseLong(id));
		model.addAttribute("sendSms", sendSms);
		model.addAttribute("EnumSendSmsStatusMap", EnumSendSmsStatus.toMap());
		return "sms/smsRecord/view";
	}
}
