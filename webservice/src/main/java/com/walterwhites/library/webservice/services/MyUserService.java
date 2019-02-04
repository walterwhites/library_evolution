package com.walterwhites.library.webservice.services;

import com.walterwhites.library.consumer.repository.entity.AdminRepositoryImpl;
import com.walterwhites.library.consumer.repository.entity.ClientRepositoryImpl;
import com.walterwhites.library.model.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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