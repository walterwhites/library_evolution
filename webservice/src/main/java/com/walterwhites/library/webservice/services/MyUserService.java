package com.walterwhites.library.webservice.services;

import com.walterwhites.library.business.utils.RoleEnum;
import com.walterwhites.library.consumer.repository.entity.AdminRepositoryImpl;
import com.walterwhites.library.consumer.repository.entity.ClientRepositoryImpl;
import com.walterwhites.library.model.entity.AbstractUser;
import com.walterwhites.library.model.entity.Admin;
import com.walterwhites.library.model.entity.Client;
import com.walterwhites.library.model.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service()
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class MyUserService implements UserDetailsService {

    private final AdminRepositoryImpl adminRepository;
    private final ClientRepositoryImpl clientRepository;

    @Autowired
    public MyUserService(AdminRepositoryImpl adminRepository, ClientRepositoryImpl clientRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbstractUser user;
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        try {
            user = clientRepository.findByUsername(username);
            for (RoleEnum role : ((Client) user).getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
            }
        }
        catch (Exception userException) {
            try {
                user = adminRepository.findByUsername(username);
                for (RoleEnum role : ((Admin) user).getRoles()){
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
                }
            }
            catch (Exception adminException) {
                throw new UsernameNotFoundException("No user present with username : " + username);
            }
        }
        UserDetails myUserDetails = new MyUser(user.getId(), user.getUsername(), user.getPassword(), grantedAuthorities);
        ((MyUser) myUserDetails).setId(user.getId());
        return myUserDetails;
    }
}