package com.training.controller;

import com.training.response.ResponseResult;
import com.training.service.MessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Api(value="/message",tags="审核失败反馈信息相关的接口")
public class MessageController {
    @Autowired
    MessageService messageService;
    @GetMapping("/table-id")
    @ApiOperation("通过tableName和id获取审核失败的反馈消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="table",value="对应的表名"),
            @ApiImplicitParam(name="id",value="表内对应的id")
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "成功获取反馈"),
            @ApiResponse(code=500,message="反馈不存在")
    })
    public ResponseResult findMessageByNameAndId(@RequestParam("table")String tableName, @RequestParam("id")Long idInTable){
        return messageService.findAllByTableNameAndIdInTable(tableName,idInTable);
    }
}
