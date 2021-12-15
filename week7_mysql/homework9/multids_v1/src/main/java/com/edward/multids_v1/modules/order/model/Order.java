package com.edward.multids_v1.modules.order.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author eagle
 * @since 2021-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`order`")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * sku_id集合
     */
    private String skuIds;

    /**
     * 数量集合
     */
    private String nums;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * (0:支付宝 1:微信 2:银行卡)
     */
    private Integer payMethod;

    /**
     * 银行卡号
     */
    private String bankAccount;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 物流单号集合
     */
    private String expressIds;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
