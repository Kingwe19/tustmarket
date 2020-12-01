package top.kingwe.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.mapper.GoodscommentsMapper;
import top.kingwe.domain.Goodscomments;

@Service
public class GoodscommentsService {

    @Resource
    private GoodscommentsMapper goodscommentsMapper;


    public int insert(Goodscomments record) {
        return goodscommentsMapper.insert(record);
    }


    public int updateByPrimaryKey(Goodscomments record) {
        return goodscommentsMapper.updateByPrimaryKey(record);
    }

    public int deleteByPrimaryKey(String commentsId) {
        return goodscommentsMapper.deleteByPrimaryKey(commentsId);
    }

    public int insertSelective(Goodscomments record) {
        return goodscommentsMapper.insertSelective(record);
    }

    public Goodscomments selectByPrimaryKey(String commentsId) {
        return goodscommentsMapper.selectByPrimaryKey(commentsId);
    }

    public int updateByPrimaryKeySelective(Goodscomments record) {
        return goodscommentsMapper.updateByPrimaryKeySelective(record);
    }
}





