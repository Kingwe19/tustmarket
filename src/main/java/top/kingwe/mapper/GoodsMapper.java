package top.kingwe.mapper;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAllOrderByGoodsRecommendDesc();

    List<Goods> findAllOrderByGoodsPriceAsc();

    List<Goods> selectAllByGoodsLevel(@Param("goodsLevel")Integer goodsLevel);

    List<Goods> selectAllByUserId(@Param("userId")Integer userId);

    List<Goods> selectAllByGoodsName(@Param("goodsName")String goodsName);

    Integer findUserIdByGoodsId(@Param("goodsId")String goodsId);

    List<Goods> selectAllByGoodsKinds(@Param("goodsKinds")Integer goodsKinds);

    List<Goods> selectAllByGoodsNameLike(@Param("likeGoodsName")String likeGoodsName);



}