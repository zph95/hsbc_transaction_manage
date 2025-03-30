package com.zeno.hsbc.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zeno.hsbc.exception.BusinessException;
import com.zeno.hsbc.exception.ExceptionType;
import com.zeno.hsbc.mapper.TransactionMapper;
import com.zeno.hsbc.vo.CreateTransaction;
import com.zeno.hsbc.vo.PageVo;
import com.zeno.hsbc.vo.TransactionVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zeno.hsbc.entity.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.zeno.hsbc.exception.ExceptionType.TRANSACTION_ALREADY_EXISTS;
import static com.zeno.hsbc.exception.ExceptionType.TRANSACTION_NOT_FOUND;

@Service
public class TransactionService {
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @CacheEvict(value = "transactionCache")
    @Transactional
    public Integer create(CreateTransaction createTransaction) {
        Transaction oldTransaction = transactionMapper.getTransactionByTid(createTransaction.getTid());
        if(oldTransaction != null) {
            throw new BusinessException(TRANSACTION_ALREADY_EXISTS.getCode(), TRANSACTION_ALREADY_EXISTS.getMessage());
        }
        Transaction transaction = new Transaction();
        transaction.setTid(createTransaction.getTid());
        transaction.setUserId(createTransaction.getUserId());
        transaction.setType(createTransaction.getType());
        transaction.setAmount(createTransaction.getAmount());
        transaction.setCurrency(createTransaction.getCurrency());
        transaction.setTransactionTime(createTransaction.getTransactionTime());
        transaction.setTargetUid(createTransaction.getTargetUid());
        transaction.setLinkKey(createTransaction.getLinkKey());
        return transactionMapper.insertTransaction(transaction);
    }

    @CacheEvict(value = "transactionCache")
    @Transactional
    public Integer modify(TransactionVo vo) {
        Transaction newTransaction = vo.toEntity();
        Transaction oldTransaction = transactionMapper.getTransactionByTid(newTransaction.getTid());
        if(oldTransaction == null) {
            throw new BusinessException(TRANSACTION_NOT_FOUND.getCode(), TRANSACTION_NOT_FOUND.getMessage());
        }
        newTransaction.setId(oldTransaction.getId());
        return transactionMapper.updateTransactionStatus(newTransaction);
    }

    @CacheEvict(value = "transactionCache")
    @Transactional
    public Integer deleteTransaction(String tid) {
        Transaction oldTransaction = transactionMapper.getTransactionByTid(tid);
        if(oldTransaction == null) {
            throw new BusinessException(TRANSACTION_NOT_FOUND.getCode(), TRANSACTION_NOT_FOUND.getMessage());
        }
        return transactionMapper.deleteTransaction(tid);
    }

    @Cacheable(value = "transactionCache")
    public List<TransactionVo> getAllTransactions() {
        return transactionMapper.getAllTransaction().stream().map(TransactionVo::new).toList();
    }

    @Cacheable(value = "transactionCache")
    public PageVo<TransactionVo> pageQueryTransaction(Long pageNum, Long pageSize, Boolean isAsc) {
        Page<Transaction> page = new Page<>(pageNum, pageSize,true);
        LambdaQueryWrapper<Transaction> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderBy(true, isAsc, Transaction::getTransactionTime);
        Page<Transaction> transactionPage = transactionMapper.selectPage(page, lambdaQueryWrapper);

        return new PageVo(transactionPage.getRecords().stream().map(TransactionVo::new).toList(), transactionPage.getTotal(), transactionPage.getCurrent(), transactionPage.getSize());

    }
}