package org.sxyxhj.productservice.service;

import cn.hutool.core.lang.Snowflake;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sxyxhj.productservice.dto.OrderInfo;
import org.sxyxhj.productservice.dto.SkuInventory;
import org.sxyxhj.productservice.mapper.InventoryMapper;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 17:06
 **/
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    Snowflake snowflake = new Snowflake(1,1);

    @Autowired
    InventoryMapper inventoryMapper;

    @Override
    public void decreaseQty(OrderInfo order) {
        //查询库存
        SkuInventory skuInventory = inventoryMapper.get(order.getSku());
        log.info("当前的库存是：{}",skuInventory.getQty());

        skuInventory.setQty(skuInventory.getQty() - order.getQty());

        //更新库存
        log.info("开始更新库存");
        inventoryMapper.updateQty(skuInventory);
        log.info("结束更新库存");
    }
}
