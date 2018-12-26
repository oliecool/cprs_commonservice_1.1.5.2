package com.hundsun.cprs.commonservice.advertisement.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.hundsun.jresplus.common.util.DateUtil;
import com.hundsun.jresplus.common.util.StringUtil;



public class UploadUtil {

	private static Log log = LogFactory.getLog(UploadUtil.class);
	public static final String SEP = "/";
	public static final String POINT = ".";
	public static final String[] EXTARRAY = { "xls", "jpg", "jpeg", "gif", "png", "swf", "tif" };
	/**
	 * 图片默认大小为5MB
	 */
	public static final int MAX_IMAG_SIZE = 5 * 1024 * 1024; // 最大图片文件尺寸5M

	/**
	 * 默认上传路径配置参数
	 */
	@Value("${upload.root}")
	public static String uploadRoot;

	/**
	 * 文件默认大小为5MB
	 */
	public static int MAX_FILE_SIZE = 1024 * 5120; // 最大文件尺寸5M

	/**
	 * 判断上传的文件大小是否小于指定值
	 * 
	 * @param file
	 * @return
	 */
	public static boolean ifFileSizePermitted(MultipartFile file) {
		return file.getSize() < MAX_FILE_SIZE ? true : false;
	}

	/**
	 * 判断上传的文件大小是否小于指定值
	 * 
	 * @param file
	 * @param maxImageSize
	 *            图片最大尺寸
	 * @param maxFileSize
	 *            文件最大尺寸
	 * @return
	 */
	public static boolean ifFileSizePermitted(MultipartFile file, int maxImageSize, int maxFileSize) {
		String orgFileName = file.getOriginalFilename();
		if (StringUtil.isBlank(orgFileName)) {
			// 如果传的是空文件
			return true;
		}
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());
		boolean isImage = false;
		for (String s : EXTARRAY) {
			if (exts.equalsIgnoreCase(s)) {
				isImage = true;
				continue;
			}
		}
		if (isImage) {
			return file.getSize() < maxImageSize ? true : false;
		} else {
			return file.getSize() < maxFileSize ? true : false;
		}

	}

	/**
	 * 判断上传的文件扩展名是否合法
	 * 
	 * @param file
	 * @return
	 */
	public static boolean ifExtendNamePermitted(MultipartFile file) {
		String orgFileName = file.getOriginalFilename();
		if (StringUtil.isBlank(orgFileName)) {
			// 如果传的是空文件
			return true;
		}
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());
		return ifExtendNamePermitted(exts);
	}

	/**
	 * 判断上传的文件扩展名是否合法
	 * 
	 * @param file
	 * @param extArray
	 *            允许的文件后缀
	 * @return
	 */
	public static boolean ifExtendNamePermitted(MultipartFile file, String[] extArray) {
		String orgFileName = file.getOriginalFilename();
		if (StringUtil.isBlank(orgFileName)) {
			// 如果传的是空文件
			return true;
		}
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());
		return ifExtendNamePermitted(exts);
	}

	public static boolean ifExtendNamePermitted(String exts) {
		for (String s : EXTARRAY) {
			if (exts.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param exts
	 * @param extArray
	 * @return true:在extArray中，false:不在其中
	 */
	public static boolean ifExtendNamePermitted(String exts, String[] extArray) {
		for (String s : extArray) {
			if (exts.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param uploadRoot
	 *            上传根目录
	 * @param destDir
	 *            上传子目录
	 * @return
	 */
	public static String uploadFile(MultipartFile file, String uploadRoot, String destDir) {
		String filePath = "";
		if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
			return filePath;
		}

		String orgFileName = file.getOriginalFilename();
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

		if (!ifExtendNamePermitted(exts)) {
			// 如果文件扩展名不在允许范围内
			return filePath;
		}

		String newFileName = createFileName(exts);
		String saveDir = uploadRoot + SEP + destDir + SEP + getSubFileDir();
		try {
			saveFile(file, saveDir, newFileName);
		} catch (IOException e) {
			log.error("UploadUtil.uploadFile", e);
		}
		filePath = destDir + SEP + getSubFileDir() + SEP + newFileName;
		return filePath;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param uploadRoot
	 * @return
	 */
	public static String uploadFile(MultipartFile file, String uploadRoot) {
		String filePath = "";
		if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
			return filePath;
		}

		String orgFileName = file.getOriginalFilename();
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

		if (!ifExtendNamePermitted(exts)) {
			// 如果文件扩展名不在允许范围内
			return filePath;
		}

		String newFileName = createFileName(exts);
		String saveDir = uploadRoot + SEP + getSubFileDir();
		try {
			saveFile(file, saveDir, newFileName);
		} catch (IOException e) {
			log.error("UploadUtil.uploadFile", e);
		}
		filePath = uploadRoot + SEP + getSubFileDir() + SEP + newFileName;
		return filePath;
	}

	/**
	 * 上传Excel
	 */
	public String uploadFile(MultipartFile file) {
		String filePath = "";
		if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
			return filePath;
		}
		String orgFileName = file.getOriginalFilename();
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());
		String newFileName = UploadUtil.createFileName(exts);
		String saveDir = getSubFileDir();
		try {
			saveFile(file, uploadRoot + SEP, newFileName);
		} catch (IOException e) {
			log.error("UploadUtil.uploadFile", e);
		}
		filePath = uploadRoot + SEP + newFileName;
		return filePath;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param destDir
	 * @return
	 */
	public static String uploadFileDefaultUpload(MultipartFile file, String destDir) {
		String filePath = "";
		if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
			return filePath;
		}

		String orgFileName = file.getOriginalFilename();
		String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

		if (!ifExtendNamePermitted(exts)) {
			// 如果文件扩展名不在允许范围内
			return filePath;
		}

		String newFileName = createFileName(exts);
		String saveDir = uploadRoot + SEP + destDir + SEP + getSubFileDir();
		try {
			saveFile(file, saveDir, newFileName);
		} catch (IOException e) {
			log.error("UploadUtil.uploadFile", e);
		}
		filePath = destDir + SEP + getSubFileDir() + SEP + newFileName;
		return filePath;
	}

	public static void saveFile(MultipartFile file, String destDir, String newFileName) throws IOException {
		if (!new File(destDir).exists()) {
			new File(destDir).mkdirs();
		}
		file.transferTo(new File(destDir + SEP + newFileName));
	}

	public static synchronized String createFileName(String exts) {
		Date date = new Date();
		String fileName = getRandomString(8) + String.valueOf(date.getTime()) + POINT + StringUtil.toLowerCase(exts);
		return fileName;
	}

	public synchronized String createFileNameOrg(String preName, String orgname, String backName) {
		int i = orgname.lastIndexOf(".");
		if (preName == null)
			preName = "";
		if (backName == null)
			backName = "";
		String fileName = "";
		if (i != -1)
			fileName = preName + orgname.substring(0, i) + backName + StringUtil.toLowerCase(orgname.substring(i));
		else
			fileName = preName + orgname.substring(0, i) + backName;

		return fileName;
	}

	private static synchronized String getRandomString(int size) {// 随机字符串
		char[] c = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',
				'x', 'c', 'v', 'b', 'n', 'm' };
		Random random = new Random(System.currentTimeMillis()); // 初始化随机数产生器
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}

		double randtemp = Math.random();
		if (randtemp < 0.1)
			randtemp += 0.1;
		if (randtemp == 1)
			randtemp -= 0.001;
		long temp = Math.round(randtemp * 1000);

		return sb.toString() + temp;
	}

	public static String getSubFileDir() {
		return DateUtil.getDateTime("yyyyMMdd", new Date());
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param fileFileName
	 * @param uploadSubPath
	 * @return
	 * @throws Exception
	 */
	public synchronized String upload(File file, String fileFileName, String uploadSubPath)
			throws FileNotFoundException, IOException {

		String fileName = null;
		// the directory to upload to
		String realPath = uploadRoot + SEP + uploadSubPath + SEP;
		// write the file to the file specified
		File dirPath = new File(realPath);
		// 如果没有，建立目录
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		// retrieve the file data
		String fileType = StringUtil.toLowerCase(fileFileName.substring(StringUtil.lastIndexOf(fileFileName, ".") + 1));
		String randonFile = createFileName(fileType);

		if (file.length() > 0) {
			InputStream stream = new FileInputStream(file);
			// write the file to the file specified
			fileName = realPath + randonFile;
			OutputStream bos = new FileOutputStream(fileName);
			int bytesRead;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();
		}
		return randonFile;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param rootDir
	 *            根目录
	 * @param fileFileName
	 *            文件名
	 * @param uploadSubPath
	 *            子目录
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public synchronized String upload(File file, String rootDir, String fileFileName, String uploadSubPath)
			throws FileNotFoundException, IOException {
		String fileName = null;
		// the directory to upload to
		String realPath = uploadRoot + SEP + uploadSubPath + SEP;
		// write the file to the file specified
		File dirPath = new File(realPath);
		// 如果没有，建立目录
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		// retrieve the file data
		String fileType = StringUtil.toLowerCase(fileFileName.substring(StringUtil.lastIndexOf(fileFileName, ".") + 1));
		String randonFile = createFileName(fileType);

		if (file.length() > 0) {
			InputStream stream = new FileInputStream(file);
			// write the file to the file specified
			fileName = realPath + randonFile;
			OutputStream bos = new FileOutputStream(fileName);
			int bytesRead;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();
		}
		return randonFile;
	}

	/**
	 * 压缩上传
	 * 
	 * @param in
	 * @param uploadRoot
	 *            上传根目录
	 * @param uploadSubPath
	 *            上传子目录
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author linbo v1.1.1.1-RC1
	 */
	public synchronized void uploadZip(ZipInputStream in, String uploadRoot, String uploadSubPath)
			throws FileNotFoundException, IOException {

		ZipEntry entry = null;

		while ((entry = in.getNextEntry()) != null) {
			String realPath = uploadRoot + SEP + uploadSubPath + SEP;
			File dirPath = new File(realPath);

			if (entry.isDirectory()) {
				File create = new File(realPath);
				create.mkdirs();
			} else {
				String entryName = entry.getName();
				entryName = entryName.substring(entryName.lastIndexOf("/") + 1, entryName.length());
				entryName = entryName.substring(0, entryName.lastIndexOf("."));
				File create = new File(dirPath, entryName + ".jpg");
				File parent = create.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(create);
					IOUtils.copy(in, fos);
				} finally {
					if (fos != null) {
						fos.close();
					}
				}
			}
		}

	}

	/**
	 * 压缩上传
	 * 
	 * @param in
	 * @param uploadSubPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author linbo v1.1.1.1-RC1
	 */
	public synchronized void uploadZip(ZipInputStream in, String uploadSubPath)
			throws FileNotFoundException, IOException {

		ZipEntry entry = null;

		while ((entry = in.getNextEntry()) != null) {
			String realPath = uploadRoot + SEP + uploadSubPath + SEP;
			File dirPath = new File(realPath);

			if (entry.isDirectory()) {
				File create = new File(realPath);
				create.mkdirs();
			} else {
				String entryName = entry.getName();
				entryName = entryName.substring(entryName.lastIndexOf("/") + 1, entryName.length());
				entryName = entryName.substring(0, entryName.lastIndexOf("."));
				File create = new File(dirPath, entryName + ".jpg");
				File parent = create.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(create);
					IOUtils.copy(in, fos);
				} finally {
					if (fos != null) {
						fos.close();
					}
				}
			}
		}

	}
}
