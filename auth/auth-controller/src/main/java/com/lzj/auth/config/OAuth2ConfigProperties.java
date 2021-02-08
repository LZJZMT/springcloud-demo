package com.lzj.auth.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/8 11:06
 */

@Getter
@Setter
@ToString
public class OAuth2ConfigProperties {

    /**
     * jwt非对称加密文件访问密码
     */
    private String jwtKeyPwd = "qqqq1234";

    private ClientConfigProperties clientConfig = new ClientConfigProperties();

    @Getter
    @Setter
    @ToString
    static class ClientConfigProperties{

        /**
         * 客户端id
         */
        private String clientId = "client";

        /**
         * 客户端密码
         */
        private String secret= "secret";

        /**
         * AccessToken 过期时间
         */
        private Integer accessTokenValidSec = 60*60*24;

        /**
         * RefreshToken 过期时间
         */
        private Integer refreshTokenValidSec = 60*60*24*7;

    }


}
