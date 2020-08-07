package com.weixin.smallpro.controller;

import com.weixin.smallpro.tools.XmlReplyUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WeChatAutoReply {

    private static Logger logger = Logger.getLogger(WeChatAutoReply.class);

    @RequestMapping(value = "/wechataccess", method = RequestMethod.POST)
    public void autoReply(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        /**
         * 接受xml
         */
        ServletInputStream inputStreamReader = request.getInputStream();
        SAXReader saxReader = new SAXReader();
        //通过ServletInputStream获取微信后台发给我的xml流对象。包装成dom4j支持解析的Document对象
        Document doc = saxReader.read(inputStreamReader);
        Element root = doc.getRootElement();
        List ele = root.elements();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < ele.size(); i++) {
            Element el = (Element) ele.get(i);
            map.put(el.getName(), el.getStringValue());
        }
        logger.info("用户发送了一条数据："+map);
        /**
         * 返回xml
         */
        response.setContentType("text/xml;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        XmlReplyUtil.xmlReplyUtil(out, map, "我不想跟你说话");
        out.close();
        logger.info("公众号返回了一条信息："+"我不想跟你说话");
    }
}
