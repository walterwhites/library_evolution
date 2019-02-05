package com.walterwhites.library.model.entity;

import com.walterwhites.library.business.utils.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Entity
@Table(name = "Admins")
@Getter
@Setter
public class Admin extends AbstractUser {
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Library library;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(
            indexes = {@Index(name = "INDEX_ADMIN_ROLE", columnList = "id")},
            name = "roles_admin",
            joinColumns = @JoinColumn(name = "id", nullable = true)
    )
    @Column(name = "role", nullable = true)
    @Enumerated(EnumType.STRING)
    Collection<RoleEnum> roles;

    public Admin() {
        this.roles = Collections.singletonList(RoleEnum.ADMINISTRATOR);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }
}