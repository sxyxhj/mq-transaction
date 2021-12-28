package org.sxyxhj.productservice.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sxyxhj.productservice.dto.OrderInfo;
import org.sxyxhj.productservice.service.InventoryService;

import java.util.List;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 17:02
 **/
@Component
@Slf4j
public class OrderListener implements MessageListenerConcurrently {

    @Autowired
    InventoryService inventoryService;
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        log.info("消费者线程监听到消息。");
        try{
            for (MessageExt message:list) {
                log.info("开始处理订单数据，准备减少库存....");
                OrderInfo order  = JSONObject.parseObject(message.getBody(), OrderInfo.class);
                inventoryService.decreaseQty(order);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e){
            log.error("处理消费者数据发生异常。{}",e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

    }
}
