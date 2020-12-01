package top.kingwe.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Goodsphoto;

@Mapper
public interface GoodsphotoMapper {
    int deleteByPrimaryKey(String imageId);

    int insert(Goodsphoto record);

    int insertSelective(Goodsphoto record);

    Goodsphoto selectByPrimaryKey(String imageId);

    int updateByPrimaryKeySelective(Goodsphoto record);

    int updateByPrimaryKey(Goodsphoto record);

    List<Goodsphoto> selectAllByGoodsId(@Param("goodsId")String goodsId);

    int deleteByGoodsId(@Param("goodsId")String goodsId);


}