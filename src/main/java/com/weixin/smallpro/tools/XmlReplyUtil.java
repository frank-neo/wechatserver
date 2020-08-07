package com.weixin.smallpro.tools;

import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;


/**
 * 这是微信公众号自动回复的xml生成工具
 * 将参数传入，封装为xml格式的流对象
 * 返回给调用者
 * @author Lee
 */
public class XmlReplyUtil {

    private static Logger logger = Logger.getLogger(XmlReplyUtil.class);

    public static void xmlReplyUtil(ServletOutputStream servletOutputStream, Map<String,String> map, String replycontent) {

        //创建输出流对象
        OutputStreamWriter ow = null;
        try {

            //将输出流写入到ServletOutputStream对象中，便于HttpServletResponse回传给微信后台；
            ow = new OutputStreamWriter(servletOutputStream,"UTF-8");

            ow.write("<xml>");
            ow.write("\r\n");
            //这里注意下发送方和接收方翻转
            String toUserName = map.get("FromUserName").toString();
            ow.write("<ToUserName>"+toUserName+"</ToUserName>");
            ow.write("\r\n");
            //这里注意下发送方和接收方翻转
            String fromUserName = map.get("ToUserName").toString();
            ow.write("<FromUserName>"+fromUserName+"</FromUserName>");
            ow.write("\r\n");
            String createTime = map.get("CreateTime").toString();
            ow.write("<CreateTime>"+createTime+"</CreateTime>");
            ow.write("\r\n");
            String msgType = map.get("MsgType").toString();
            ow.write("<MsgType>"+msgType+"</MsgType>");
            ow.write("\r\n");
            //设置回复内容
            ow.write("<Content>"+replycontent+"</Content>");
            ow.write("\r\n");
            String msgId = map.get("MsgId").toString();
            ow.write("<MsgId>"+msgId+"</MsgId>");
            ow.write("\r\n");
            ow.write("</xml>");
            ow.flush();

            logger.info("========》自动回复xml渲染成功！");
        } catch (Exception e) {
            logger.info("---------》自动回复xml渲染失败。。");
            e.printStackTrace();
        }finally {
            try {
                ow.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
