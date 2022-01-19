package com.edward.orderconsumerdemo.modules.orders.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author eagle
 * @since 2022-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long productId;

    private Integer status;

    private Date createTime;

    private Date updateTime;


}
