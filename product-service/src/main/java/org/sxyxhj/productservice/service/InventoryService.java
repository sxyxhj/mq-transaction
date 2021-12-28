package org.sxyxhj.productservice.service;

import org.sxyxhj.productservice.dto.OrderInfo;

public interface InventoryService {
    void decreaseQty(OrderInfo order);
}
