package com.training.service;

import com.training.dto.OssCallbackResult;
import com.training.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

public interface OssService {

     // 获取oss上传时携带的秘钥
    OssPolicyResult policy();

     //oss上传成功回调
    OssCallbackResult callback(HttpServletRequest request);
}
