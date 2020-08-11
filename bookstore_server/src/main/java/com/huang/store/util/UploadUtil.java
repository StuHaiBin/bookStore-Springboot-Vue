package com.huang.store.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: 黄龙
 * @date: 2020/8/10 20:23
 * @description: 上传文件工具类
 */
public class UploadUtil {
    private static final Logger log = LoggerFactory.getLogger(UploadUtil.class);
    public static String uploadFile(MultipartFile file,String filePath){
        String id = UUID.randomUUID().toString();
        id = id.replaceAll("\\-", "");
        try {
            if (file.isEmpty()) {
                System.out.println("===================上传图片失败！===============");
                return "上传失败";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String path = filePath + id + suffixName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
                System.out.println("=============创建新的文件夹=====================");
            }
            file.transferTo(dest);// 文件写入
            System.out.println("====================成功写入=======================");
            return id + suffixName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
