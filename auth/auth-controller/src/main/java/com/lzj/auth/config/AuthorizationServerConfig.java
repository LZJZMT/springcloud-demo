package com.lzj.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/7 10:21
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Resource(name = "authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenStore tokenStore;

    @Bean
    @RefreshScope
    @ConfigurationProperties(OAuth2Constant.JWT_PREFIX)
    public OAuth2ConfigProperties oAuth2ConfigProperties(){
        return new OAuth2ConfigProperties();
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        OAuth2ConfigProperties authConfigProperties = oAuth2ConfigProperties();
        // 非对称加密方式
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 使用非对称加密方式
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("auth-jwt.jks"), authConfigProperties.getJwtKeyPwd().toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("auth-jwt"));
        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        OAuth2ConfigProperties authConfigProperties = oAuth2ConfigProperties();

        clients.inMemory()
                //客户端id
                .withClient(authConfigProperties.getClientConfig().getClientId())
                .secret(passwordEncoder().encode(authConfigProperties.getClientConfig().getSecret()))
                //允许的授权类型
                .authorizedGrantTypes(OAuth2Constant.GRANT_TYPE, OAuth2Constant.REFRESH_TOKEN)
                .scopes("server")
                // token过期时间2h分钟
                .accessTokenValiditySeconds(authConfigProperties.getClientConfig().getAccessTokenValidSec())
                // token刷新时间4h分钟
                .refreshTokenValiditySeconds(authConfigProperties.getClientConfig().getRefreshTokenValidSec())
                //其他客户端的
                .and();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
