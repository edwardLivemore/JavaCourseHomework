package com.edward.mqcommon.req;

import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgRequest {
    private String topic;

    private MqMessage<Order> msg;
}
