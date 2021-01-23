package com.lzj.order.api;


import com.lzj.common.ApplicationNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApplicationNameConstant.ORDER_APP)
public interface OrderApi {

    @PostMapping("/order/createOrder")
    void createOrder(@RequestParam String commodityCode, @RequestParam int count);

}
