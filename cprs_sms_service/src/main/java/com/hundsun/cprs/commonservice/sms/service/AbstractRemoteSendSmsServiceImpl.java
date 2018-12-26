package com.hundsun.cprs.commonservice.sms.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.commonservice.sms.common.ServiceResult;
import com.hundsun.cprs.commonservice.sms.dao.SendSmsDAO;
import com.hundsun.cprs.commonservice.sms.dao.SmsModelDAO;
import com.hundsun.cprs.commonservice.sms.domain.SendSms;
import com.hundsun.cprs.commonservice.sms.domain.SmsModel;
import com.hundsun.cprs.commonservice.sms.enums.EnumErrorLog;
import com.hundsun.cprs.commonservice.sms.enums.EnumSendSmsStatus;
import com.hundsun.jresplus.common.util.StringUtil;
import com.hundsun.network.uc.client.req.UserRequest;
import com.hundsun.network.uc.client.service.RemoteUcService;

public abstract class AbstractRemoteSendSmsServiceImpl implements RemoteSendSmsServiceType {

	protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
    protected RemoteUcService remoteUcService;
    @Autowired
    protected SendSmsDAO      sendSmsDAO;
    @Autowired
    protected SmsModelDAO     smsModelDAO;

    @Value("${send.sms.url}")
    protected String          url;
    @Value("${send.sms.encode}")
    protected String          encode;

