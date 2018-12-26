package com.hundsun.cprs.commonservice.sms.common.page;

import java.util.List;

import com.github.pagehelper.Page;

public abstract class MyBatisPageHelper {
	
	public static <T> void setPageInfo(Pagination<T> query,List<T> result){
		query.setData(result);
		query.setTotalCount(result==null?0:Long.valueOf(((Page)result).getTotal()).intValue());
	}
}
