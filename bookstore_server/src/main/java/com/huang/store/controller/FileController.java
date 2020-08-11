package com.huang.store.controller;

import com.huang.store.entity.book.BookImg;
import com.huang.store.service.imp.BookService;
import com.huang.store.util.FileUtil;
import com.huang.store.util.ResultUtil;
import com.huang.store.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@ResponseBody
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private String basePath="D://ITsoftware//IDEA//data//Vue//book_01//";
    private String bookPath="static//image//book01//";

    @Autowired
    @Qualifier("firstVersion")
    BookService bookService;

    /**
     * 上传图书图片
     * @param map
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadBookImg")
    public Map<String,Object> upload(@RequestParam Map<String,String> map,@RequestParam("file") MultipartFile file) {
        System.out.println("上传图片起作用！");
        Map<String,Object> m = new HashMap<>();
        String isbn = "";
        if(map.size() >0){
            try{
                isbn = map.get("isbn").toString();
            }catch (Exception e){
                return ResultUtil.resultCode(500,"上传图片失败");
            }
        }
        String path = basePath+bookPath;
        String imgName = UploadUtil.uploadFile(file,path);
        BookImg bookImg = new BookImg();
        bookImg.setImgSrc(bookPath+imgName);
        bookImg.setIsbn(isbn);
        if(bookService.addBookImg(bookImg)>0){
            return ResultUtil.resultCode(200,"上传图片成功");
        }
        return ResultUtil.resultCode(500,"上传图片失败");
    }

    /**
     * 删除某张图片
     * @param url
     * @param isbn
     * @return
     */
    @GetMapping("/delOneImg")
    public Map<String,Object> delImg(@RequestParam(value = "url") String url,
                                     @RequestParam(value = "isbn")String isbn){
        System.out.println("删除图片");
        System.out.println("bookId:"+isbn);
        String path = basePath+url;
        System.out.println("删除的图片的路径是："+path);
        FileUtil.delOneImg(path);
        if(bookService.deleteOneBookImg(isbn,url)>0){
            return ResultUtil.resultCode(200,"删除图片成功");
        }
        return ResultUtil.resultCode(200,"删除图片失败");
    }























    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        System.out.println("多文件上传起作用了");
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String id = UUID.randomUUID().toString();
                    id = id.replaceAll("\\-", "");
                    byte[] bytes = file.getBytes();
                    String fileName = file.getOriginalFilename();
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    String path = bookPath + id + suffixName;
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(path)));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传图片成功！";
    }

    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始文件下载");
        String fileName = "huang.txt";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("D://other//image/huang.txt");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }




//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public String uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] file) {
//        String isbn = "";
//        System.out.println("isbn"+isbn);
//        if(map.size()>0){
//            isbn = map.get("isbn").toString();
//        }
//        if(file.length<1){
//            System.out.println("必须有一个文佳");
//        }else{
//            for(int i=0;i<file.length;i++){
//                String id = UUID.randomUUID().toString();
//                id = id.replaceAll("\\-","");
//                String fileName = file[i].getOriginalFilename();
//                String suffixName = fileName.substring(fileName.lastIndexOf("."));
//                String path = filePath + id +suffixName;
//                File dest = new File(path);
//                if(!dest.getParentFile().exists()){
//                    dest.getParentFile().mkdirs();//新建文件夹
//                }
//                try {
//                    file[i].transferTo(dest);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return "上传图片成功";
//        }
//        return "上传图片失败";
//    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] file) {
        String  fileId;
        if(map.size() >0){
            try{
                fileId = map.get("isbn").toString();
                System.out.println(fileId);
            }catch (Exception e){
                System.out.println("出错了");
            }
        }
        if(file.length<1){
            System.out.println("文件的大小小于0");
        }else{
            for(int i=0;i<file.length;i++){
                String id = UUID.randomUUID().toString();
                id = id.replaceAll("\\-","");
                String fileName = file[i].getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                String path = bookPath + id +suffixName;
                File dest = new File(path);
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdirs();//新建文件夹
                }
                try {
                    file[i].transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "上传成功";
        }
        return "哈哈哈哈";
    }
}
