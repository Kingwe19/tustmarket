package top.kingwe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Wechat;

@Mapper
public interface WechatMapper {
    int deleteByPrimaryKey(Integer wechatId);

    int insert(Wechat record);

    int insertSelective(Wechat record);
}