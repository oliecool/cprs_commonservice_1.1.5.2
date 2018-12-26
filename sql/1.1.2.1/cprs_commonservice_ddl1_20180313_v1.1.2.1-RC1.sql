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
