-- 广告表中新增父分类编码字段 
ALTER TABLE commonservice_advertisement add parentCode char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位分类代码';
 -- 将老数据的父分类编码更新到parentCode字段
update commonservice_advertisement ta set parentCode=(select code from commonservice_adPosition ca where ca.id=ta.adPositionId);

 -- 文章表新增父分类编码字段
ALTER TABLE trade_article add parentCode char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类代码';
 -- 将老数据的父分类编码更新到parentCode字段
update trade_article ta set parentCode=(select articleType_code from trade_article_type tat where tat.id=ta.articleType_Id);

-- 修改文章文类表，增加字段parentId
ALTER TABLE trade_article_type add parentId INT(11) DEFAULT 0 COMMENT '父分类id';

-- 修改广告位自增字段的起始点为2000
alter table commonservice_adPosition auto_increment = 2000 ;