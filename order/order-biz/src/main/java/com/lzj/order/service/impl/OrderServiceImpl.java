package com.lzj.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.order.dao.OrderMapper;
import com.lzj.order.entity.OrderEntity;
import com.lzj.order.service.IOrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements IOrderService {

    @Override
    public OrderEntity createOrder(String userId, String commodityCode, int count, BigDecimal price) {
        BigDecimal money = price.multiply(BigDecimal.valueOf(count));
        OrderEntity orderEntity = new OrderEntity()
                .setUserId(userId).setCommodityCode(commodityCode)
                .setCount(count).setMoney(money.intValue());

        super.save(orderEntity);
        return orderEntity;
    }
}
