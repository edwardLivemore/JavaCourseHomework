package com.edward.banktransferbank2.modules.transfer.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
    @TableId(value = "tx_id")
    private String txId;

    /**
     * 创建时间
     */
    private Date createTime;


}
