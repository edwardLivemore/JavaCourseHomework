package com.edward.mqcommon.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class MqMessage<T> {

    private HashMap<String, Object> headers;

    private T body;
}
