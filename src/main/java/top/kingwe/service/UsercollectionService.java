package top.kingwe.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.mapper.UsercollectionMapper;
import top.kingwe.domain.Usercollection;

import java.util.List;

@Service
public class UsercollectionService {

    @Resource
    private UsercollectionMapper usercollectionMapper;


    public int insert(Usercollection record) {
        return usercollectionMapper.insert(record);
    }

    public int insertSelective(Usercollection record) {
        return usercollectionMapper.insertSelective(record);
    }


    public List<Usercollection> selectAllByUserId(int userId) {
        return usercollectionMapper.selectAllByUserId(userId);
    }

    public int deleteByUserIdAndGoodsId(Usercollection usercollection) {
        return usercollectionMapper.deleteByUserIdAndGoodsId(usercollection.getUserId(),usercollection.getGoodsId());
    }

    public int deleteAllByUerId(Usercollection usercollection) {
        return usercollectionMapper.deleteByUserId(usercollection.getUserId());
    }

    public Usercollection selectByUserIdAndGoodsId(Integer userId, String goodsId) {
       return usercollectionMapper.selectByUserIdAndGoodsId(userId,goodsId);
    }
}


