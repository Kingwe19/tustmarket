package top.kingwe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Goodscomments;

@Mapper
public interface GoodscommentsMapper {
    int deleteByPrimaryKey(String commentsId);

    int insert(Goodscomments record);

    int insertSelective(Goodscomments record);

    Goodscomments selectByPrimaryKey(String commentsId);

    int updateByPrimaryKeySelective(Goodscomments record);

    int updateByPrimaryKey(Goodscomments record);
}