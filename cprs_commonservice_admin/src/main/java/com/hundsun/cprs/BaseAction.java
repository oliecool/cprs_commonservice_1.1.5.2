package com.hundsun.cprs;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Action基类
 * 
 * @author zhengdd
 * @version $Id: BaseAction.java,v 0.1 2010-3-19 下午03:30:44 zhengdd Exp $
 */
@Controller
public abstract class BaseAction {
    // 日志
    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * 初始化绑定
     * 
     * @param binder
     */
    @InitBinder
    protected final void initBinderInternal(WebDataBinder binder) {

        registerDefaultCustomDateEditor(binder);
        registerDefaultCustomNumberEditor(binder);
        initBinder(binder);
    }

    private void registerDefaultCustomNumberEditor(WebDataBinder binder) {

        // 注册双精度数字格式化类型: #0.00
        NumberFormat numberFormat = new DecimalFormat("#0.00");
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, numberFormat, true));
    }

    protected void registerDefaultCustomDateEditor(WebDataBinder binder) {

        // 注册默认的日期格式化类型: yyyy-MM-dd
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 提供子类初始化表单, 子类如果要调用请重写该方法
     * 
     * @param binder
     */
    protected void initBinder(WebDataBinder binder) {
    	
    }
    
    protected String error(ModelMap model, String message) {
		model.addAttribute("message", message);
		return "error";
	}
    
    protected String success(ModelMap model, String message) {
    	return success(model,message,null);
	}
    
    protected String success(ModelMap model, String message,String redirectUrl) {
		model.addAttribute("message", message);
		if(redirectUrl != null)
			model.addAttribute("url", redirectUrl);
		return "success";
	}
    
    
}
