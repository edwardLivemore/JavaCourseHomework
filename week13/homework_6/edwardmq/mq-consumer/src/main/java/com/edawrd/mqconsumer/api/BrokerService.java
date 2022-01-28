package com.edawrd.mqconsumer.api;

import com.edward.mqcommon.entity.CommonResult;
import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import com.edward.mqcommon.req.MsgReadOffsetRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${broker.url}/broker", name = "broker")
public interface BrokerService {
    @GetMapping("msg")
    CommonResult<MqMessage<Order>> poll(@RequestParam String topic);

    @PostMapping("readOffset")
    CommonResult<Void> updateReadOffset(@RequestBody MsgReadOffsetRequest request);
}
