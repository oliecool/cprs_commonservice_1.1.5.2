package com.hundsun.cprs.commonservice.sms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.commonservice.sms.service.AbstractRemoteSendSmsServiceImpl;
import com.hundsun.cprs.commonservice.sms.utils.DateUtil;
import com.hundsun.cprs.commonservice.sms.enums.EnumSmsErrorCode;

@Service("remoteSendSmsServiceHS")
public class RemoteHundsunSmsServiceImpl extends
		AbstractRemoteSendSmsServiceImpl {
	
	@Value("${send.sms.own_company_id}")
	protected String own_company_id;	

	@Value("${send.sms.company_id}")
	protected String company_id;
	
	@Value("${send.sms.businsys_no}")
	protected String businsys_no;
	
	@Value("${send.sms.sms_authkey}")
	protected String sms_authkey;
	

	@Override
	protected Map<String, String> getResult(JSONObject resultJsonObject) {
		Map<String, String> httpResult = new HashMap<String, String>();
		try {
			JSONArray ja = (JSONArray) resultJsonObject.get("data");
			JSONObject json = (JSONObject) ja.get(0);			
			String smsid = String.valueOf(json.get("sms_id"));
			String time = DateUtil.convertDateToString("yyyyMMdd", new Date());
			if(smsid !=null && smsid.indexOf(time)>=0){
				//0表示成功
				httpResult.put("code","0");	
				return httpResult;
			}else{
				httpResult.put("code",smsid);
				if(EnumSmsErrorCode.indexByValue(smsid)!=null){
					httpResult.put("msg",EnumSmsErrorCode.indexByValue(smsid));
				}else{
					httpResult.put("msg",smsid);
				}				
			}
		} catch (JSONException e) {
			log.error("获取返回参数出错",e);
			httpResult.put("code", "1111");
			httpResult.put("msg", "获取返回参数出错");
		}		
		return httpResult;
	}

	@Override
	protected List<BasicNameValuePair> getParam(String smsContent, String phone) {
		List<BasicNameValuePair> parameList = new ArrayList<BasicNameValuePair>();
		parameList.add(new BasicNameValuePair("mobile_tel", phone));
		parameList.add(new BasicNameValuePair("sms_content", smsContent));
		parameList.add(new BasicNameValuePair("own_company_id", own_company_id));
		parameList.add(new BasicNameValuePair("company_id", company_id));
		parameList.add(new BasicNameValuePair("businsys_no", businsys_no));
		parameList.add(new BasicNameValuePair("sms_priority", "5"));
		parameList.add(new BasicNameValuePair("sms_authkey", sms_authkey));
		return parameList;
	}

	public String getOwn_company_id() {
		return own_company_id;
	}

	public void setOwn_company_id(String own_company_id) {
		this.own_company_id = own_company_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getBusinsys_no() {
		return businsys_no;
	}

	public void setBusinsys_no(String businsys_no) {
		this.businsys_no = businsys_no;
	}

	public String getSms_authkey() {
		return sms_authkey;
	}

	public void setSms_authkey(String sms_authkey) {
		this.sms_authkey = sms_authkey;
	}

	
}
