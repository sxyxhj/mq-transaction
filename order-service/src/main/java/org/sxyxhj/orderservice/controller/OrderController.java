package org.sxyxhj.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sxyxhj.orderservice.dto.OrderInfo;
import org.sxyxhj.orderservice.service.OrderService;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 13:50
 **/

@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping("/api/v1/order/submit")
    public void orderSubmit(@RequestBody OrderInfo orderInfo) throws MQClientException {
        log.info("开始创建订单");
        service.createOrder(orderInfo);
        log.info("结束创建订单");
    }

}
