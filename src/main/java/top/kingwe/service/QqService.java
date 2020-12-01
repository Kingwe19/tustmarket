package top.kingwe.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.kingwe.domain.Qq;
import top.kingwe.mapper.QqMapper;

@Service
public class QqService {

    @Resource
    private QqMapper qqMapper;


    public int insert(Qq record) {
        return qqMapper.insert(record);
    }


    public int insertSelective(Qq record) {
        return qqMapper.insertSelective(record);
    }

}


