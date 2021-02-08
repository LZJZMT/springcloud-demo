package com.lzj.auth.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/7 14:46
 */

@Data
@Accessors(chain = true)
public class SysUser implements UserDetails {

    private String id;

    private String account;

    private Integer platformId;

    private Integer roleId;

    private String roleCode;

    private String password;


    private List<Authority> authorities;

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
