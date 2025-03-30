package com.zeno.hsbc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zeno.hsbc.vo.CreateTransaction;
import com.zeno.hsbc.vo.PageVo;
import com.zeno.hsbc.vo.Response;
import com.zeno.hsbc.vo.TransactionVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zeno.hsbc.service.TransactionService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@Validated
@RestController
@Tag(name = "交易管理接口", description = "提供交易的创建、修改、查询和删除功能")
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @Operation(summary = "创建交易", description = "创建一个新的交易记录")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Response<Integer>> create(@RequestBody @Validated CreateTransaction createTransaction) {
        int result = service.create(createTransaction);
        return new ResponseEntity<>(Response.success(result), HttpStatus.OK);
    }

    @Operation(summary = "更改交易", description = "修改现有的交易记录")
    @PostMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Integer>> modify(@RequestBody @Validated TransactionVo vo) {
        int result =  service.modify(vo);
        return new ResponseEntity<>(Response.success(result), HttpStatus.OK);
    }

    @Operation(summary = "删除交易", description = "根据交易ID删除交易记录")
    @DeleteMapping("/delete/{tid}")
    public  ResponseEntity<Response<String>> deleteTransaction(@PathVariable String tid) {
        service.deleteTransaction(tid);
        return new ResponseEntity<>(Response.success(tid), HttpStatus.OK);
    }

    @Operation(summary = "查询全部交易", description = "获取所有交易记录")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<TransactionVo>>> getAllTransactions() {
        List<TransactionVo> allData = service.getAllTransactions();
        return new ResponseEntity<>(Response.success(allData), HttpStatus.OK);
    }

    @Operation(summary = "分页查询交易", description = "分页查询交易记录")
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PageVo<TransactionVo>>> getTransactionsByPage(
            @RequestParam(value = "page", defaultValue = "1") long page,
            @RequestParam(value = "size", defaultValue = "10") long size,
            @RequestParam(value = "isAsc", defaultValue = "false") boolean isAsc){
        return new ResponseEntity<>(Response.success(service.pageQueryTransaction(page, size, isAsc)), HttpStatus.OK);
    }

}