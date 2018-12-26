package com.hundsun.cprs.commonservice.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {

    public static void outPutFile(String filePath, String filename, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            if (!(new File(filePath)).exists()) {
                return;
            }
            os = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=\""
                                                      + new String(filename.getBytes("GBK"),
                                                          "iso8859-1") + "\"");
            response.setContentType("text/plain");
            byte temp[] = new byte[1000];
            fis = new FileInputStream(filePath);
            int n = 0;
            while ((n = fis.read(temp)) != -1) {
                os.write(temp, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (os != null)
                    os.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
