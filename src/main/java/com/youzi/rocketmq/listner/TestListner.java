package com.youzi.rocketmq.listner;

import com.alibaba.fastjson.JSON;
import com.youzi.rocketmq.common.RocketmqConstant;
import com.youzi.rocketmq.entity.Test;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 普通消费
 * @author: zhuqi
 * @date: 2020年09月01日 15:22
 */
@Component
@RocketMQMessageListener(topic = RocketmqConstant.TOPIC_TEST, consumerGroup = RocketmqConstant.CONSUMER_GROUP_TEST,
        messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class TestListner implements RocketMQListener<Test> {

    private Logger logger = LoggerFactory.getLogger(TestListner.class);

    @Override
    public void onMessage(Test test) {
        logger.info("普通消费接收："+JSON.toJSONString(test, true));
    }

}
