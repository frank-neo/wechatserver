package com.weixin.smallpro.controller;

import com.weixin.smallpro.tools.Sha1Unit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerAccess {

    @RequestMapping(value = "/wechataccess",method = RequestMethod.GET)
    public String wechataccess(String signature,String timestamp,String nonce,String echostr){

        System.out.println("signature ====>"+signature+",timestamp ====>"+timestamp+",nonce ====>"+nonce+",echostr ====>"+echostr);

        if (Sha1Unit.checkSignature(signature, timestamp, nonce)) {
            System.out.print("校验通过echostr返回：=====>"+echostr); // 校验通过，原样返回echostr参数内容
            return echostr;
        }
        return "wrong ServerAccess!";
    }
}
