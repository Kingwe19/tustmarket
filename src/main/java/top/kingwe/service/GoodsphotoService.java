package top.kingwe.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.mapper.GoodsphotoMapper;
import top.kingwe.domain.Goodsphoto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsphotoService {

    @Resource
    private GoodsphotoMapper goodsphotoMapper;


    public int insert(Goodsphoto record) {
        return goodsphotoMapper.insert(record);
    }

    public Goodsphoto selectOneByGoodsId(String goodsId){
        return goodsphotoMapper.selectAllByGoodsId(goodsId).get(0);
    }

    public List<Goodsphoto> selectAllByGoodsId(String goodsId){
        return goodsphotoMapper.selectAllByGoodsId(goodsId);
    }


    public Map<String, String> selectAllMapByGoodsId(String goodsId) {
        List<Goodsphoto> goodsphotos = goodsphotoMapper.selectAllByGoodsId(goodsId);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        int i = 1;
        for (Goodsphoto list:goodsphotos) {
            stringStringHashMap.put("img"+i,list.getImageUrl());
            i++;
        }
        return stringStringHashMap;
    }

    public void delectByGoodsId(String goodsId) {
        goodsphotoMapper.deleteByGoodsId(goodsId);
    }
}


