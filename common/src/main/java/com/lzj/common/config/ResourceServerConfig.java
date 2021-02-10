package com.lzj.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;
import sun.tools.jstat.Token;

import java.io.IOException;

/**
 * @author lzj
 */
@Slf4j
@Configuration
@EnableResourceServer
@ConditionalOnClass(ResourceServerConfigurerAdapter.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String[] ANT_MATCHERS = {"/actuator/**", "/v2/api-docs-ext"};

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    @ConditionalOnMissingBean(JwtAccessTokenConverter.class)
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 非对称加密方式
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String publicKey;
        try {
            publicKey = new String(
                    FileCopyUtils.copyToByteArray(new ClassPathResource("pubkey.pem").getInputStream()));
        }
        catch (IOException e) {
            throw new RuntimeException("设置jwt公钥失败");
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resource) {
        // 自定义异常
        resource.tokenStore(tokenStore());
    }
    
    @Override
    public void configure(HttpSecurity http)
        throws Exception {
        http.headers()
            .frameOptions()
            .disable()
            .and()
            // 禁用csrf，因为不使用session
            .csrf()
            .disable()
            // 基于token，所以不需要session
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 认证失败处理类
            .exceptionHandling()
            // 认证用户访问无权限资源时异常
            //.accessDeniedHandler(accessDeniedHandler)
            // 匿名用户访问无权限资源时的异常
            //.authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .authorizeRequests()
            .antMatchers("/login")
            .permitAll()
            .anyRequest()
            .authenticated();
    }
}
