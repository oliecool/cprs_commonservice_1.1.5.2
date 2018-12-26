package com.hundsun.cprs.article.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.common.enums.EnumBussinessCode;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.article.domain.Article;
import com.hundsun.cprs.commonservice.article.domain.ArticleType;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleTypeQuery;
import com.hundsun.cprs.commonservice.article.enums.EnumArticleTypeStatus;
import com.hundsun.cprs.commonservice.article.service.ArticleService;
import com.hundsun.cprs.commonservice.article.service.ArticleTypeService;
import com.hundsun.cprs.commonservice.common.enums.EnumDefinyType;
@Controller
public class ArticleTypeAction extends BaseAction{
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("articleType/list.htm")
	public void queryList(ModelMap model,@ModelAttribute("query")ArticleTypeQuery query){
		model.addAttribute("ArticleTypeStatusMap",EnumArticleTypeStatus.toMap());
		articleTypeService.selectArticleTypeByPage(query);		
	}
	
	@RequestMapping(value="articleType/add.htm",method=RequestMethod.GET)
	public String addInit(ModelMap model,@RequestParam(value="articleTypeId",required = false) String articleTypeId){
		if (articleTypeId != null){
			ArticleType articleType=articleTypeService.selectArticleTypeById(Long.parseLong(articleTypeId));
			model.addAttribute("articleType",articleType);
			return "articleType/secondAdd";
		}else{
			return "articleType/add";
		}
	}
	
	@RequestMapping(value="articleType/add.htm",method=RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("articleType")ArticleType articleType){
		if (articleType.getParentId()==null){
			articleType.setParentId(0L);
		}
		ServiceResult result=articleTypeService.createArticleType(articleType);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/artlce/helpIndex");
			model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_SAVE_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="articleType/edit.htm",method=RequestMethod.GET)
	public void editInit(ModelMap model,@RequestParam("id")String id){
		ArticleType articleType=articleTypeService.selectArticleTypeById(Long.parseLong(id));
		model.addAttribute("articleType", articleType);
		model.addAttribute("ArticleTypeStatusMap", EnumArticleTypeStatus.toMap());
	}

	@RequestMapping(value="articleType/secondEdit.htm",method=RequestMethod.GET)
	public void secondEditInit(ModelMap model,@RequestParam("id")String id){
		List<ArticleType> articleTypeList = articleTypeService.selectFirstArticleTypeList();
		model.addAttribute("articleTypeList",articleTypeList);
		ArticleType articleType=articleTypeService.selectArticleTypeById(Long.parseLong(id));
		model.addAttribute("articleType", articleType);
		model.addAttribute("ArticleTypeStatusMap", EnumArticleTypeStatus.toMap());
	}
	
	@RequestMapping(value="articleType/edit.htm",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("articleType")ArticleType articleType){
		ServiceResult result=articleTypeService.editArticleType(articleType);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/artlce/helpIndex");
			model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_EDIT_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="articleType/delete.htm")
	public String delete(ModelMap model,@RequestParam("id")String id){
		ArticleType articleType=articleTypeService.selectArticleTypeById(Long.parseLong(id));
		//1.首先判断该分类是系统创建的还是客户创建的，系统创建的不允许删除
		//2.判断该分类下是否存在文章，若存在则删除失败
		if(articleType!=null){
			if(articleType.getDefinyType().equals(EnumDefinyType.SYSTEM_TYPE.getType())){
				model.addAttribute("message", EnumBussinessCode.SYSTEMTYPE_DELETE_FAILED.getValue());
				return "error";
			}
			List<Article> articleList=articleService.selectArticleListByArticleTypecode(articleType.getArticleTypeCode());
			if(!articleList.isEmpty()){
				model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_DELETE_FAILED.getValue());
				return "error";
			}
			//一级分类下存在二级分类时，也不允许删除
			if(articleType.getParentId()==0){
				List<ArticleType> articleTypeList = articleTypeService.selectSecondArticleTypeListById(articleType.getId());
				if (!articleTypeList.isEmpty()){
					model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_DELETE_FAILED.getValue());
					return "error";
				}
			}
		}
		ServiceResult result=articleTypeService.deleteArticleType(Long.parseLong(id));
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			model.addAttribute("url", "/artlce/helpIndex");
			model.addAttribute("message", EnumBussinessCode.ARTICLETYPE_DELETE_SUCCESS.getValue());
			return "success";
		}
	}
	@RequestMapping("articleType/view.htm")
	public void articleTypeView(ModelMap model,@RequestParam("id")String id){
		ArticleType articleType=articleTypeService.selectArticleTypeById(Long.parseLong(id));
		model.addAttribute("articleType",articleType);
		ArticleType parentType=articleTypeService.selectArticleTypeById(articleType.getParentId());
		model.addAttribute("parentType",parentType);
	}
	/*
	 * 异步校验文章分类编码是否重复
	 * 
	 * */
	@RequestMapping(value = "/articleType/ajax/validateCode.htm")
	public void validateGoodsCode(@RequestParam("code") String code,ModelMap model, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		ArticleType articleType=articleTypeService.selectArticleTypeBycode(code);
		if(articleType==null){
			json.put("info", "Yes");
		}else{
			json.put("info", "No");
		}				
		try {
			response.getWriter().print(json);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
}
