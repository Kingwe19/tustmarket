package top.kingwe.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Usercollection;

@Mapper
public interface UsercollectionMapper {
    int insert(Usercollection record);

    int insertSelective(Usercollection record);

    List<Usercollection> selectAllByUserId(@Param("userId")Integer userId);

    int deleteByUserIdAndGoodsId(@Param("userId")Integer userId,@Param("goodsId")String goodsId);

    int deleteByUserId(@Param("userId")Integer userId);

    Usercollection selectByUserIdAndGoodsId(@Param("userId")Integer userId,@Param("goodsId")String goodsId);


}