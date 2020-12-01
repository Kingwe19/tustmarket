package top.kingwe.service;

import lombok.ToString;
import org.junit.Test;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.domain.Goods;
import top.kingwe.mapper.GoodsMapper;
import top.kingwe.mapper.GoodsphotoMapper;

import java.util.List;
import java.util.Map;

@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;


    public int insertSelective(Goods record){
        return goodsMapper.insertSelective(record);
    }


    public List<Goods> getIndexGoods(int index) {
        List<Goods> list = null;
        //获取热门精品
        if (index == 1) {
            list = goodsMapper.findAllOrderByGoodsRecommendDesc();
        } else if (index == 2) {
            //获取低价好物
            list = goodsMapper.findAllOrderByGoodsPriceAsc();
        } else {
            //获取 9 新以上
            list = goodsMapper.selectAllByGoodsLevel(90);
        }
        return list;
    }

    public Goods getGoodsById(String goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    public List<Goods> getMyGoods(int userId) {
        return goodsMapper.selectAllByUserId(userId);
    }


    public List<Goods> selectAllByGoodsName(String goodsName) {
        return goodsMapper.selectAllByGoodsNameLike(goodsName);
    }

    public int deleteByPrimaryKey(String goodsId) {
        return goodsMapper.deleteByPrimaryKey(goodsId);
    }

    public int updateByPrimaryKeySelective(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    public int findUserIdByGoodsId(String goodsId) {
        return goodsMapper.findUserIdByGoodsId(goodsId);
    }

    public List<Goods> getSelectGoods(int selectNum) {
        return goodsMapper.selectAllByGoodsKinds(selectNum);
    }
}



