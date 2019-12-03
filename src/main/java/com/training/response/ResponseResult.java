package com.training.response;

public class ResponseResult {
    private Integer code;
    private String msg;
    private Object data;
    public ResponseResult(){
        code=200;msg="ok";data=null;
    }

    public ResponseResult(Object data){
        code=200;msg="ok";
        this.data=data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data=null;
    }

    public ResponseResult(Integer code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
