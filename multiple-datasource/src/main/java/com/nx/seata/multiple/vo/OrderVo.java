package com.nx.seata.multiple.vo;

import lombok.Data;


@Data
public class OrderVo {
    private String userId;
    // 商品编号
    private String commodityCode;
    // 商品数量
    private Integer count;
    // 商品金额
    private Integer money;
}
