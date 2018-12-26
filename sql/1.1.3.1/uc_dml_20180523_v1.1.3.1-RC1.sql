

/*==============================================================*/            
/* Author    : jhn                                             */
/* Date      : 2018-05-023                                     */
/* Notes     : 修改内容如下：                                   */
/* 注:可重复执行
   作用：1. 设置省市区菜单
		
 */
/*==============================================================*/

delete from eclp_authority where code='320910' and name = '地域列表' and sub_system_id = (select id from eclp_sub_system where name='commonservice' and is_deleted='N') and parent_id = (select t.id from (select id from eclp_authority where code='320900' and name='地域管理' and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N')  and is_deleted='N')t);

delete from eclp_authority where code='320900' and name='地域管理' and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N')  and is_deleted='N';


insert into eclp_authority values (seq_authority.nextval,320900,'地域管理',2,1,null,(select id from eclp_sub_system where name='commonservice' and is_deleted='N'),4,1,(select t.id from (select id from eclp_authority where code is null and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t),2,1,'N',sysdate,sysdate,1,get_curr_exchange_id(),null);


insert into eclp_authority values (seq_authority.nextval,320910,'地域列表',3,1,'/plate/plateIndex.htm',(select id from eclp_sub_system where name='commonservice' and is_deleted='N'),1,1,(select t.id from (select id from eclp_authority where code = 320900 and sub_system_id=(
select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t),2,1,'N',sysdate,sysdate,1,get_curr_exchange_id(),null);


commit;
