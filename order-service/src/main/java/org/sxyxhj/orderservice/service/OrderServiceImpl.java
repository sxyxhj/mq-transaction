package org.sxyxhj.orderservice.service;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sxyxhj.orderservice.dto.OrderInfo;
import org.sxyxhj.orderservice.dto.TransactionLog;
import org.sxyxhj.orderservice.mapper.OrderMapper;
import org.sxyxhj.orderservice.mapper.TransactionLogMapper;
import org.sxyxhj.orderservice.mq.TransactionProducer;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 13:53
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{


    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TransactionLogMapper transactionLogMapper;
    @Autowired
    TransactionProducer producer;

    Snowflake snowflake = new Snowflake(1,1);


    @Transactional
    @Override
    public void createOrder(OrderInfo orderInfo, String transactionId) {

        //1.创建订单
        orderMapper.createOrder(orderInfo);

        //2.写入事务日志
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setId(transactionId);
        transactionLog.setBusiness("order");
        transactionLog.setForeignKey(String.valueOf(orderInfo.getId()));
        transactionLogMapper.insert(transactionLog);

        log.info("订单创建完成。{}",orderInfo);
    }
    //前端调用，只用于向RocketMQ发送事务消息
    @Override
    public void createOrder(OrderInfo order) throws MQClientException {
        order.setId(snowflake.nextId());
        order.setOrderNo(snowflake.nextIdStr());
        producer.send(JSON.toJSONString(order),"order");
    }
}
