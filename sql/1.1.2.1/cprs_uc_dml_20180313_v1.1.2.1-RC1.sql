/*==============================================================*/            
/* Author    : 何荣                                             */
/* Date      : 2018-03-06                                       */
/* Notes     : 修改内容如下：                                   */
/* 注:不可重复执行                                              */
/*==============================================================*/
--更新原广告菜单下的二级菜单code值
UPDATE ECLP_AUTHORITY SET CODE = 320020 WHERE SUB_SYSTEM_ID=(SELECT ID FROM ECLP_SUB_SYSTEM WHERE NAME='commonservice' AND FULL_NAME='公共服务平台') AND CODE = 320400 AND NAME = '广告维护';
UPDATE ECLP_AUTHORITY SET CODE = 320040 WHERE SUB_SYSTEM_ID=(SELECT ID FROM ECLP_SUB_SYSTEM WHERE NAME='commonservice' AND FULL_NAME='公共服务平台') AND CODE = 320800 AND NAME = '广告位维护';
UPDATE ECLP_AUTHORITY SET CODE = 320060 WHERE SUB_SYSTEM_ID=(SELECT ID FROM ECLP_SUB_SYSTEM WHERE NAME='commonservice' AND FULL_NAME='公共服务平台') AND CODE = 321200 AND NAME = '广告位分类';

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