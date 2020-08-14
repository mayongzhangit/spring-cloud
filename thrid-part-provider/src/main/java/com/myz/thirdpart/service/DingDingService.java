package com.myz.thirdpart.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.myz.thirdpart.util.DingDingUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author yzMa
 * @desc
 * @date 2020/8/14 14:37
 * @email 2641007740@qq.com
 */
@Service
public class DingDingService {

    public static void main(String[] args) throws Exception{

        String serviceUrl = "https://oapi.dingtalk.com/robot/send?access_token=8f552d7042fb5d2ac770426dc1b7131c21fc90297fe7b1d4792db7c9636ac555";

        Long timestamp = System.currentTimeMillis();
        String sign = DingDingUtil.getSign(timestamp);
        String finalUrl = serviceUrl+"&timestamp="+timestamp+"&sign="+sign;

        DingTalkClient client = new DefaultDingTalkClient(finalUrl);

        OapiRobotSendRequest textRequest = new OapiRobotSendRequest();
        textRequest.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("这是一个文本消息！！！");
        textRequest.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("15701311193"));
// isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(false);
        textRequest.setAt(at);
        OapiRobotSendResponse textResponse = client.execute(textRequest);

        OapiRobotSendRequest linkRequest = new OapiRobotSendRequest();
        linkRequest.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.baidu.com/");
        link.setPicUrl("");
        link.setTitle("这是链接消息跳转到baidu");
        link.setText("这个是链接消息，点击跳转到百度");
        linkRequest.setLink(link);
        OapiRobotSendResponse linkResponse = client.execute(linkRequest);

        OapiRobotSendRequest markDownRequest = new OapiRobotSendRequest();
        markDownRequest.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("markdonwn title");
        markdown.setText("# 一级标题\n" +
                "> 今天晚上有暴雨\n\n" +
                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
                "> ## 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        markDownRequest.setMarkdown(markdown);
        OapiRobotSendResponse markdownResponse = client.execute(markDownRequest);

    }
}
