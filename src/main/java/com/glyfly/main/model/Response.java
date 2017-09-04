package com.glyfly.main.model;

/**
 * Created by Administrator on 2017/4/11.
 */
public class Response {
    private int code;
    private String msg;
    private Result result;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Result getResult() {
        return result;
    }
}
