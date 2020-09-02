package com.youzi.rocketmq.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.youzi.rocketmq.common.RocketmqConstant;
import com.youzi.rocketmq.entity.Test;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * @author: zhuqi
 * @date: 2020年09月01日 15:52
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
    * @Description: 普通消费
    * @author: zhuqi
    * @date: 2020/9/1 15:54
    * @Return: java.lang.String
    */
    @RequestMapping("/test")
    public String test() {
        Test test = new Test();
        test.setUuid(IdUtil.fastUUID());
        test.setName("张三");
        test.setPhone("18888888888");
        rocketMQTemplate.convertAndSend(RocketmqConstant.TOPIC_TEST, test);
        return JSON.toJSONString(test, true);
    }

    /**
     * @Description: 顺序消费-通过haskey来区分发送到哪个queue中
     * @author: zhuqi
     * @date: 2020/9/1 15:54
     * @Return: java.lang.String
     */
    @RequestMapping("/orderHasKey")
    public String orderHasKey() {
        //通过hasKey确定在哪个queue
        for(int i=1; i<=5; i++){
            rocketMQTemplate.syncSendOrderly(RocketmqConstant.TOPIC_ORDER, "顺序消费orderOne:"+i, "orderOne");
        }
        for(int i=1; i<=5; i++){
            rocketMQTemplate.syncSendOrderly(RocketmqConstant.TOPIC_ORDER, "顺序消费orderTwo:"+i, "orderTwo");
        }
        return JSON.toJSONString("ok", true);
    }

    /**
     * @Description: 顺序消费-通过取出queue数量根据你的orderid进行取余来发送到哪个queue中
     * @author: zhuqi
     * @date: 2020/9/1 15:54
     * @Return: java.lang.String
     */
    @RequestMapping("/orderQueueNum")
    public String orderQueueNum() {
        rocketMQTemplate.setMessageQueueSelector((List<MessageQueue> mqs, Message msg, Object arg) -> {
            int queueNum = Integer.valueOf(String.valueOf(arg)) % mqs.size();
            System.out.println("队列id：" + queueNum + " 消息:" + new String(msg.getBody()));
            return mqs.get(queueNum);
        });
        for(int i=1; i<=10; i++){
            String msg = "type:" + i%4 + " value:" + i;
            rocketMQTemplate.syncSendOrderly(RocketmqConstant.TOPIC_ORDER, msg, String.valueOf(i));
        }
        return JSON.toJSONString("ok", true);
    }

    /**
     * @Description: 事务消费(需要注册一个类用@RocketMQTransactionListener注解，实现RocketMQLocalTransactionListener接口)
     * @author: zhuqi
     * @date: 2020/9/1 15:54
     * @Return: java.lang.String
     */
    @RequestMapping("/transaction")
    public String transaction() {
        try {
            rocketMQTemplate.sendMessageInTransaction(RocketmqConstant.TOPIC_TRANSACTION,
                    MessageBuilder.withPayload("事务消费测试信息").build(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString("ok", true);
    }

}
