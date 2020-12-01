package top.kingwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.kingwe.domain.Orders;
import top.kingwe.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersContorller {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/findAllByUid")
    public List<Orders> findAllByUid(@RequestParam int userId){
        return ordersService.selectByUserId(userId);
    }

}
