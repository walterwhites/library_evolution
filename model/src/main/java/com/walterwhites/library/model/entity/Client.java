package com.walterwhites.library.model.entity;

import com.walterwhites.library.business.utils.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client extends AbstractUser {
    private String firstname;
    private String lastname;
    private String language;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Loan> loans;
    @Column(name = "alert_email", nullable = false)
    private boolean alert_email =  true;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(
            indexes = {@Index(name = "INDEX_CLIENT_ROLE", columnList = "id")},
            name = "roles_client",
            joinColumns = @JoinColumn(name = "id", nullable = true)
    )
    @Column(name = "role", nullable = true)
    @Enumerated(EnumType.STRING)
    Collection<RoleEnum> roles;

    public Client() {
        this.roles = Collections.singletonList(RoleEnum.USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }
}
