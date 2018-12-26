

-- 新增广告位分类--竞购系统
delete from commonservice_adpositiontype;
insert into commonservice_adpositiontype(name,code,definy_Type) values("竞购系统","bid","system");

-- 新增广告位
delete from commonservice_adposition;
insert into commonservice_adposition(name,code,definy_Type,adPositionTypeId) SELECT "H5-首页轮播","indexCarousel","system",id from commonservice_adpositiontype where code = 'bid';
insert into commonservice_adposition(name,code,definy_Type,adPositionTypeId) SELECT "H5-热点新闻","hotNews","system",id from commonservice_adpositiontype where code = 'bid';

-- 修改老数据和新分类的关联关系
update commonservice_advertisement set adPositionId=(SELECT id from commonservice_adposition where code = "indexCarousel") where parentCode="indexCarousel";
update commonservice_advertisement set adPositionId=(SELECT id from commonservice_adposition where code = "hotNews") where parentCode="hotNews";

-- 新建文章一级分类
delete from trade_article_type;
insert into trade_article_type(articleType_name,articleType_code,parentId,definy_Type) values("竞购系统","bid",0,"system");
insert into trade_article_type(articleType_name,articleType_code,parentId,definy_Type) values("会员系统","ucSystem",0,"system");
insert into trade_article_type(articleType_name,articleType_code,parentId,definy_Type) values("交易系统","tradeSystem",0,"system");

-- 新建文章二级分类
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "帮助中心","helpCenter","system",id from trade_article_type where articleType_code = 'bid';
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "竞购文章总分类","bidSystem","system",id from trade_article_type where articleType_code = 'bid';
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "竞购发现tab1","bid_find1","system",id from trade_article_type where articleType_code = 'bid';
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "竞购发现tab2","bid_find2","system",id from trade_article_type where articleType_code = 'bid';
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "竞购发现tab3","bid_find3","system",id from trade_article_type where articleType_code = 'bid';
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "会员协议","uc_agreements","system",id from trade_article_type where articleType_code = 'ucSystem';
insert into trade_article_type(articleType_name,articleType_code,definy_Type,parentId) SELECT "交易协议","trade","system",id from trade_article_type where articleType_code = 'tradeSystem';

-- 修改老数据和新分类的关联关系
update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "helpCenter") where parentCode="helpCenter";

update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "bidSystem") where parentCode="bidSystem";

update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "uc_agreements") where parentCode="uc_agreements";

update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "trade") where parentCode="trade";

