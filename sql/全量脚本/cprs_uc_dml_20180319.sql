/*==============================================================*/            
/* Author    : 何荣                                              */
/* Date      : 2018-02-08                                      */
/* Notes     : 修改内容如下：                                   */
/* 注:可重复执行
 */
/*==============================================================*/
/**
 * 清空公共服务平台的菜单及配置
 */
delete from eclp_authority where sub_system_id = (select id from eclp_sub_system where name='commonservice' and is_deleted='N');
delete from eclp_role_system where role_id = (select id from eclp_role where code='commonservice_role' and is_deleted='N');
delete from eclp_user_role where role_id = (select id from eclp_role where code='commonservice_role' and is_deleted='N');
delete from eclp_role where code = 'commonservice_role' and is_deleted='N' ;
delete from eclp_sub_system where name = 'commonservice';
/**
 * 构建公共服务平台的菜单及配置
 */
insert into eclp_sub_system values (seq_sub_system.nextval,'commonservice','公共服务平台',2,1,1,11,'http://commonservice.cprs.deve:8089',null,1,null,'N',sysdate,sysdate,null,null,get_curr_exchange_id());
insert into eclp_role values (seq_role.nextval, 'commonservice_role', '公共服务平台超级角色', 1, 10, 'N', NULL, sysdate, sysdate, 2, 0, 0);
insert into eclp_role_system values (seq_role_system_id.nextval,(select id from eclp_role where code='commonservice_role' and display_name='公共服务平台超级角色' and is_deleted='N'),(select id from eclp_sub_system where name='commonservice' and is_deleted='N'),sysdate,sysdate,get_curr_exchange_id());
commit;

--==eclp菜单根节点
insert into ECLP_AUTHORITY values (seq_authority.nextval, null, '公共服务平台', 1, 2, null, (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 30, 1, -1, 2, 1, 'N',sysdate,sysdate, 1,get_curr_exchange_id(),null);
commit;

--一级菜单
insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320000, '广告管理', 2, 1, null, (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 1, 1, (select t.id from (select id from eclp_authority where code is null and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 2, 1, 'N', sysdate, sysdate, 1, 0, null);

--二级菜单
insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320060, '广告位分类', 3, 1, 'adPositionType/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 1, 1, (select t.id from (select id from eclp_authority where code = 320000 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320040, '广告位维护', 3, 1, 'adposition/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 2, 1, (select t.id from (select id from eclp_authority where code = 320000 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320020, '广告维护', 3, 1, 'advertisement/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 3, 1, (select t.id from (select id from eclp_authority where code = 320000 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

commit;

/*==============================================================*/            
/* Author    : 何荣                                                                                                                                */
/* Date      : 2018-03-06                                       */
/* Notes     : 修改内容如下：                                                                                                             */                                         
/*==============================================================*/

--一级菜单
insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320400, '文章管理', 2, 1, null, (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 2, 1, (select t.id from (select id from eclp_authority where code is null and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 2, 1, 'N', sysdate, sysdate, 1, 0, null);

insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320800, '短信管理', 2, 1, null, (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 3, 1, (select t.id from (select id from eclp_authority where code is null and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 2, 1, 'N', sysdate, sysdate, 1, 0, null);

--二级菜单
insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320420, '文章分类', 3, 1, 'articleType/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 1, 1, (select t.id from (select id from eclp_authority where code = 320400 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320440, '文章维护', 3, 1, 'article/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 2, 1, (select t.id from (select id from eclp_authority where code = 320400 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320820, '模板维护', 3, 1, 'smsModel/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 1, 1, (select t.id from (select id from eclp_authority where code = 320800 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

insert into eclp_authority (ID, CODE, NAME, TYPE, IS_CORE, URL, SUB_SYSTEM_ID, SORT, STATUS, PARENT_ID, IS_LEAF, OPEN_TYPE, IS_DELETED, GMT_CREATE, GMT_MODIFY, IS_ASSIGN, EXCHANGE_ID, OWNER)
values (seq_authority.nextval, 320820, '短信记录列表', 3, 1, 'smsRecord/list.htm', (select id from eclp_sub_system where name='commonservice' and is_deleted='N'), 2, 1, (select t.id from (select id from eclp_authority where code = 320800 and sub_system_id=(select id from eclp_sub_system where name='commonservice' and is_deleted='N') and is_deleted='N') t), 1, 1, 'N', sysdate, sysdate, 1, 0, null);

commit;