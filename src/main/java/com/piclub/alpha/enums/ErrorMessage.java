package com.piclub.alpha.enums;

public enum ErrorMessage {

    request_param_error("0100", "请求参数错误");

    private String code;
    private String desc;

    ErrorMessage(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
