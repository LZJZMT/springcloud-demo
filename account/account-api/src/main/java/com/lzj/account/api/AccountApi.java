package com.lzj.account.api;

import com.lzj.common.ApplicationNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@FeignClient(name = ApplicationNameConstant.ACCOUNT_APP)
public interface AccountApi {

    @PostMapping("/account/debit")
    void debit(@RequestParam String userId,@RequestParam BigDecimal money);

}
