package com.edward.shardingsphere_xa.modules.platformOrderItem.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author eagle
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("platform_order_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformOrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
