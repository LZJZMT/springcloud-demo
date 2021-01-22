package com.lzj.account.controller;


import com.lzj.account.api.AccountApi;
import com.lzj.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
@RestController
@RequestMapping("/account")
public class AccountController implements AccountApi {

    @Autowired
    IAccountService accountService;

    @Override
    public void debit(String userId, int money) {

    }
}

