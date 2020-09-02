package com.youzi.rocketmq.listner;

import com.alibaba.fastjson.JSON;
import com.youzi.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 事务消费
 * @author: zhuqi
 * @date: 2020年09月01日 16:43
 */
@Component
@RocketMQMessageListener(topic = RocketmqConstant.TOPIC_TRANSACTION, consumerGroup = RocketmqConstant.CONSUMER_TRANSACTION_TEST,
        messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class TransactionListner implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(TransactionListner.class);

    @Override
    public void onMessage(String message) {
        logger.info("事务消费接收："+ JSON.toJSONString(message, true));
    }

}
