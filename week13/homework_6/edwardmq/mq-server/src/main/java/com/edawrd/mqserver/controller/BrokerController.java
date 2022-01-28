package com.edawrd.mqserver.controller;

import com.edawrd.mqserver.service.BrokerService;
import com.edward.mqcommon.entity.CommonResult;
import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import com.edward.mqcommon.req.MsgReadOffsetRequest;
import com.edward.mqcommon.req.MsgRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("broker")
public class BrokerController {
    @Autowired
    private BrokerService brokerService;

    @PostMapping("topic")
    public CommonResult<Void> createTopic(@RequestParam String topic) {
        brokerService.createTopic(topic);
        return CommonResult.success(null);
    }

    @PostMapping("msg")
    public CommonResult<Void> send(@RequestBody MsgRequest request) throws Exception {
        brokerService.send(request);
        return CommonResult.success(null);
    }

    @GetMapping("msg")
    public CommonResult<MqMessage<Order>> poll(@RequestParam String topic) throws Exception {
        return CommonResult.success(brokerService.poll(topic));
    }

    @PostMapping("readOffset")
    public CommonResult<Void> updateReadOffset(@RequestBody MsgReadOffsetRequest request) throws Exception {
        brokerService.updateReadOffset(request);
        return CommonResult.success(null);
    }

}
