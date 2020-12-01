package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    /**
     * 商品id，每个商品都是独一无二的(主键）
     */
    private String goodsId;

    /**
     * 商品的名字，可以重复
     */
    private String goodsName;

    /**
     * 商品描述
     */
    private String goodsDescription;

    /**
     * 商品价格
     */
    private Double goodsPrice;

    /**
     * 商品的所有者，user表的外键
     */
    private Integer userId;

    /**
     * 商品的热度，默认为30，每天减1，到0自动下架
     */
    private Integer goodsHot;

    /**
     * 每当有人把该商品加入购物车，则其goods_recommend加1，商品的推荐，就是根据这个值来的
     */
    private Integer goodsRecommend;

    /**
     * 商品新旧程度，100为全新，0为破烂不堪
     */
    private Integer goodsLevel;

    /**
     * 商品种类：1服饰，2配饰，3鞋，4美妆洗护，5电子数码，6运动户外，7图书文娱，8演出票，9生活旅行，10箱包烟酒，11宠物，12其他
     */
    private Integer goodsKinds;

    private static final long serialVersionUID = 1L;
}