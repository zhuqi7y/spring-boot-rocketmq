package com.youzi.rocketmq.listner;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @Description: 一个项目有且仅有一个@RocketMQTransactionListener注解
 * @author: zhuqi
 * @date: 2020年09月01日 17:26
 */
@RocketMQTransactionListener
public class GlobalTransactionListner implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            //return RocketMQLocalTransactionState.UNKNOWN;
            //return RocketMQLocalTransactionState.ROLLBACK;
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 本地事务的检查，检查本地事务是否成功
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        //return RocketMQLocalTransactionState.ROLLBACK;
        return RocketMQLocalTransactionState.COMMIT;
    }

}
