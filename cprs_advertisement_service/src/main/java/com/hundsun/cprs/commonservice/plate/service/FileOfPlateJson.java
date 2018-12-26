package com.hundsun.cprs.commonservice.plate.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.hundsun.cprs.commonservice.advertisement.common.exception.ServiceCodeException;

public class FileOfPlateJson {
    
    public  static String readFile(String filePath){
        
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ServiceCodeException(-1, "文件不存在！");
        }
        
        //文件读取
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            br = new BufferedReader(new FileReader(file));
            sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
        } catch (FileNotFoundException e) {
            throw new ServiceCodeException(-1, "文件不存在！");
        } catch (IOException e) {
            throw new ServiceCodeException(-1, "读取文件异常！");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
    
            } catch (IOException e) {
                throw new ServiceCodeException(-1, "读取文件异常！");
            }
        }
        return sb != null ? sb.toString() : null;
    }
    
}
