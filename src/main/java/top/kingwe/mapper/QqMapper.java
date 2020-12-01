package top.kingwe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.kingwe.domain.Qq;

@Mapper
public interface QqMapper {
    int insert(Qq record);

    int insertSelective(Qq record);
}