package com.hundsun.cprs.commonservice.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hundsun.jresplus.common.util.DateUtil;

@Service
public class FileUploadUtil {

    public static final String   SEP            = "/";
    public static final String   POINT          = ".";
    public static final String[] EXTARRAY       = { "jpg", "jpeg", "bmp", "png" };
    public static final String[] EXCEL_EXTARRAY = { "xls" };

    @Value("${file.upload.dir}")
    private  String   fileUploadDir;
    
    private static FileUploadUtil fileUploadUtil;

    public FileUploadUtil() {
        fileUploadUtil = this;
        fileUploadUtil.fileUploadDir = this.fileUploadDir;
    }

    public static final int      MAX_FILE_SIZE  = 3 * 1024 * 1024;                //最大文件尺寸200k

    protected static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    /**
     * 判断上传的文件大小是否小于指定值
     * @param file
     * @return
     */
    public static boolean ifFileSizePermitted(MultipartFile file) {
        return file.getSize() < MAX_FILE_SIZE ? true : false;
    }

    /**
     * 判断上传的文件扩展名是否合法
     * @param file
     * @return
     */
    public static boolean ifExtendNamePermitted(MultipartFile file) {
        String orgFileName = file.getOriginalFilename();
        if (StringUtil.isBlank(orgFileName)) {
            //如果传的是空文件
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
     * 上传文件
     * @param file
     * @param destDir
     * @return
     */
    public static String uploadCommonFile(MultipartFile file, String destDir) {
        String filePath = "";
        if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
            return filePath;
        }

        String orgFileName = file.getOriginalFilename();
        String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

        String newFileName = FileUploadUtil.createFileName(exts);
        String saveDir = fileUploadUtil.fileUploadDir + SEP + destDir + SEP + getSubFileDir();
        try {
            saveFile(file, saveDir, newFileName);
        } catch (IOException e) {
        	logger.error("FileUploadUtil.uploadCommonFile error", e);
        }
        filePath = destDir + SEP + getSubFileDir() + SEP + newFileName;
        return filePath;
    }

    /**
     * 上传文件
     * @param file
     * @param destDir
     * @return
     */
    public static String uploadFile(MultipartFile file, String destDir) {
        String filePath = "";
        if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
            return filePath;
        }

        String orgFileName = file.getOriginalFilename();
        String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

        if (!ifExtendNamePermitted(exts)) {
            //如果文件扩展名不在允许范围内
            return filePath;
        }

        String newFileName = FileUploadUtil.createFileName(exts);
        String saveDir = fileUploadUtil.fileUploadDir + SEP + destDir + SEP + getSubFileDir();
        try {
            saveFile(file, saveDir, newFileName);
        } catch (IOException e) {
        }
        filePath = destDir + SEP + getSubFileDir() + SEP + newFileName;
        return filePath;
    }

    private static void saveFile(MultipartFile file, String destDir, String newFileName)
                                                                                        throws IOException {
        if (!new File(destDir).exists()) {
            new File(destDir).mkdirs();
        }
        file.transferTo(new File(destDir + SEP + newFileName));
    }

    public static synchronized String createFileName(String exts) {
        Date date = new Date();
        String fileName = getRandomString(8) + String.valueOf(date.getTime()) + POINT
                          + StringUtil.toLowerCase(exts);
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
            fileName = preName + orgname.substring(0, i) + backName
                       + StringUtil.toLowerCase(orgname.substring(i));
        else
            fileName = preName + orgname.substring(0, i) + backName;

        return fileName;
    }

