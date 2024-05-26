package com.tencent.wx.handler;

import com.tencent.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ReceiveTextMsgHandler implements WxChatMsgHandler{

    private static final String KEY_WORD = "验证码";


    @Resource
    private RedisUtil redisUtil;

    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info("收到文本消息");
        String content = messageMap.get("Content");
        if(!KEY_WORD.equals(content)){
            return "";
        }

        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");

        Random random = new Random();
        int num = random.nextInt(1000);
        String numKey = redisUtil.buildKey(fromUserName, String.valueOf(num));
        redisUtil.setNx(numKey,"1",5L, TimeUnit.MINUTES);
        String numContent = "您当前的验证码是：" + num + "，5分钟内有效。";
        String replyContent = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + numContent + "]]></Content>\n" +
                "</xml>";
        return replyContent;
    }
}
