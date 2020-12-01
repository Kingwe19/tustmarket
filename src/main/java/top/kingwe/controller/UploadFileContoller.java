package top.kingwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.kingwe.domain.Goodsphoto;
import top.kingwe.service.GoodsphotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadFileContoller {

    @Autowired
    GoodsphotoService goodsphotoService;

    @PostMapping("/uploadFile")
    Map<String,Integer> uploadFile(@RequestParam Map<String, String> map, @RequestParam("file") MultipartFile[] files) throws Exception {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        //根据goodsId这个key获取要存在数据库中的value，也就是图片存在硬盘中的文件名（不包含后缀）
        String goodsId = map.get("goodsId");
        //存储图片的物理路径（图床位置）
        String pic_path = "D:\\java_workspace\\TUSTmarket\\vue_web\\src\\assets\\";

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            //新的图片名称
            //assert originalFilename != null;
            String newFileName = goodsId +UUID.randomUUID().toString().substring(1,5) + originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileUrl = pic_path + newFileName;
            //以字节数组形式返回图片的内容
            byte[] bytes = file.getBytes();
            //将图片写入指定文件中
            new FileOutputStream(fileUrl).write(bytes);
            // 将上传的文件移动到指定文件夹
            /*try {
                file.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }*/


            Goodsphoto goodsphoto = new Goodsphoto();
            goodsphoto.setGoodsId(goodsId);
            goodsphoto.setImageId(newFileName);
            goodsphoto.setImageUrl("http://localhost:9090/"+newFileName);
            int count = goodsphotoService.insert(goodsphoto);
            if (count == 0){
                stringIntegerHashMap.put("code",0);
                return stringIntegerHashMap;
            }
          /*  //此处代码在大数据情况下有逻辑错误，后期应该解决维护
            int count = goodsUrlService.saveGoodsUrl(new Goodsurl(goodsId, fileUrl, UUID.randomUUID().toString()));
            if (count == 0)
                throw new Exception("图片保存失败");*/
        }

        stringIntegerHashMap.put("code",1);
        return stringIntegerHashMap;
    }
}
