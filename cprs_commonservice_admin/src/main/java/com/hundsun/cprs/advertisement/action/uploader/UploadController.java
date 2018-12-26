package com.hundsun.cprs.advertisement.action.uploader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hundsun.cprs.BaseAction;
import com.hundsun.jresplus.common.util.StringUtil;
import com.hundsun.network.uc.biz.enums.EnumFileType;
import com.hundsun.network.uc.biz.util.helper.FileHelper;


@Controller
@RequestMapping("/advertisement")
public class UploadController extends BaseAction {
	
	@Value("${image.server.host}")
	private String serverHost;
	
	@Value("${image.server.port}")
	private String serverPort;
	
	@Value("${image.server.path}")
	private String uploadServerPath;
	
	@Value("${upload.images.root}")
	private String uploadPath;
		

	/**
	 * kindeditor富文本编辑器上传
	 * @author tanhl
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/kindeditor-image-upload", "/kindeditor-accessory-upload" })
	public void kindeditorUpload(@RequestParam("imgFile") MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response) {
		String errMsg;
		//文件格式、大小校验
		if(StringUtil.equals(request.getRequestURI(), "/advertisement/kindeditor-image-upload.htm")) {
			errMsg = FileHelper.checkFile(file, EnumFileType.IMAGE.getCode());
		} else {
			errMsg = FileHelper.checkFile(file, EnumFileType.ACCESSORY.getCode());
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		//如果文件格式校验没有错误则上传文件到服务器
		if(StringUtil.isBlank(errMsg)) {
			String uploadServer = "http://" + serverHost + ":" +serverPort + "/" + uploadServerPath;
			String uploadName = FileHelper.uploadFile(file, uploadPath);
			buffer.append("\"filename\":\"" + uploadName + "\""); // 上传之后的文件名
			buffer.append(",");
			buffer.append("\"fileOriginalName\":\"" + file.getOriginalFilename() + "\""); // 原始文件名
			buffer.append(",");
			buffer.append("\"url\":\"" + uploadServer + "/" + uploadName + "\""); // 上传之后的读取url
			buffer.append(",");
			buffer.append("\"error\":" + 0); // 上传之后的读取url
		} else {
			buffer.append("\"error\":" + 1);
			buffer.append(",");
			buffer.append("\"message\":\"" + errMsg + "\""); //如果有错误，返回错误信息
		}
		buffer.append("}");
		try {
			response.getWriter().write(buffer.toString());
			response.getWriter().close();
		} catch (Exception e) {
			log.error("富文本编辑器上传文件失败", e);
		}
	}
}

