package com.walterwhites.library.webservice.services;

import com.walterwhites.library.consumer.repository.entity.AdminRepository;
import com.walterwhites.library.consumer.repository.entity.ClientRepository;
import com.walterwhites.library.model.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service()
@Configuration
@EnableJpaRepositories("com.walterwhites.library.consumer.repository")
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class MyUserService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public MyUserService(AdminRepository adminRepository, ClientRepository clientRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbstractUser user = clientRepository.findByUsername(username);
        if (user == null) {
            user = adminRepository.findByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username : " + username);
        }
        else {
            return user;
        }
    }
}