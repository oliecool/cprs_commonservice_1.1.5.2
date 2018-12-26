/*
 * Hundsun Inc.
 * Copyright (c) 2006-2012 All Rights Reserved.
 *
 * Author     :wuly18574
 * Version    :1.0
 * Create Date:2017年11月22日
 */
package com.hundsun.cprs.commonservice.sms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.castor.core.util.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.commonservice.common.redis.dao.BaseRedisDao;
import com.hundsun.cprs.commonservice.common.redis.enums.EnumBusinessDicCode;
import com.hundsun.cprs.commonservice.sms.enums.EnumSmsErrorCode;
import com.hundsun.cprs.commonservice.sms.service.AbstractRemoteSendSmsServiceImpl;
import com.hundsun.cprs.commonservice.sms.service.SendSmsService;
import com.hundsun.cprs.commonservice.sms.utils.DateUtil;

/**
 * @author wuly18574
 * @version $Id: RemoteGTNundsunSmsServiceImpl.java,v 0.1 2017年11月22日 下午4:48:57 Exp $
 */
@Service("remoteSendSmsServiceGTN")
public class RemoteGTNundsunSmsServiceImpl extends AbstractRemoteSendSmsServiceImpl
                                           implements SendSmsService {
    @Value("${send.sms.own_company_id}")
    protected String              own_company_id;

    @Value("${send.sms.company_id}")
    protected String              company_id;

    @Value("${send.sms.businsys_no}")
    protected String              businsys_no;

    @Value("${send.sms.sms_authkey}")
    protected String              sms_authkey;

    @Value("${send.sms.sms_priority}")
    protected String              sms_priority;

    @Value("${send.sms.token.url}")
    protected String              tokenUrl;

    @Value("${send.sms.key}")
    protected String              key;

    @Value("${send.sms.secret}")
    protected String              secret;

    @Value("${send.sms.encode}")
    protected String              encode;

    @Value("${send.sms.host}")
    protected String              host;
    
    protected static String accessToken;

	@Autowired
	private BaseRedisDao<String> stringRedisDao;
	
	
    @Override
    protected Map<String, String> getResult(JSONObject resultJsonObject) {
        Map<String, String> httpResult = new HashMap<String, String>();
        try {
            JSONArray ja = (JSONArray) resultJsonObject.get("data");
            JSONObject json = (JSONObject) ja.get(0);
            String smsid = String.valueOf(json.get("sms_id"));
            String time = DateUtil.convertDateToString("yyyyMMdd", new Date());
            if (smsid != null && smsid.indexOf(time) >= 0) {
                //0表示成功
                httpResult.put("code", "0");
                return httpResult;
            } else {
                httpResult.put("code", smsid);
                if (EnumSmsErrorCode.indexByValue(smsid) != null) {
                    httpResult.put("msg", EnumSmsErrorCode.indexByValue(smsid));
                } else {
                    httpResult.put("msg", smsid);
                }
            }
        } catch (JSONException e) {
            log.error("获取返回参数出错", e);
            httpResult.put("code", resultJsonObject.getString("error"));
            httpResult.put("msg", resultJsonObject.getString("error_description"));
        }
        return httpResult;
    }

    @Override
    protected List<BasicNameValuePair> getParam(String smsContent, String phone) {
    	 List<BasicNameValuePair> parameList = new ArrayList<BasicNameValuePair>();
    	 
     	try {
     		String getAccessToken = stringRedisDao.getValueByKey(EnumBusinessDicCode.TOKEN_DAY_GET.getKey());
     	        parameList.add(new BasicNameValuePair("mobile_tel", phone));
     	        parameList.add(new BasicNameValuePair("sms_content", smsContent));
     	        parameList.add(new BasicNameValuePair("own_company_id", own_company_id));
     	        parameList.add(new BasicNameValuePair("company_id", company_id));
     	        parameList.add(new BasicNameValuePair("businsys_no", businsys_no));
     	        parameList.add(new BasicNameValuePair("sms_priority", "5"));
     	        parameList.add(new BasicNameValuePair("sms_authkey", sms_authkey));
     	        parameList.add(new BasicNameValuePair("access_token", getAccessToken));
     	        parameList.add(new BasicNameValuePair("sms_priority", sms_priority));
 			
 		} catch (Exception e) {
 			log.error("从redis中获取令牌异常！",e);
 		}
        
         return parameList;
    }

    @Override
    public void getAccessToken() {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.protocol.content-charset", encode);

        HttpPost httpPost = new HttpPost(tokenUrl);

        List<BasicHeader> headerList = new ArrayList<BasicHeader>();
        char[] authorization = Base64Encoder.encode((key + ":" + secret).getBytes());
        headerList.add(new BasicHeader("Authorization", "Basic " + String.valueOf(authorization)));
        headerList
            .add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encode));
        for (BasicHeader header : headerList) {
            httpPost.addHeader(header);
        }
        List<BasicNameValuePair> paramList = new ArrayList<BasicNameValuePair>();
        paramList.add(new BasicNameValuePair("grant_type", "client_credentials"));
        paramList.add(new BasicNameValuePair("host", host));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, HTTP.UTF_8);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = getResultJson(httpEntity);
            if(StringUtils.isEmpty(jsonObject.getString("access_token"))) {
            	log.error("获取短信令牌失败,短信令牌为空");
            }else {
            	accessToken = jsonObject.getString("access_token");
            	log.info("accessToken:"+accessToken);
            	log.info("获取短信令牌成功！");
            	try {
            		stringRedisDao.updateValueByKey(EnumBusinessDicCode.TOKEN_DAY_GET.getKey(), accessToken);
				} catch (Exception e) {
					log.warn("获取短信令牌成功后存储Redis信息处理失败！", e);
				}
            }
        } catch (Exception e) {
            log.error("send sms getAccessToken error", e);
        }
    }

}
