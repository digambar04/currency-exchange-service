package com.synechron.currencyexchange.service;

import com.synechron.currencyexchange.pojos.User;
import com.synechron.currencyexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * The User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user by name from db
     * @param userName
     * @return Type UserDetails
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
}
