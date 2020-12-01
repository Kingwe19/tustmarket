package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usercollection implements Serializable {
    /**
     * 收藏物品的用户的id
     */
    private Integer userId;

    /**
     * 被用户收藏的物品的id
     */
    private String goodsId;

    private static final long serialVersionUID = 1L;
}