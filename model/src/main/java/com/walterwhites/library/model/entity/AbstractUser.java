package com.walterwhites.library.model.entity;

import com.walterwhites.library.business.utils.BCryptManagerUtil;
import com.walterwhites.library.business.utils.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class AbstractUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    protected String email;
    protected String username;
    protected Date created_at;
    protected Date updated_at;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "account_non_expired")
    protected boolean accountNonExpired;

    @Column(name = "account_non_locked")
    protected boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    protected boolean credentialsNonExpired;

    @Column(name = "enabled")
    protected boolean enabled;

    public AbstractUser() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.created_at = new Date();
    }

    public AbstractUser(String email, String password, String firstname, String lastname, Collection<RoleEnum> roles) {
        this.password = BCryptManagerUtil.passwordencoder().encode(password);
        this.email = email;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
