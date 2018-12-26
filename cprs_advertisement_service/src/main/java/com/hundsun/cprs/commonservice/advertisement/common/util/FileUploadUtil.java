package com.hundsun.cprs.commonservice.advertisement.common.util;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import com.hundsun.jresplus.common.util.DateUtil;
import com.hundsun.jresplus.common.util.StringUtil;

public class FileUploadUtil {
	private Log log = LogFactory.getLog(getClass());
	public static final String SEP = "/";
	public static final String POINT = ".";
	public static final String[] EXTARRAY = { "jpg", "jpeg", "gif", "png" };
	public static final int MAX_IMAG_SIZE = 5 * 1024 * 1024; // 最大图片文件尺寸5M

	public String uploadRoot;

	public static String getSubFileDir() {
		return DateUtil.getDateTime("yyyyMMdd", new Date());
	}

	/**
	 * 广告图片上传
	 * 
	 * @param file
	 * @param destDir
	 * @return
	 */
	public String uploadFile(MultipartFile file, String destDir) {
		String filePath = "";
		if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
			return filePath;
		}

		String orgFileName = file.getOriginalFilename();
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

		if (!UploadUtil.ifExtendNamePermitted(exts, FileUploadUtil.EXTARRAY)) {
			return filePath;
		}

		String newFileName = UploadUtil.createFileName(exts);
		String saveDir = destDir + SEP + getSubFileDir();
		try {
			UploadUtil.saveFile(file, uploadRoot + SEP + saveDir, newFileName);
		} catch (IOException e) {
			log.error("FileUploadUtil.uploadFile", e);
		}		
		filePath = saveDir + SEP + newFileName;
		return filePath;
	}
	
	public String getUploadRoot() {
		return uploadRoot;
	}

	public void setUploadRoot(String uploadRoot) {
		this.uploadRoot = uploadRoot;
	}
}
