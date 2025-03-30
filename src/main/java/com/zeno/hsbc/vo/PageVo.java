package com.zeno.hsbc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageVo <T> implements Serializable {

    private T rows;

    private Long total;

    private Long pageNum;

    private Long pageSize;

}
