package com.edward.mqcommon.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgReadOffsetRequest {
    private String topic;

    private Integer readOffset;
}
