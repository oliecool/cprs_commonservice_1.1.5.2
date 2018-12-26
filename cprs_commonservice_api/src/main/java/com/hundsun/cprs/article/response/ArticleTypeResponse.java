package com.hundsun.cprs.article.response;

import com.hundsun.cprs.article.vo.ArticleTypeVo;

public class ArticleTypeResponse extends BaseResponse{

    private ArticleTypeVo  articleTypeVo;

    public ArticleTypeVo getArticleTypeVo() {
        return articleTypeVo;
    }

    public void setArticleTypeVo(ArticleTypeVo articleTypeVo) {
        this.articleTypeVo = articleTypeVo;
    }
}