    protected ServiceResult sendSms(final String sendPhone, final String smsCode,
                                    final Map<String, String> params, final Long userId, final String date) {
        ServiceResult result = new ServiceResult();
        String phone = sendPhone;
        JSONObject resultJsonObject = null;
        try {
            HttpResponse response = null;
            SendSms record = new SendSms();

            log.info(
                "发送短信请求参数：phone=" + phone + " smsCode=" + smsCode + " userId=" + userId + "date=" + date);
            if (StringUtil.isEmpty(smsCode) && (StringUtil.isEmpty(phone) && null == userId)) {
                result.setErrorInfo(EnumErrorLog.PARAMETER_ERROR.getName());
                result.setErrorNO(-999);
                return result;
            }

            // 取短信模板信息
            SmsModel model = this.smsContentReplace(smsCode, params);
            if (null != model) {
                record.setSmsContent(model.getSmsContent());// 短信模板内容
            } else {
                log.error(EnumErrorLog.SMS_MODEL_ERROR.getName());
                result.setErrorInfo(EnumErrorLog.SMS_MODEL_ERROR.getName());
                result.setErrorNO(-999);
                return result;
            }
            // 传入参数手机号码不为空，则取传入手机号码，否则根据userId取用户手机号
            if (StringUtil.isNotEmpty(phone)) {
                record.setMoblieNo(phone);
            } else {
                UserRequest req = new UserRequest();
                try {
                    req.setUserId(userId);
                    req.setParentId(-1L);
                    req.setIsDetail("1");
                    List<Map<String, String>> listuser = remoteUcService.getUsersList(req);
                    Map<String, String> map = (Map<String, String>) listuser.get(0);

                    phone = map.get("cellPhone");
                    if (StringUtil.isEmpty(phone)) {
                        log.error(EnumErrorLog.PHONE_ERROR.getName());
                        result.setErrorInfo(EnumErrorLog.PHONE_ERROR.getName());
                        result.setErrorNO(-999);
                        return result;
                    }
                    record.setMoblieNo(phone);
                } catch (Exception e) {
                    record.setStatus(EnumSendSmsStatus.FAIL.getValue());
                    sendSmsDAO.insert(record);
                    log.error(EnumErrorLog.UC_PHONE_ERROR.getName() + e);
                    result.setErrorInfo(EnumErrorLog.UC_PHONE_ERROR.getName());
                    result.setErrorNO(-999);
                    return result;
                }
            }
            HttpClient httpClient = new DefaultHttpClient();
            httpClient.getParams().setParameter("http.protocol.content-charset", encode);

            //子类实现参数配置
            List<BasicNameValuePair> parameList = getParam(model.getSmsContent(), phone);

            HttpPost httpPost = new HttpPost(url);

            List<BasicHeader> headerList = new ArrayList<BasicHeader>();
            headerList
                .add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encode));
            for (BasicHeader header : headerList) {
                httpPost.addHeader(header);
            }

            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameList, HTTP.UTF_8);
                httpPost.setEntity(entity);

                response = httpClient.execute(httpPost);
                HttpEntity httpEntity = response.getEntity();
                if (log.isInfoEnabled()) {
                    log.info("response.getEntity().getContentLength()=[" + httpEntity.getContentLength()
                             + "]response.getEntity().getContent()=[" + httpEntity.getContent() + "]");
                }
                resultJsonObject = getResultJson(httpEntity);

                //子类实现参数配置
                Map<String, String> httpResult = getResult(resultJsonObject);

                String sResult = httpResult.get("code");
                String resulttext = httpResult.get("msg");
                if (log.isInfoEnabled()) {
                    log.info("返回code-------:" + sResult);
                    log.info("返回Msg-------:" + resulttext);
                }
                sResult = sResult.trim();
                //				if(log.isInfoEnabled()){
                //				    log.info("使用trim方法后的res::"+sResult);
                //				}
                //				Document dom = DocumentHelper.parseText(res);
                //				Element root = dom.getRootElement();
                //				String sResult = root.element("result").getText();
                //				String resulttext = root.element("result-text").getText();

                if (sResult.equals("0")) {
                    log.info("短信发送成功...result:" + EnumSendSmsStatus.SUCCESS.getValue());
                    record.setStatus(EnumSendSmsStatus.SUCCESS.getValue());
                    record.setFailReason(resulttext);
                    sendSmsDAO.insert(record);
                } else {
                    record.setStatus(EnumSendSmsStatus.FAIL.getValue());
                    record.setFailReason(resulttext);
                    sendSmsDAO.insert(record);
                    log.error("result:" + result + "result-text" + resulttext);
                    result.setErrorInfo(resulttext);
                    result.setErrorNO(-999);
                }
                return result;
            } catch (Exception e) {
                record.setStatus(EnumSendSmsStatus.FAIL.getValue());
                sendSmsDAO.insert(record);
                log.error("短信发送异常," + resultJsonObject.getString("error_info"), e);
                result.setErrorInfo(EnumSendSmsStatus.FAIL.getName());
                result.setErrorNO(-999);
                return result;

            } finally {
                httpClient.getConnectionManager().shutdown();
            }
        } catch (Exception e) {
            log.error("send sms fail!", e);
        }
        return result;
    }

    protected JSONObject getResultJson(HttpEntity httpEntity) {
        if (httpEntity != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
                StringBuilder entityStringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    entityStringBuilder.append(line);
                }
                // 利用从HttpEntity中得到的String生成JsonObject
                return JSONArray.parseObject(entityStringBuilder.toString());
            } catch (Exception e) {
                log.error("", e);
            }
        }
        return null;
    }

    protected abstract Map<String, String> getResult(JSONObject resultJsonObject);

    protected abstract List<BasicNameValuePair> getParam(String smsContent, String phone);
    
    @Override
    public Map<String, String> toUserSendSms(final String sendPhone, final String smsCode,
                                             final Map<String, String> params, final Long userId,
                                             final String date) {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("0", "");
        map.put("errorNo", null);
        map.put("errorInfo", null);
        try {
            new Thread() {
                @Override
                public void run() {
                    ServiceResult result = sendSms(sendPhone, smsCode, params, userId, date);
                    map.put("errorNo", result.getErrorInfo());
                    map.put("errorInfo", result.getErrorInfo());
                }
            }.start();
        } catch (Exception e) {
            log.error("toUserSendSms fail:", e);
        }
        return map;
    }

    /*
     * 把短信模板里的通配符替换成传入的参数
     */
    protected SmsModel smsContentReplace(String smsCode, Map<String, String> params) {
        SmsModel model = smsModelDAO.selectBySmsCode(smsCode);
        if (null != model) {
            String content = model.getSmsContent();
            String stringarray[] = model.getSmsContent().split("#");
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    for (String string : stringarray) {
                        if (param.getKey().equals(string)) {
                            content = content.replace("#" + string + "#", param.getValue());
                        }
                    }
                }
            }
            model.setSmsContent(content);
        }
        return model;
    }

    public RemoteUcService getRemoteUcService() {
        return remoteUcService;
    }

    public void setRemoteUcService(RemoteUcService remoteUcService) {
        this.remoteUcService = remoteUcService;
    }

    public SendSmsDAO getSendSmsDAO() {
        return sendSmsDAO;
    }

    public void setSendSmsDAO(SendSmsDAO sendSmsDAO) {
        this.sendSmsDAO = sendSmsDAO;
    }

    public SmsModelDAO getSmsModelDAO() {
        return smsModelDAO;
    }

    public void setSmsModelDAO(SmsModelDAO smsModelDAO) {
        this.smsModelDAO = smsModelDAO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

}
