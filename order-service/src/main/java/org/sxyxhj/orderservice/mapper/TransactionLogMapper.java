package org.sxyxhj.orderservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.sxyxhj.orderservice.dto.TransactionLog;

public interface TransactionLogMapper {
    @Insert("insert into transaction_log values(#{id},#{business},#{foreignKey})")
    void insert(TransactionLog transactionLog);

    @Select("select id from transaction_log where id = #{id}")
    int get(String transactionId);
}
