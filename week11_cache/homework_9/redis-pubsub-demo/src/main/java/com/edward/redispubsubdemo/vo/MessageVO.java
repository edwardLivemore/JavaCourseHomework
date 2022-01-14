package com.edward.redispubsubdemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {
    private String orderName;

    private Double amount;

    private Integer userId;

    private String orderTime;
}
