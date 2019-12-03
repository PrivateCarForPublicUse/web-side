package com.training.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value="/",tags="页面跳转，测试用")
@Controller
public class PageController {
    @ApiOperation("跳转到用户首页")
    @GetMapping("/user/index")
    public String userIndexPage(){
        return "/user/front";
    }
}
