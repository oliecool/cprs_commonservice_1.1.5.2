/*==============================================================*/            
/* Author    : 何荣                                              */
/* Date      : 2018-02-08                                      */
/* Notes     : 修改内容如下：                                   */
/* 注:可重复执行
 */
/*==============================================================*/
-- 创建数据库
DROP DATABASE IF EXISTS `cprs_commonservice`;
DROP user IF EXISTS 'cprs_commonservice'@'%';
create schema cprs_commonservice default character set utf8 collate utf8_general_ci;
-- 创建用户
create user 'cprs_commonservice'@'%' IDENTIFIED BY 'cprs_prod_571'; 
-- 赋权限
grant all privileges on cprs_commonservice.* to cprs_commonservice;
-- 立即启用修改
flush  privileges ;


-- 创建数据库
DROP DATABASE IF EXISTS `cprs_quartz`;
DROP user IF EXISTS 'cprs_quartz'@'%';
create schema cprs_quartz default character set utf8 collate utf8_general_ci;
-- 创建用户
create user 'cprs_quartz'@'%' IDENTIFIED BY 'cprs_prod_571'; 
-- 赋权限
grant all privileges on cprs_quartz.* to cprs_quartz;
-- 立即启用修改
flush  privileges ;