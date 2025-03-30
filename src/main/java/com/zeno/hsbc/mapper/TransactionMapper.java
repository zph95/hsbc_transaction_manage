package com.zeno.hsbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zeno.hsbc.entity.Transaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransactionMapper extends BaseMapper<Transaction> {

    String TABLE_NAME = "transaction";

    String INSERT_FIELDS = "user_id, tid, type, amount, currency, transaction_time, target_uid, link_key ";

    String SELECT_FIELDS = "id, user_id, tid, type, amount, currency, transaction_time, target_uid, link_key,created_time,modified_time";

    @Insert("INSERT INTO " + TABLE_NAME + " (" + INSERT_FIELDS + ") VALUES (#{userId}, #{tid}, #{type}, #{amount}, #{currency}, #{transactionTime}, #{targetUid}, #{linkKey})")
    int insertTransaction(Transaction transaction);

    @Select("SELECT " + SELECT_FIELDS + " FROM " + TABLE_NAME + " WHERE tid = #{tid}")
    Transaction getTransactionByTid(@Param("tid") String tid);

    @Select("SELECT " + SELECT_FIELDS + " FROM " + TABLE_NAME)
    List<Transaction> getAllTransaction();

    @Update("UPDATE " + TABLE_NAME + " SET user_id = #{userId}, type = #{type}, amount = #{amount}, currency = #{currency}, transaction_time = #{transactionTime}, target_uid = #{targetUid}, link_key = #{linkKey} WHERE tid = #{tid}")
    int updateTransactionStatus(Transaction transaction);

    @Delete("Delete  FROM " + TABLE_NAME + " WHERE tid = #{tid}")
    int deleteTransaction(@Param("tid") String tid);

}
