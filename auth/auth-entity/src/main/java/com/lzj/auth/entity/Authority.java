package com.lzj.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zero
 */
@Data
public class Authority implements GrantedAuthority {
    
    private String authority;
}
