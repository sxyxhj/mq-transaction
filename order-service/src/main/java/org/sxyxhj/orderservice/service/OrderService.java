package org.sxyxhj.orderservice.service;

import org.apache.rocketmq.client.exception.MQClientException;
import org.sxyxhj.orderservice.dto.OrderInfo;

public interface OrderService {
    public void createOrder(OrderInfo orderInfo, String transactionId);
    public void createOrder(OrderInfo order) throws MQClientException;
}
