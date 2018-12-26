


/*==============================================================*/            
/* Author    : jhn                                             */
/* Date      : 2018-05-23                                     */
/* Notes     : 修改内容如下：                                   */
/* 注:可重复执行
   作用：1. 设置省市区菜单
		
 */
/*==============================================================*/

 
 
DROP TABLE IF EXISTS `commons_plate`;
CREATE TABLE `commons_plate` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
`plate`  varchar(100) NOT NULL COMMENT '省市区名' ,
`parent_id`  bigint(20) NULL DEFAULT NULL   COMMENT '区-->上级市区名，市-->上级省级名' ,
`plate_type`  varchar(100) NOT NULL COMMENT '省市区类型 P：省  C：城市 A：区' ,
`is_delete`  varchar(100) NOT NULL COMMENT '是否删除 Y：删除  N:不删除' ,
`gmt_create`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`gmt_modify`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='省市区表'
;



ALTER TABLE `commons_plate` AUTO_INCREMENT=1;

commit;
