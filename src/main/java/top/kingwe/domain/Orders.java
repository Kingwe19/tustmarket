package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    /**
     * 订单ID（主键、随机码）
     */
    private String ordersId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 订单状态（1已完成、0未完成、-1有异议）
     */
    private Integer ordersStatus;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 支付ID
     */
    private Integer payId;

    /**
     * 处理状态（1已处理、0未处理）
     */
    private Integer adminStatus;

    private String time;

    private static final long serialVersionUID = 1L;
}