package com.hundsun.cprs.commonservice.sms.dao;

import java.util.List;

import com.hundsun.cprs.commonservice.sms.domain.SendSms;
import com.hundsun.cprs.commonservice.sms.domain.query.SendQuery;
import com.hundsun.cprs.commonservice.sms.query.SendSmsQuery;



public interface SendSmsDAO {
    int deleteByPrimaryKey(Long id);

    Long insert(SendSms record);

    SendSms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendSms record);

    SendSms selectByQuery(SendSmsQuery sendSmsQuery);

    int updateByPrimaryKey(SendSms record);
    /**
     * 根据参数查询list
     * @param sendSms
     * @return
     */
    List<SendSms> listSendSms(SendSms sendSms);
    
    /*
     * 分页查询已发送短信
     * @param SendQuery
     * @return
     * */
    List<SendSms> querySendSmsByPage(SendQuery query);
}