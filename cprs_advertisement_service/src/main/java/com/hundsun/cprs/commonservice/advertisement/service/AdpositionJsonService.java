package com.hundsun.cprs.commonservice.advertisement.service;

import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionJson;
import com.hundsun.cprs.commonservice.article.domain.ArticleTypeJson;

import java.util.List;

public interface AdpositionJsonService {
    public List<AdPositionJson> getAdPositionJson();
    public List<ArticleTypeJson> getArticleJson();
}
