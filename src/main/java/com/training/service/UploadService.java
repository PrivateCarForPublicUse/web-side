package com.training.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface UploadService {
    Map<String, String> upfile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException;
    public boolean downfile(String pathname, String filename, String localpath) throws IOException;
}
