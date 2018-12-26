

/*==============================================================*/            
/* Author    : jhn                                             */
/* Date      : 2018-07-08                                     */
/* Notes     : 修改内容如下：                                   */
/* 注:不可重复执行	
 */
/*==============================================================*/


 alter table trade_article_Type add definy_Type varchar(100) COMMENT '添加方式client 客户  system 系统';
 
 alter table trade_article add definy_Type varchar(100) COMMENT '添加方式client 客户  system 系统';
 
 alter table commonservice_adPosition add definy_Type varchar(100) COMMENT '添加方式client 客户  system 系统';
 
 alter table commonservice_adpositiontype  add definy_Type varchar(100) COMMENT '添加方式client 客户  system 系统';
 
 alter table commonservice_advertisement add definy_Type varchar(100) COMMENT '添加方式client 客户  system 系统';