    private static synchronized String getRandomString(int size) {// 随机字符串
        char[] c = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g',
                'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
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

    public String getFileUploadDir() {
        return fileUploadDir;
    }

    public static String getSubFileDir() {
        return DateUtil.getDateTime("yyyyMMdd", new Date());
    }

    /**
     * 
     * @param file
     * @param fileFileName
     * @param uploadSubPath
     * @return
     * @throws Exception
     */
    public synchronized String upload(File file, String fileFileName, String uploadSubPath)
                                                                                           throws FileNotFoundException,
                                                                                           IOException {

        String fileName = null;
        // the directory to upload to
        String realPath = fileUploadDir + SEP + uploadSubPath + SEP;
        // write the file to the file specified
        File dirPath = new File(realPath);
        // 如果没有，建立目录
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        // retrieve the file data
        String fileType = StringUtil.toLowerCase(fileFileName.substring(StringUtil.lastIndexOf(
            fileFileName, ".") + 1));
        String randonFile = createFileName(fileType);

        if (file.length() > 0) {
            InputStream stream = null;
            OutputStream bos = null;
            try {
                stream = new FileInputStream(file);
                // write the file to the file specified
                fileName = realPath + randonFile;
                bos = new FileOutputStream(fileName);
                int bytesRead;
                byte[] buffer = new byte[8192];
                while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }

            } catch (Exception e) {
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (Exception e) {
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
        return randonFile;
    }

    //    public synchronized void uploadZip(ZipInputStream in, String uploadSubPath)
    //                                                                               throws FileNotFoundException,
    //                                                                               IOException {
    //
    //        ZipEntry entry = null;
    //
    //        while ((entry = in.getNextEntry()) != null) {
    //            String realPath = fileUploadDir + SEP + uploadSubPath + SEP;
    //            File dirPath = new File(realPath);
    //
    //            if (entry.isDirectory()) {
    //                File create = new File(realPath);
    //                create.mkdirs();
    //            } else {
    //                String entryName = entry.getName();
    //                entryName = entryName.substring(entryName.lastIndexOf("/") + 1, entryName.length());
    //                entryName = entryName.substring(0, entryName.lastIndexOf("."));
    //                File create = new File(dirPath, entryName + ".jpg");
    //                File parent = create.getParentFile();
    //                if (!parent.exists()) {
    //                    parent.mkdirs();
    //                }
    //                FileOutputStream fos = null;
    //                try {
    //                    fos = new FileOutputStream(create);
    //                    IOUtils.copy(in, fos);
    //                } finally {
    //                    if (fos != null) {
    //                        try {
    //							fos.close();
    //						} catch (Exception e) {
    //						}
    //                    }
    //                }
    //            }
    //        }
    //
    //    }

    /**
     * 匹配自定义的文件类型
     * @author zhangchen 2012-8-29上午11:09:58
     */
    public static boolean ifExtendNamePermitted(MultipartFile multipartFile, String[] permittedName) {
        String file = multipartFile.getOriginalFilename();
        int point = file.lastIndexOf(".");
        String fileExtendName = "";
        if (point >= 0) {
            fileExtendName = file.substring(point + 1, file.length());
        }
        for (String extentedName : permittedName) {
            if (extentedName.equalsIgnoreCase(fileExtendName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 自定义类型文件的上传
     * @author zhangchen 2012-8-29下午02:54:12
     */
    public String uploadCustomFile(MultipartFile file, String destDir) {
        String filePath = "";
        if (file == null || StringUtil.isEmpty(file.getOriginalFilename())) {
            return filePath;
        }

        String orgFileName = file.getOriginalFilename();
        String exts = orgFileName.substring(orgFileName.lastIndexOf(".") + 1, orgFileName.length());

        String newFileName = FileUploadUtil.createFileName(exts);
        String saveDir = fileUploadDir + SEP + destDir + SEP + getSubFileDir();
        try {
            saveFile(file, saveDir, newFileName);
        } catch (IOException e) {
        }
        filePath = destDir + SEP + getSubFileDir() + SEP + newFileName;
        return filePath;
    }

    public static String getFileName(String savedFilePath, String proFileName, String... fileDirs) {
        return getFileDir(savedFilePath, fileDirs) + getRandomFileName(proFileName);
    }

    public static String getFileDir(final String imageDir, String... fileDirs) {
        String dir = imageDir;
        for (String fileDir : fileDirs) {
            dir = dir + SEP + fileDir;
        }
        dir += SEP;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    public static String getRandomFileName(String proFileName) {
        int length = proFileName.lastIndexOf(POINT);
        if (length == -1) {
            return UUID.randomUUID().toString();
        }
        String suffix = proFileName.substring(length);
        return UUID.randomUUID().toString() + suffix;
    }
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public static String getCellValue(Cell cell) {
    	if (cell != null) {
    		cell.setCellType(Cell.CELL_TYPE_STRING);
        	return cell.getStringCellValue();
		}
    	return null;
    }
}
