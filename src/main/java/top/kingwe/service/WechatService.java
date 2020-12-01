package top.kingwe.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.domain.Wechat;
import top.kingwe.mapper.WechatMapper;

@Service
public class WechatService {

    @Resource
    private WechatMapper wechatMapper;


    public int deleteByPrimaryKey(Integer wechatId) {
        return wechatMapper.deleteByPrimaryKey(wechatId);
    }


    public int insert(Wechat record) {
        return wechatMapper.insert(record);
    }


    public int insertSelective(Wechat record) {
        return wechatMapper.insertSelective(record);
    }

}


