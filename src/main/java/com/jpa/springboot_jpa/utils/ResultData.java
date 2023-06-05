package com.jpa.springboot_jpa.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultData {

    private int status;
    private String msg;
    private Object data;

    public ResultData(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultData(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
