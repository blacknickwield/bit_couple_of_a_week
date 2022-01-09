package com.gjf.bit_couple_of_a_week.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseResult {

    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public ResponseResult code(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseResult message(String message) {
        this.message = message;
        return this;
    }

    public ResponseResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

//    public ResponseResult data()
}
