package com.atguigu.gmalllogger.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.util.GmallConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: gmall
 * @description: 页面跳转
 * @author: li chao
 * @create: 2019-07-22 12:45
 * @Version 1.0
 */

@RestController  // 等价于: @Controller + @ResponseBody
public class LoggerController {

    //    @RequestMapping(value = "/log", method = RequestMethod.POST)
    //    @ResponseBody  //表示返回值是一个 字符串, 而不是 页面名


    @PostMapping("/log") // 等价于: @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String doLog(@RequestParam("log") String log){
        //1.日志中添加时间戳
        JSONObject addts = addts(log);

        //2.日志存到本地文件
        logAddLocal(addts);

        //3.日志存到kafka
        sendToKafka(addts);

        return "success";
    }



    //向kafka中添加数据
    @Autowired
    KafkaTemplate<String,String>  template;
    private void sendToKafka(JSONObject logTs) {
        String topic = GmallConstant.TOPIC_EVENT;
        if (logTs.getString("type").equals("startup")){
            topic=GmallConstant.TOPIC_STARTUP;
        }
        template.send(topic,logTs.toString());
    }



    //本地添加文件
    Logger logeer =LoggerFactory.getLogger(LoggerController.class);

    private void logAddLocal(JSONObject logTs) {
        logeer.info(logTs.toString());
    }

    //对json中添加时间戳
    private JSONObject addts(String log) {
        //转换json对象
        JSONObject jsonObject = JSON.parseObject(log);
        jsonObject.put("ts",System.currentTimeMillis());
        return jsonObject;
    }


}