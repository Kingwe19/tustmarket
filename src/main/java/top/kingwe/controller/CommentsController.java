package top.kingwe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kingwe.domain.Goodscomments;
import top.kingwe.service.GoodscommentsService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    GoodscommentsService goodscommentsService;

    @RequestMapping("/submitFeedback")
    public Map<String,Integer> submitFeedback(@RequestBody Goodscomments goodscomments){
        goodscomments.setCommentsId(UUID.randomUUID().toString());
        int i = goodscommentsService.insert(goodscomments);
        Map<String,Integer> map = new HashMap<>();
        map.put("code",i);
        return map;
    }
}
