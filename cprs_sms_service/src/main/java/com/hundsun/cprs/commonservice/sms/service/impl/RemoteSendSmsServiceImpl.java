package com.hundsun.cprs.commonservice.sms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.commonservice.sms.service.AbstractRemoteSendSmsServiceImpl;

@Service("remoteSendSmsServiceYP")
public class RemoteSendSmsServiceImpl extends AbstractRemoteSendSmsServiceImpl {

	@Value("${send.sms.apikey}")
	protected String apikey;
	
	@Override
	protected List<BasicNameValuePair> getParam(String smsContent, String phone) {
		List<BasicNameValuePair> parameList = new ArrayList<BasicNameValuePair>();
		parameList.add(new BasicNameValuePair("apikey", apikey));
		parameList.add(new BasicNameValuePair("text", smsContent));
		parameList.add(new BasicNameValuePair("mobile", phone));
		return parameList;
	}

	@Override
	protected Map<String, String> getResult(JSONObject resultJsonObject) {
		Map<String, String> httpResult = new HashMap<String, String>();
		try {
			httpResult.put("code",resultJsonObject.getString("code"));
			httpResult.put("msg",resultJsonObject.getString("msg"));
		} catch (JSONException e) {
			log.error("获取返回参数出错",e);
			httpResult.put("code", "1111");
			httpResult.put("msg", "获取返回参数出错");
		}		
		return httpResult;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}	

	
}
