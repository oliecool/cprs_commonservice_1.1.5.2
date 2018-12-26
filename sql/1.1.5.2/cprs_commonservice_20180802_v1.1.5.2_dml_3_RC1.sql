update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "bid_find1") where parentCode="bid_find1";

update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "bid_find2") where parentCode="bid_find2";

update trade_article set articleType_Id=(SELECT id from trade_article_type where articleType_code = "bid_find3") where parentCode="bid_find3";
