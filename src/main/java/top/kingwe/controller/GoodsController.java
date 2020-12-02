package top.kingwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kingwe.domain.Goods;
import top.kingwe.domain.Goodsphoto;
import top.kingwe.domain.User;
import top.kingwe.domain.Usercollection;
import top.kingwe.mapper.GoodsMapper;
import top.kingwe.mapper.GoodsphotoMapper;
import top.kingwe.mapper.OrdersMapper;
import top.kingwe.mapper.UsercollectionMapper;
import top.kingwe.service.GoodsService;
import top.kingwe.service.GoodsphotoService;
import top.kingwe.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Resource
    private GoodsphotoService goodsphotoService;

    @Autowired
    private UsercollectionMapper usercollectionMapper;

    @Autowired
    private OrdersMapper ordersMapper;


    @GetMapping("/getRandomGoodsId")
    public Map<String,String> getRandomGoodsId() {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsId", UUID.randomUUID().toString());
        return map;
        //response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    @PostMapping("/createGoods")
    public Map<String,Integer> createGoods(@RequestBody Goods goods){
        int i = goodsService.insertSelective(goods);
        Map<String,Integer> map = new HashMap<>();
        map.put("code",i);
        return map;
    }

    @GetMapping("/getIndexGoods/{index}")
    public List<Goods> getIndexGoods(@PathVariable int index){
        return goodsService.getIndexGoods(index);
    }

    @GetMapping("/getSelectGoods")
    public List<Goods> getSelectGoods(int selectNum){return goodsService.getSelectGoods(selectNum);}

    @GetMapping("/getGoodsMainImg")
    public Goodsphoto getGoodsMainImg(String goodsId){
        return goodsphotoService.selectOneByGoodsId(goodsId);
   }

    public List<Goodsphoto> select13123(String goodsId){
       return goodsphotoService.selectAllByGoodsId(goodsId);
   }

   @GetMapping("/getGoodsById")
    public Goods getGoodsById(@RequestParam String goodsId){
        return goodsService.getGoodsById(goodsId);
    }

    @GetMapping("/getGoodsImgMap")
    public Map<String,String> getGoodsImgMap(@RequestParam String goodsId){
       return goodsphotoService.selectAllMapByGoodsId(goodsId);
   }

   @GetMapping("/getMyGoods")
    List<Goods> getMyGoods(@RequestParam int userId){
        return goodsService.getMyGoods(userId);
   }

   @GetMapping("/getSearchGoods")
   List<Goods> getSearchGoods(@RequestParam String goodsName){
        return goodsService.selectAllByGoodsName(goodsName);

   }

    @PostMapping("/deleteMyGoodsAndUrls")
    public Map<String,Integer> deleteMyGoodsAndUrls(@RequestBody Usercollection usercollection){
        Map<String,Integer> map = new HashMap<>();
        int i = goodsService.deleteByPrimaryKey(usercollection.getGoodsId());
        map.put("code",i);
        usercollectionMapper.deleteByUserIdAndGoodsId(usercollection.getUserId(),usercollection.getGoodsId());
        goodsphotoService.delectByGoodsId(usercollection.getGoodsId());
        ordersMapper.deleteByGoodsId(usercollection.getGoodsId());
        return map;
    }

    @PostMapping("/saveGoodsDscripAndGoodsName")
    public Map<String,Integer> saveGoodsDscripAndGoodsName(@RequestBody Goods goods){
        Map<String,Integer> map = new HashMap<>();
        int i = goodsService.updateByPrimaryKeySelective(goods);
        map.put("code",i);
        return map;

    }
}
