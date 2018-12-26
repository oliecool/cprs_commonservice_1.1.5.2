
/*==============================================================*/            
/* Author    : jhn                                             */
/* Date      : 2018-05-29                                     */
/* Notes     : 修改内容如下：                                   */
/* 注:不可重复执行
   作用：1.为省市区设置地域编码
		
 */
/*==============================================================*/

 
alter table commons_plate  add plate_code varchar (100) not null  COMMENT '地域编码' ;



alter table trade_article  add article_remark varchar (200) not null  COMMENT '文章简介' ;
alter table trade_article  add logo_Image_Url varchar (200) not null  COMMENT '文章logo图片路径' ;


ALTER TABLE commons_plate ADD UNIQUE  plate_code ;

commit;