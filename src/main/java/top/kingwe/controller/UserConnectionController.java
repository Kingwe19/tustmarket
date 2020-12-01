package top.kingwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kingwe.domain.Goods;
import top.kingwe.domain.Usercollection;
import top.kingwe.mapper.GoodsMapper;
import top.kingwe.service.UsercollectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collection")
public class UserConnectionController {

    @Autowired
    private UsercollectionService usercollectionService;

    @Autowired
    private GoodsMapper goodsMapper;


    @PostMapping("/addOneToCollection")
    public Map<String,Integer> addOneToShopCar(@RequestBody Usercollection usercollection){
        Map<String,Integer> map = new HashMap<>();
        //找值，存在就返回非1,解决重复添加的问题
        Usercollection usercollection1 = usercollectionService.selectByUserIdAndGoodsId(usercollection.getUserId(), usercollection.getGoodsId());
        if(usercollection1 != null){
            map.put("code",0);
            return map;
        }

        int insert = usercollectionService.insert(usercollection);
        map.put("code",insert);
        //给商品表的Recommend属性+1
        Goods goods = goodsMapper.selectByPrimaryKey(usercollection.getGoodsId());
        goods.setGoodsRecommend(goods.getGoodsRecommend()+1);
        goodsMapper.updateByPrimaryKeySelective(goods);
        return map;
    }

    @GetMapping("/findAllByUid")
    public List<Usercollection> findAllByUid(@RequestParam int userId ){
        return usercollectionService.selectAllByUserId(userId);
    }

    @PostMapping("/deleteOneByUidAndGid")
    public Map<String,Integer> deleteOneByUidAndGid(@RequestBody Usercollection usercollection){
        Map<String,Integer> map = new HashMap<>();
        int i = usercollectionService.deleteByUserIdAndGoodsId(usercollection);
        map.put("code",i);
        return map;
    }

    @PostMapping("/deleteAllByUid")
    public Map<String,Integer> deleteAllByUid(@RequestBody Usercollection usercollection){
        Map<String,Integer> map = new HashMap<>();
        int i = usercollectionService.deleteAllByUerId(usercollection);
        map.put("code",i);
        return map;
    }
}
