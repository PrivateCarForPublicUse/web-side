package com.training.controller;

import com.training.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/ensure")
public class MyController {
    @Autowired
    UploadService uploadService;
    //上传文件
    @PostMapping("/uploadwork")
    public String uploadWork(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");

        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if(true){// ("DOCX".equals(type.toUpperCase())||"DOC".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = user + "_" + fileName;

                    // 设置存放图片文件的路径
                    path = trueFileName;//realPath+"/workplace/classwork/" + trueFileName;
                    File dest = new File(path);
                    //判断文件父目录是否存在
//                    if (!dest.getParentFile().exists()) {
//                        dest.getParentFile().mkdir();
//                    }

                    File f=new File("/image/"+trueFileName);
                    if (!f.getParentFile().exists()) {
                        f.getParentFile().mkdir();
                    }
                    uploadService.upfile(file,request);
                    file.transferTo(dest);

                    return trueFileName;
                }else {
                    return "error";
                }
            }else {
                return "error";
            }
        }else {
            return "error";
        }
    }
}