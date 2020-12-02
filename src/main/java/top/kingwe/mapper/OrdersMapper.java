package top.kingwe.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Orders;

@Mapper
public interface OrdersMapper {
    int deleteByPrimaryKey(String ordersId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String ordersId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> selectByUserId(@Param("userId")Integer userId);

int deleteByGoodsId(@Param("goodsId")String goodsId);


}