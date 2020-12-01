package top.kingwe.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.kingwe.domain.Goods;
import top.kingwe.domain.Orders;
import top.kingwe.domain.Usercollection;
import top.kingwe.service.GoodsService;
import top.kingwe.service.OrdersService;
import top.kingwe.service.UsercollectionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class AliTest {

    private static Usercollection usercollection = new Usercollection();

    private final String APP_ID = "2016110200788108";
    private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNBwRmcx6pg62B299j9/w4kl1eFPjEYjkrD9CEhtWoDPaenfIsT8y3EnLvitky/xBjCe5FKX0mW9iJGx5wQ0/hY6holF/yMz3+o65gRXrXOFzOUkpX2zYjgSQoWoLd8y426HsFQ5oGfcJleImdElUu4PxC4C5wnUcx/0pe5AwsB3is8j/u93cL2X3vPNw1VIQn226441MU9A8SLN26ZOu6mDyJXmpzAYbYkRn9fZSQcUURWnmnUB5Owny1IN/R9F6lThMuNkkGpvYKNRNpmOd1XtsgdbT1u/nxLmcOj8PEFVadOFpC0YQiu8n67p7lC18YnN53nsdbzFzBn0KsIEXpAgMBAAECggEAE2AfbqPqVofM0saOjoGHmY4iXcar1u9Xw17M2yhbOldtmqVAnG3qRky2lcqRxLveWH0Iy1/D3f1IZq3fJLVayDCU9qlL9U6s+csrLfbdxavstDwCEsf/TCl1+kVVLYE3nDuci0CabMHib2Z8IaRfJ62petaqCrbRDUFxyk+yjGnUm+GP4IZLjbl8riQvL98a21o9ikaHnczR3pu7uzARaqkKhCuTAlNtmVyGRsCSenTBhSv4f97gZWe626QUGLg1zd8dIzr2kCPrtKz6gzGSq517JIVBDChvPGNrxacE0Lr9q0I5NFyiiwPRxROKB5OzgGWZH8cRvw6ZQ7GMBQwNwQKBgQDySmn4KCeuhlQCZZErzQPEQcL54ko5rkeysTIkahtENnUm0Nj9H2BLfkQi40X+ry569VMqY5dz4/Q/quzAJxIUxszwcY4hNT3bWGgGtR5Onh2empgMOzHv9ZjbKZhh0Tvt34a1JKCGhvRMxymtP5vYHa904HkB9jZiw9kVtgvz9QKBgQCVAc1u9ANpyUX0U6AqNS/OC3sIU+xoUYHzMoh5qDRexFKz/qGnnV6W/pN8hmeLGnzKgnRTlzcCUgJuh3KRQiHnb5gjAu/669agwxFsOyEniOR4ZndCj7xS28dau9TaZJT9qOKI1w8+q6JHKASoFv/8r2GZM/jONQO207qVZx9FpQKBgCjUaW8sNSOv91BAFWqwLcZQ3q6Fd8SoneR6/9wrFVU1aCCWaTqeoveV252VdyIoG0kVNmKaTdd1vp9MCoGHFAD0T2zldfIaqsXf7AfVVboh4xPrMVw+TXgGhy/Hnqo9PEAlvumftn11GI4nsdpEMdsFhZ/IhBvUhe2220pRV0pRAoGAHB2g88SbOETALF/zIsOyZ4CHEIU1YGGw0YDBe6MfbppAuLHdMtFg3cDfd2ms8/Rlg5il9nfb61YqG0z3cy01VIQcAs81FPWXI/JqwS9PDDSqV4WOD0Mr176cmSRD/4lnC43oYMWjnMGp9x838beVScqV8eB6m/4o20CMPlCc9/kCgYBgGhsla2/LIuRFN3tHymcp4SAQg2X3PrILttZOaci6uE3ku7eonurLHiW+d6ec5RmN6TKZbMxwaY8qPg0WOVWCPRAUAcHZ3sNq+Tx/B3h0HEwA02upn4V/916HfD1DceYCkPkAKr3woeKn51YGX4eR6+B5rG8cLlRJE+8SQ1rXlw==";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtDyhZWgr+kmAvdE8/8iyp5ujecYv4+3hTheSGzRV9SySvxYvthMbLUSa/tWVi9Mr0QGnwNIWN/P2iL7G7NOybGZaBLw31IpMBJeBceCgxAzcNfyNlhkUMvHmYQZozQUDpYCgQsCAVssiHxQ30kp7HuPYxVDwc7HBLOujAgYhPOpI3RoSdKG9k1DMCzX7SwAKPClrre1CJ9N3n0fJ08Mre71YIjrdQiBwxDTVANQogtL8m86ufeilV9L1LQuBYgyPXVzQHyDs0STEVSnVpdiBVAzF6QJl44RHX4Mw0CE5zlxIETHi3IWr+e48kLQK6SmZgbhDZtwi6Sm+bJTpVbVDLwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://公网地址/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8083/returnUrl";

    @Autowired
    UsercollectionService usercollectionService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("alipay")
    public void alipay(HttpServletResponse httpResponse,HttpServletRequest httpRequest) throws IOException {
        Goods goods = new Goods();
        //赋值goods
        goods.setGoodsDescription(httpRequest.getParameter("goodsDescription"));
        goods.setUserId(Integer.valueOf(httpRequest.getParameter("userId")));
        goods.setGoodsId(httpRequest.getParameter("goodsId"));
        goods.setGoodsPrice(Double.valueOf(httpRequest.getParameter("goodsPrice")));
        goods.setGoodsName(httpRequest.getParameter("goodsName"));
        //赋值usercollection
        usercollection.setGoodsId(goods.getGoodsId());
        usercollection.setUserId(goods.getUserId());
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id，我现在把它改成我的goodsId
        String out_trade_no = UUID.randomUUID().toString();//goods.getGoodsId();
        //付款金额，必填
        String total_amount =goods.getGoodsPrice().toString();
        //订单名称，必填
        String subject =goods.getGoodsName();
        //商品描述，可空
        String body = goods.getGoodsDescription();
        //商品的编号，可空，我现在把它设置成我的userId
        String goods_id = goods.getUserId().toString();
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"goods_id\":\""+ goods_id +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    //在付款成功后,浏览器会跳转到我们定义的同步回调页面,来验证是否付款成功等操作
    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public void returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        System.out.println("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            //付款时间
            String timestamp= new String(request.getParameter("timestamp").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+total_amount);
            System.out.println("这是我请求过来的userId="+usercollection.getUserId());
            System.out.println("这是我请求过来的goodsId="+usercollection.getGoodsId());

            //支付成功，修复支付状态,删除购物车表，添加订单表
            usercollectionService.deleteByUserIdAndGoodsId(usercollection);
            Orders orders = new Orders();
            orders.setAdminStatus(1);//同步调用是虚假的，正真的处理状态要看异步的参数，但是异步要公网地址的路径
            orders.setGoodsId(usercollection.getGoodsId());
            orders.setOrdersId(UUID.randomUUID().toString());
            orders.setOrdersStatus(1);//同步调用是虚假的，正真的支付成功失败要看异步的参数，但是异步要公网地址的路径
            orders.setPayId(usercollection.getUserId());
            orders.setPrice(Double.valueOf(total_amount));
            orders.setTime(timestamp);
            orders.setUserId(goodsService.findUserIdByGoodsId(usercollection.getGoodsId()));//根据usercollection.getGoodsId()去查商品表中的userId
            ordersService.insert(orders);
            response.sendRedirect("http://localhost:8080/#/person#ordersDivId");
            //return "http://localhost:8080/#/person#ordersDivId";//跳转付款成功页面
        }else{

           // return "no";//跳转付款失败页面
        }

    }

}
