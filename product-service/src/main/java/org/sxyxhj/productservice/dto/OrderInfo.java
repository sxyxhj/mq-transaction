package org.sxyxhj.productservice.dto;

import lombok.Data;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 13:53
 **/
@Data
public class OrderInfo {
    private long id;
    private String orderNo;
    private int sku;
    private int qty;
}
