package org.sxyxhj.orderservice.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: mq-transaction
 * @description: 创建事务消息发送者
 * @author: sxyxhj
 * @create: 2021-12-28 13:58
 **/
@Component
@Slf4j
public class TransactionProducer {
    private TransactionMQProducer producer;

    private String producerGroup="order_trans_group";

    //创建线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(50));

    // 用于监听本地事务和事务状态
    @Autowired
    OrderTransactionListener orderTransactionListener;

    @PostConstruct
    public void init(){
        producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setExecutorService(executor);
        producer.setTransactionListener(orderTransactionListener);

        this.start();
    }

    private void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            log.error("producer启动失败");
        }
    }

    //事务消息发送
    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic,data.getBytes());
        return this.producer.sendMessageInTransaction(message,null);
    }

}
