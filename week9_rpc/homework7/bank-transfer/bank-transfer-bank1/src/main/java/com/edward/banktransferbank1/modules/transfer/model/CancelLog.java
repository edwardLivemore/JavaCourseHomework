package com.edward.banktransferbank1.modules.transfer.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author eagle
 * @since 2022-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cancel_log")
public class CancelLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 事务id
     */
    private String txId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
