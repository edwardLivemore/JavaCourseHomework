package com.edward.mqcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqMessage<T> {

    private HashMap<String, Object> headers;

    private T body;
}
