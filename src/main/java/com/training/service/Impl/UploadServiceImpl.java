package com.training.service.Impl;

import com.training.Util.FtpUtil;
import com.training.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    FtpUtil ftpUtil;

    @Override
    public Map<String, String> upfile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "上传文件失败");
        String fileName = file.getOriginalFilename();//获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件的后缀名
//这里有一个比较重要的信息。因为文件上传如果就是以他的中文名字的话下载下来是为空的就是文件里什么都没有。
//所以在文件上传的时候我先保存了文件信息，然后通过得到文件名去查找这个文件的Id，以他的Id加上后缀作为新的文件名存到ftp服务器中
//        Resource resource = repository.findResourceByName(fileName);
//上面这个方法就是springdatajpa，不熟悉的同学可以去看博主上一篇文章，或者用你自己的方法取得文件id
//        fileName = resource.getId()+suffixName;
        fileName="file"+suffixName;
        //上传的文件名也需要加上后缀，不然虚拟机不知道文件格式
        InputStream inputStream = file.getInputStream();
        String filePath = null;
//关于ftp处理文件上传下载这里单独写了一个工具类ftpUtil，下面会写这个类
//@Autowired  private FtpUtil ftpUtil;service层上面引入了这个方法。
        Boolean flag = ftpUtil.uploadFile(fileName, inputStream);//主要就是这里实现了ftp的文件上传
        if (flag == true) {
            //log.info("上传文件成功!");
            filePath = ftpUtil.FTP_BASEPATH + fileName;
            map.put("code", "1");
            map.put("msg", "上传文件成功");
        }
        map.put("path", filePath);
        System.out.println(map);
        return map;
    }

    @Override
    public boolean downfile(String pathname, String filename, String localpath) throws IOException {
        boolean flag ;//同样这里也是用ftpUtil这个工具类去下载，传入文件的存放地址和文件名，和文件在服务器上的地址
        flag = ftpUtil.downloadFile(pathname, filename,localpath);
        return flag;
    }
}
