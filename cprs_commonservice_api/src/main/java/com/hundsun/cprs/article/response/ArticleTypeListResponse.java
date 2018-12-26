package com.hundsun.cprs.article.response;

import com.hundsun.cprs.article.vo.ArticleTypeVo;

import java.util.List;

public class ArticleTypeListResponse extends  BaseResponse{

    private List<ArticleTypeVo> articleVoList;

    public List<ArticleTypeVo> getArticleVoList() {
        return articleVoList;
    }

    public void setArticleVoList(List<ArticleTypeVo> articleVoList) {
        this.articleVoList = articleVoList;
    }
}
