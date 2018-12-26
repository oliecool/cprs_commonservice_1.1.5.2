package com.hundsun.cprs.article.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hundsun.cprs.commonservice.advertisement.common.util.BeanUtils;
import com.hundsun.cprs.commonservice.advertisement.common.util.FileUploadUtil;
import com.hundsun.cprs.commonservice.advertisement.domain.AdPositionJson;
import com.hundsun.cprs.commonservice.advertisement.domain.Advertisement;
import com.hundsun.cprs.commonservice.advertisement.service.AdpositionJsonService;
import com.hundsun.cprs.commonservice.article.domain.ArticleTypeJson;
import com.hundsun.cprs.commonservice.common.redis.dao.BaseRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.cprs.BaseAction;
import com.hundsun.cprs.common.enums.EnumBussinessCode;
import com.hundsun.cprs.commonservice.advertisement.common.ServiceResult;
import com.hundsun.cprs.commonservice.article.domain.Article;
import com.hundsun.cprs.commonservice.article.domain.ArticleType;
import com.hundsun.cprs.commonservice.article.domain.query.ArticleQuery;
import com.hundsun.cprs.commonservice.article.enums.EnumArticleStatus;
import com.hundsun.cprs.commonservice.article.service.ArticleService;
import com.hundsun.cprs.commonservice.article.service.ArticleTypeService;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ArticleAction extends BaseAction{
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private AdpositionJsonService adpositionJsonService;
	@Resource(name = "imageUploadUtil")
	private FileUploadUtil fileUploadUtil;
	@Autowired
	private BaseRedisDao<String> stringRedisDao;

	@RequestMapping("article/articleList.htm")
	public String queryList(ModelMap model,@ModelAttribute("query")ArticleQuery query){
		List<ArticleType> articleTypeList=articleTypeService.selectSecondArticleTypeList();
		model.addAttribute("articleTypeList", articleTypeList);
		model.addAttribute("EnumArticleStatusMap", EnumArticleStatus.toMap());
		articleService.selectArticleByPage(query);
		return "article/list";
	}
	@RequestMapping("article/list.htm")
	public String queryPage(ModelMap model){
		return "article/listIndex";
	}

	@RequestMapping("article/articleLeft")
	public String getLeftPage(ModelMap model){
		return "article/leftIndex";
	}

	@RequestMapping("artlce/helpIndex")
	public String gethelpPage(ModelMap model){
		return "article/helpIndex";
	}

	@RequestMapping("article/articleRight")
	public String getRightPage(ModelMap model){
		return "article/rightIndex";
	}
	@RequestMapping("article/addWhat")
	public String addWhat(ModelMap model,@RequestParam("id")String id){
		model.addAttribute("articleTypeId",id);
		return "article/switchModel";
	}
	/**
	 * 文章二级分类树的展示
	 * @return
	 */
	@RequestMapping(value = "/ajax/getArticleTypeTree")
	public @ResponseBody List<ArticleTypeJson> getAdpositionTypeTree() {
		return adpositionJsonService.getArticleJson();
	}

	@RequestMapping(value="article/add.htm",method=RequestMethod.GET)
	public void addInit(ModelMap model,@RequestParam("articleTypeId")String articleTypeId){
		/*List<ArticleType> articleTypeList=articleTypeService.selectArticleTypeList();
		model.addAttribute("articleTypeList", articleTypeList);*/
		ArticleType articleType = articleTypeService.selectArticleTypeById(Long.parseLong(articleTypeId));
		model.addAttribute("articleType",articleType);
	}
	
	@RequestMapping(value="article/add.htm",method=RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("article")Article article,@RequestParam("uuid")String uuid/*,@RequestParam(value = "file", required = false)MultipartFile file*/)throws Exception{
		/*if(file.getOriginalFilename()!=""){
			String path=fileUploadUtil.uploadFile(file, "articleLogo");
		    article.setLogoImageUrl(path);
		}*/
		String path=stringRedisDao.getValueByKey("article:article"+uuid+"_img");
		article.setLogoImageUrl(path);
		article.setIsPublish(EnumArticleStatus.WAIT_PUBLISH.getCode());
		ServiceResult result=articleService.createArticle(article);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			ArticleQuery query = new ArticleQuery();
			query.setArticalTypeId(article.getArticalTypeId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/article/articleList");
			model.addAttribute("message", EnumBussinessCode.ARTICLE_SAVE_SUCCESS.getValue());
			return "success";
		}
	}



	@RequestMapping(value="article/publish.htm",method=RequestMethod.GET)
	public String publishInit(ModelMap model,@RequestParam("id")String id){
		Article article=articleService.selectArticleById(Long.parseLong(id));
		article.setIsPublish(EnumArticleStatus.FINISH_PUBLISH.getCode());
		article.setPublishDate(new Date());
		ServiceResult result=articleService.editArticle(article);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			ArticleQuery query = new ArticleQuery();
			query.setArticalTypeId(article.getArticalTypeId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/article/articleList");
			model.addAttribute("message", EnumBussinessCode.ARTICLE_PUBLISH_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="article/publish.htm",method=RequestMethod.POST)
	public String publish(ModelMap model,@ModelAttribute("article")Article article,@RequestParam("uuid")String uuid/*,@RequestParam(value = "file", required = false)MultipartFile file*/)throws Exception{
		/*if(file.getOriginalFilename()!=""){
			String path=fileUploadUtil.uploadFile(file, "articleLogo");
			article.setLogoImageUrl(path);
		}*/
		String path=stringRedisDao.getValueByKey("article:article"+uuid+"_img");
		article.setLogoImageUrl(path);
		article.setIsPublish(EnumArticleStatus.FINISH_PUBLISH.getCode());
		article.setPublishDate(new Date());
		ServiceResult result=articleService.createArticle(article);		
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			ArticleQuery query = new ArticleQuery();
			query.setArticalTypeId(article.getArticalTypeId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/article/articleList");
			model.addAttribute("message", EnumBussinessCode.ARTICLE_PUBLISH_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="article/cancelPublish.htm",method=RequestMethod.GET)
	public String cancelPublish(ModelMap model,@RequestParam("id")String id){
		Article article=articleService.selectArticleById(Long.parseLong(id));
		article.setIsPublish(EnumArticleStatus.CANCEL_PUBLISH.getCode());
		article.setPublishDate(null);
		ServiceResult result=articleService.editArticle(article);
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			ArticleQuery query = new ArticleQuery();
			query.setArticalTypeId(article.getArticalTypeId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/article/articleList");
			model.addAttribute("message", EnumBussinessCode.ARTICLE_CANCELPUBLISH_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="article/edit.htm",method=RequestMethod.GET)
	public void editInit(ModelMap model,@RequestParam("id")String id){
		Article article=articleService.selectArticleById(Long.parseLong(id));
		model.addAttribute("article", article);
		List<ArticleType> articleTypeList=articleTypeService.selectSecondArticleTypeList();
		model.addAttribute("articleTypeList", articleTypeList);
	}
	
	@RequestMapping(value="article/edit.htm",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("article")Article article,@RequestParam("oldArticleTypeId")String oldArticleTypeId/*,@RequestParam(value = "file", required = false)MultipartFile file*/)throws Exception{

		/*if(file != null){
			String path=fileUploadUtil.uploadFile(file, "articleLogo");
			article.setLogoImageUrl(path);
		}*/
		String path=stringRedisDao.getValueByKey("article:article"+article.getId()+"_img");
		if(path != null){
			article.setLogoImageUrl(path);
		}
		
		ServiceResult result=articleService.editArticle(article);
		
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			ArticleQuery query = new ArticleQuery();
			query.setArticalTypeId(Long.parseLong(oldArticleTypeId));
			model.addAttribute("query", query);
			model.addAttribute("url", "/article/articleList");
			model.addAttribute("message", EnumBussinessCode.ARTICLE_EDIT_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="article/delete.htm")
	public String delete(ModelMap model,@RequestParam("id")String id){
		Article article = articleService.selectArticleById(Long.parseLong(id));
		ServiceResult result=articleService.deleteArticle(Long.parseLong(id));
		if(result.getErrorNO()!=null){
			model.addAttribute("message", result.getErrorInfo());
			return "error";
		}else{
			ArticleQuery query = new ArticleQuery();
			query.setArticalTypeId(article.getArticalTypeId());
			model.addAttribute("query", query);
			model.addAttribute("url", "/article/articleList");
			model.addAttribute("message", EnumBussinessCode.ARTICLE_DELETE_SUCCESS.getValue());
			return "success";
		}
	}
	
	@RequestMapping(value="article/view.htm")
	public void view(ModelMap model,@RequestParam("id")String id){
		Article article=articleService.selectArticleById(Long.parseLong(id));
		model.addAttribute("article", article);
		ArticleType articleType = articleTypeService.selectArticleTypeById(article.getArticalTypeId());
		model.addAttribute("articleType", articleType);
		model.addAttribute("EnumArticleStatusMap", EnumArticleStatus.toMap());
	}
	
	/*
	 * 异步校验文章编码是否重复
	 * 
	 * */
	@RequestMapping(value = "/article/ajax/validateCode.htm")
	public void validateGoodsCode(@RequestParam("code") String code,ModelMap model, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		Article articleParam = new Article();
		articleParam.setArticleCode(code);
		Article article = articleService.selectArticleBycodeOrId(articleParam);
		if(article==null){
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
	
	/*
	 * 异步校验文章排序号是否重复
	 * 
	 * */
	@RequestMapping(value = "/article/ajax/validateOrders.htm")
	public void validateGoodsCode(@RequestParam("orders") String orders,@RequestParam("articalTypeId") String articalTypeId,ModelMap model, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		List<Article> articleList=articleService.selectArticleByOrderAndTypeId(Integer.parseInt(orders), Long.parseLong(articalTypeId));
		if(articleList.isEmpty()){
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

	@RequestMapping("/ajax/article/updateLogo.htm")
	public void updateLogo(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "file")MultipartFile file, HttpServletResponse response)throws Exception{
		JSONObject json = new JSONObject();
		String path=fileUploadUtil.uploadFile(file, "articleLogo");
		if (id.equals("articleId")){
			UUID uuid = UUID.randomUUID();
			String key=uuid.toString();
			stringRedisDao.updateValueByKey("article:article"+key+"_img",path, 60*60*2*1000);
			json.put("uuid",key);
		}else{
			Long articleId=Long.parseLong(id);
			stringRedisDao.updateValueByKey("article:article"+articleId+"_img",path, 60*60*2*1000);
		}
		json.put("data",path);
		try {
			response.getWriter().print(json);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	@Override
	protected void initBinder(WebDataBinder binder) {
		// 注册默认的日期格式化类型: yyyy-MM-dd
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
