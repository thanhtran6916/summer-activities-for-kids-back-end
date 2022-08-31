package com.example.backend.entity;

import com.example.backend.dto.RoleDTO;
import com.example.backend.util.Constant;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> roles;

    public static UserDetails build(User user) {
        Type type = new TypeToken<ArrayList<RoleDTO>>() {}.getType();
        List<RoleDTO> roles = Constant.gson.fromJson(user.getRoles(),  type);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleDTO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return UserPrincipal
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
