package org.sxyxhj.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sxyxhj.orderservice.mapper.TransactionLogMapper;

/**
 * @program: mq-transaction
 * @description:
 * @author: sxyxhj
 * @create: 2021-12-28 16:47
 **/
@Service
@Slf4j
public class TransactionLogServiceImpl implements TransactionLogService{

    @Autowired
    TransactionLogMapper transactionLogMapper;
    @Override
    public int get(String transactionId) {
        return transactionLogMapper.get(transactionId);
    }
}
