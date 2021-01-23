package com.lzj.order.controller;


import com.lzj.account.api.AccountApi;
import com.lzj.order.api.OrderApi;
import com.lzj.order.entity.OrderEntity;
import com.lzj.order.service.IOrderService;
import com.lzj.storage.api.StorageApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController implements OrderApi {

    @Autowired
    AccountApi accountApi;
    @Autowired
    StorageApi storageApi;
    @Autowired
    IOrderService orderService;

    private final static String USER_ID = "lzj";
    private final static String COMMODITY_CODE = "MAC_A";
    private final static BigDecimal PRICE = new BigDecimal("100.00");

    @PostMapping("/createOrder")
    @Override
    public void createOrder(@RequestParam String commodityCode,@RequestParam int count) {
        OrderEntity order = orderService.createOrder(USER_ID, COMMODITY_CODE, count, PRICE);
        log.warn("用户{}已创建订单{},订单内容：商品{}单价{}数量{}",USER_ID,order.getId(),COMMODITY_CODE,PRICE,count);

        BigDecimal money = PRICE.multiply(BigDecimal.valueOf(count));
        accountApi.debit(USER_ID,money);
        log.warn("用户{}已扣减🐟额{}",USER_ID,money);

        storageApi.deduct(COMMODITY_CODE,count);
        log.warn("商品{}已扣减库存{}",COMMODITY_CODE,count);
    }
}

