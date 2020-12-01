package top.kingwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kingwe.domain.User;
import top.kingwe.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User UserFound = userService.login(user);
        return UserFound;
    }

    @PostMapping("/register")
    public Map<String,Integer> register(@RequestBody User user){
        int code = userService.register(user);
        Map<String,Integer> jsonMap = new HashMap<>();
        jsonMap.put("code",code);
        return jsonMap;
    }

    @GetMapping("/getInfo")
    public User getInfo(@RequestParam int userId){
        return userService.getInfo(userId);
    }

    @PostMapping("/setInfo")
    public Map<String,Integer> setInfo(@RequestBody User user){
        int code = userService.setInfo(user);
        Map<String,Integer> jsonMap = new HashMap<>();
        jsonMap.put("code",code);
        return jsonMap;
    }
}
