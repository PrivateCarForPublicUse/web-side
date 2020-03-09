package com.training.controller;

import com.training.dto.OssCallbackResult;
import com.training.dto.OssPolicyResult;
import com.training.response.ResponseResult;
import com.training.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value="/aliyun/oss",tags="文件服务器管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation("获取上传文件所需秘钥")
    @GetMapping("/policy")
    public ResponseResult policy() {
        OssPolicyResult result = ossService.policy();
        return new ResponseResult(result);
    }

    @ApiOperation("oss上传成功回调")
    @PostMapping("/callback")
    public ResponseResult callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return new ResponseResult(ossCallbackResult);
    }

}
