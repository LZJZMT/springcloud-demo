package com.lzj.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.account.entity.AccountEntity;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzj
 * @since 2021-01-17
 */
public interface IAccountService extends IService<AccountEntity> {

    void debit(String userId, BigDecimal money);
}
