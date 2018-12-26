package com.hundsun.cprs.commonservice.advertisement.service.impl;

import com.hundsun.cprs.commonservice.advertisement.common.util.BeanUtils;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPosition;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionJson;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionType;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionService;
import com.hundsun.cprs.commonservice.advertisement.service.AdPositionTypeService;
import com.hundsun.cprs.commonservice.advertisement.service.AdpositionJsonService;
import com.hundsun.cprs.commonservice.advertisement.service.BaseService;
import com.hundsun.cprs.commonservice.article.domain.ArticleType;
import com.hundsun.cprs.commonservice.article.domain.ArticleTypeJson;
import com.hundsun.cprs.commonservice.article.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdpositionJsonServiceImpl extends BaseService implements AdpositionJsonService {

    @Autowired
    private AdPositionTypeService adPositionTypeService;
    @Autowired
    private AdPositionService adPositionService;
    @Autowired
    private ArticleTypeService articleTypeService;

    @Override
    public List<AdPositionJson> getAdPositionJson() {
        //第一步：获取所有的广告位分类
        List<AdPositionType> adPositionTypeList = adPositionTypeService.selectAdPositionTypeList();
        List<AdPositionJson> adPositionJsonList = new ArrayList<AdPositionJson>();
        adPositionJsonList=BeanUtils.batchTransform(AdPositionJson.class, adPositionTypeList);
        //第二步：获取所有的广告位
        List<AdPosition> adPositionList = adPositionService.selectAdPositionList();
        if (adPositionList != null){
            for (AdPosition adPosition:adPositionList){
                AdPositionJson adPositionJson = BeanUtils.transfrom(AdPositionJson.class,adPosition);
                adPositionJsonList.add(adPositionJson);
            }
        }
        return adPositionJsonList;
    }

    @Override
    public List<ArticleTypeJson> getArticleJson() {
        List<ArticleType> articleTypeList = articleTypeService.selectArticleTypeList();
        List<ArticleTypeJson> articleTypeJsonList = new ArrayList<>();
        if (articleTypeList != null){
            for (ArticleType articleType : articleTypeList){
                ArticleTypeJson json = new ArticleTypeJson();
                json.setParentId(articleType.getParentId());
                json.setId(articleType.getId());
                json.setName(articleType.getArticleTypeName());
                articleTypeJsonList.add(json);
            }
        }
        return articleTypeJsonList;
    }
}
