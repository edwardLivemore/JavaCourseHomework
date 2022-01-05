package com.edward.banktransferbank1.modules.transfer.model;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("confirm_log")
public class ConfirmLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 事务id
     */
    @TableId(value = "tx_id")
    private String txId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
