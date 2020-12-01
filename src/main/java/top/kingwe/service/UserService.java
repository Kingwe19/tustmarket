package top.kingwe.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestParam;
import top.kingwe.mapper.UserMapper;
import top.kingwe.domain.User;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 登录验证方法
     * @param user
     * @return User对象，空为失败
     */
    public User login(User user){
        User userFound = userMapper.selectByUsernameTel(user.getUsernameTel());
        if(userFound == null){
            return new User();
        }else if(userFound.getPassword().equals(user.getPassword())){
            return userFound;
        }
        return new User();
    }

    /**
     * 注册验证方法
     * @param user
     * @return 1为成功注册，0为失败
     */
    public int register(User user){
        User userFound = userMapper.selectByUsernameTel(user.getUsernameTel());
        //找到空对象，数据库中无对应记录，则可以成功进行注册操作
        if(userFound == null){
            userMapper.insertSelective(user);
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param userId
     * @return 用户信息
     */
    public User getInfo(int userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    public int setInfo(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

}


