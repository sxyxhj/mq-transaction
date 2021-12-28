package org.sxyxhj.orderservice.dto;

import lombok.Data;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 15:46
 **/
@Data
public class TransactionLog {
    private String id;
    private String business;
    private String foreignKey;
}
