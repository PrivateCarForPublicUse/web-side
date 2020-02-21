package com.training.controller;

import com.training.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RequestMapping("/file")
@Controller
public class FileController {
    @Autowired
    UploadService uploadService;

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, String> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        return uploadService.upfile(file, request);//这里调用service的upfile方法，传入两个参数。
    }

    @GetMapping("/download")
    @ResponseBody
    public boolean downfile(@RequestParam("path")String path){
//通过文件的id去得到这个文件的所有信息
//        Resource resource = resourceRepository.findResourceById(id);
//        String type =  resource.getType();//取出要下载的这个文件类型
//因为之前存的时候就是用id加后缀存在服务器中的，所以现在也要用同样的名字去查找
//        id =  id+type;//然后用ip加类型作为文件名去下载文件
        String id="1";
        boolean flag = false;
        System.out.println("文件名："+id);//这里可以输出一下有没有错
        //这里我要得到文件的id和type作为filename去下载,下载是通过filename去对应的目录查找文件名相同的文件下载下来，后台将文件名转换中文文件名。
        try {
            flag =  uploadService.downfile( "\\huanglong" , "file.jpg",path);//下载调用service层的方法
            System.out.println(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
