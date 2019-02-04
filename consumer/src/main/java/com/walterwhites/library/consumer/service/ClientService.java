package com.walterwhites.library.consumer.service;

import com.walterwhites.library.consumer.repository.entity.ClientRepository;
import com.walterwhites.library.model.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("clientService")
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbstractUser user = clientRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username : " + username);
        }
        else {
            return user;
        }
    }
}