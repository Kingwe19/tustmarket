package top.kingwe.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goodscomments implements Serializable {
    /**
     * 评论的唯一标识id（主键）
     */
    private String commentsId;

    /**
     * 评论人id可以找到对应所有信息
     */
    private Integer userId;

    /**
     * 被评论商品的id
     */
    private String goodsId;

    /**
     * 评论内容
     */
    private String goodsComments;

    /**
     * 反馈类型（1投诉、0建议、-1反馈）
     */
    private Integer commentsKinds;

    private static final long serialVersionUID = 1L;
}