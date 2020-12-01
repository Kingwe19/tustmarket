package top.kingwe.controller;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class TestMyProgram {

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user == null);
    }
}

@Mapper
class User{
    private boolean isLogin;
    private int userId;
    private String username;
    private String password;

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
