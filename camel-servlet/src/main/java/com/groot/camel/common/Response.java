package com.groot.camel.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Response<T> {

    private int status = 200;
    private String message;
    private T data;
}
