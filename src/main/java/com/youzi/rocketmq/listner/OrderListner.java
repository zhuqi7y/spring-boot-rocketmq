package com.youzi.rocketmq.listner;

import com.youzi.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 顺序消费
 * @author: zhuqi
 * @date: 2020年09月01日 16:04
 */
@Component
@RocketMQMessageListener(topic = RocketmqConstant.TOPIC_ORDER, consumerGroup = RocketmqConstant.CONSUMER_ORDER_TEST,
        messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class OrderListner implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(OrderListner.class);

    @Override
    public void onMessage(String message) {
        logger.info(Thread.currentThread().getName() + "顺序消费接收: " + message);
    }

}
