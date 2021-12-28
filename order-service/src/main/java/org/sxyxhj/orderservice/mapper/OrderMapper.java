package org.sxyxhj.orderservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.sxyxhj.orderservice.dto.OrderInfo;

public interface OrderMapper {
    @Insert("insert into order_info values(#{id},#{orderNo},#{sku},#{qty})")
    void createOrder(OrderInfo order);
}
