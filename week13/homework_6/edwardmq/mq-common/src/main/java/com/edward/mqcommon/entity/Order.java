package com.edward.mqcommon.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;
    private String userId;
    private String productId;
    private Integer price;
    private LocalDateTime orderTime;
}
