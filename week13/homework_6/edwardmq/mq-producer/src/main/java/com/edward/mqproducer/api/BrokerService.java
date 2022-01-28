package com.edward.mqproducer.api;

import com.edward.mqcommon.entity.CommonResult;
import com.edward.mqcommon.req.MsgRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${broker.url}/broker", name = "broker")
public interface BrokerService {
    @PostMapping("topic")
    CommonResult<Void> createTopic(@RequestParam String topic);

    @PostMapping("msg")
    CommonResult<Void> send(@RequestBody MsgRequest request);
}
