package top.kingwe.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.mapper.OrdersMapper;
import top.kingwe.domain.Orders;

import java.util.List;

@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;


    public List<Orders> selectByUserId(int userId) {
        return ordersMapper.selectByUserId(userId);
    }

    public int insert(Orders orders) {
        return ordersMapper.insert(orders);
    }
}

