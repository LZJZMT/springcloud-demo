package com.lzj.auth.service.impl;

import com.lzj.auth.entity.SysUser;
import com.lzj.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/7 11:26
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //TODO 根据业务写相应校验登陆代码
        SysUser tmpUser = new SysUser().setId(UUID.randomUUID().toString()).setAccount(s).setPassword(passwordEncoder.encode("qqqq"));
        return tmpUser;
    }
}
