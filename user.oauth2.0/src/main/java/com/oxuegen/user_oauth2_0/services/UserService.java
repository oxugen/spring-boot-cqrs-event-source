package com.oxuegen.user_oauth2_0.services;

import com.oxuegen.user_core.models.User;
import com.oxuegen.user_oauth2_0.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("Incorrect username/ Password supplied");
        }

        var accountDetails = user.get().getAccount();

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(accountDetails.getPassword())
                .authorities(accountDetails.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
