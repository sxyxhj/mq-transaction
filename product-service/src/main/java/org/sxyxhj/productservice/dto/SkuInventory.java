package org.sxyxhj.productservice.dto;

import lombok.Data;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 18:08
 **/
@Data
public class SkuInventory {
    private int id;
    private int skuNo;
    private int qty;
}
