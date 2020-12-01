package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goodsphoto implements Serializable {
    /**
     * 图片的id，自动递增
     */
    private String imageId;

    /**
     * 商品id，商品的唯一标志，但是一个商品可能有多个图，所以才有这张表
     */
    private String goodsId;

    /**
     * 商品图片的地址
     */
    private String imageUrl;

    private static final long serialVersionUID = 1L;
}