
/*==============================================================*/            
/* Author    : 何荣                                              */
/* Date      : 2018-02-08                                      */
/* Notes     : 修改内容如下：                                   */
/* 注:可重复执行
 */
/*==============================================================*/

use cprs_commonservice; 
DROP TABLE IF EXISTS `SMS_MODEL`;
create table SMS_MODEL
(
  id          int(12) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  sms_code    char(96) not null,
  sms_type    char(96),
  sms_content varchar(512),
  status      char(32),
  gmt_create  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  gmt_modify  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




use cprs_commonservice; 
DROP TABLE IF EXISTS `SEND_SMS`;
create table SEND_SMS
(
  id            int(12) not null AUTO_INCREMENT COMMENT '主键ID',
  sms_id        int(12),
  moblie_no     varchar(2000),
  business_type char(32),
  status        char(32),
  is_transmit   char(32),
  operator      char(32),
  fail_reason   char(255),
  gmt_create    timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  gmt_modify    timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  sms_content   varchar(512),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


commit;


