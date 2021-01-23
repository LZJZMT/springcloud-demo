package com.lzj.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.account.dao.AccountMapper;
import com.lzj.account.entity.AccountEntity;
import com.lzj.account.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements IAccountService {

    @Transactional
    @Override
    public void debit(String userId, BigDecimal money){

        AccountEntity account = super.lambdaQuery()
                .eq(AccountEntity::getUserId, userId).last(" for update").one();
        if (account != null){
            if (account.getMoney() < money.intValue()){
                throw new RuntimeException("余额不足");
            }
            account.setMoney(account.getMoney() - money.intValue());
            super.updateById(account);
        }else {
            throw new RuntimeException("用户不存在");
        }

    }
}
