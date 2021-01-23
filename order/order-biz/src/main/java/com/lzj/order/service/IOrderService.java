package com.lzj.order.service;

import com.lzj.order.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
public interface IOrderService extends IService<OrderEntity> {

    OrderEntity createOrder(String userId, String commodityCode, int count, BigDecimal price);
}
