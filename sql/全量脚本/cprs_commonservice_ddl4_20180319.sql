/*==============================================================*/            
/* Author    : 何荣                                              */
/* Date      : 2018-02-08                                      */
/* Notes     : 修改内容如下：                                   */
/* 注:可重复执行
 */
/*==============================================================*/
-- ----------------------------
-- Table structure for `commonservice_adPosition`
-- ----------------------------
use cprs_commonservice; 
DROP TABLE IF EXISTS `commonservice_adPosition`;
CREATE TABLE `commonservice_adPosition` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
`name`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位名称' ,
`code`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位代码' ,
`systemtype`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位所属系统' ,
`adPositionTypeId`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位所属分类' ,
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位描述' ,
`template`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '广告位模板' ,
`height`  int(11) NULL DEFAULT NULL COMMENT '广告位高度' ,
`width`  int(11) NULL DEFAULT NULL COMMENT '广告位宽度' ,
`gmt_create`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`gmt_modified`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='广告位'
;

-- ----------------------------
-- Table structure for `commonservice_advertisement`
-- ----------------------------
use cprs_commonservice; 
DROP TABLE IF EXISTS `commonservice_advertisement`;
CREATE TABLE `commonservice_advertisement` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
`title`  char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告标题' ,
`adPositionId`  int(11) NULL DEFAULT NULL COMMENT '广告所属广告位id' ,
`path`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径' ,
`content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '广告内容' ,
`orders`  int(11) NULL DEFAULT NULL COMMENT '广告排序号' ,
`type`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告类型' ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址' ,
`beginDate`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '广告开始时间' ,
`endDate`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '广告结束时间' ,
`gmt_create`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`gmt_modified`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='广告'
;

-- ----------------------------
-- Table structure for `commonservice_AdPositionType`
-- ----------------------------
use cprs_commonservice; 
DROP TABLE IF EXISTS `commonservice_AdPositionType`;
CREATE TABLE `commonservice_AdPositionType` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
`name`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位分类名称' ,
`code`  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位分类代码' ,
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告位分类描述' ,
`gmt_create`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`gmt_modified`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='广告位分类'
;

-- ----------------------------
-- Table structure for `trade_article`
-- ----------------------------
use cprs_commonservice; 
DROP TABLE IF EXISTS `trade_article`;
create table trade_article
(
  id                int NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
  articleType_Id    int NOT NULL  COMMENT '文章分类ID' ,
  article_code      char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章code',
  article_name      char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  article_text      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容' ,
  article_author    char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  is_publish        char(10) NULL DEFAULT NULL  COMMENT '是否发布:Y-是，N-否',
  is_top            char(10) NULL DEFAULT NULL  COMMENT '是否置顶',
  orders  int(11) NULL DEFAULT NULL COMMENT '文章排序号' ,
  publishDate  timestamp NULL DEFAULT NULL COMMENT '文章发布时间' ,
  beginDate  timestamp NULL DEFAULT NULL COMMENT '文章开始时间' ,
  endDate  timestamp NULL DEFAULT NULL COMMENT '文章结束时间' ,
  gmt_create        timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  gmt_modify        timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='文章表';

-- ----------------------------
-- Table structure for `trade_article_Type`
-- ----------------------------
use cprs_commonservice; 
DROP TABLE IF EXISTS `trade_article_Type`;
create table trade_article_Type
(
  id                int NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
  articleType_name  char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类名称' ,
  articleType_code  char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类code' ,
  status            char(10) NULL DEFAULT NULL COMMENT '文章分类启用为Y，禁用为N',
  description       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分类描述' ,
  gmt_create        timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  gmt_modify        timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='文章分类表';

commit;