package com.edawrd.mqserver.controller;

import com.edawrd.mqserver.service.BrokerService;
import com.edward.mqcommon.entity.CommonResult;
import com.edward.mqcommon.entity.Mq;
import com.edward.mqcommon.entity.MqMessage;
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

//    @GetMapping("mq")
//    public CommonResult<Mq> getMq(@RequestParam String topic) {
//        return CommonResult.success(brokerService.getMq(topic));
//    }

    @GetMapping("msg")
    public CommonResult<MqMessage> poll(@RequestParam String topic) throws Exception {
        return CommonResult.success(brokerService.poll(topic));
    }
}
