package com.example.project.service;

import com.example.project.model.User;
import com.example.project.model.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.project.security.Role.USER;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetailsForm(username);
    }

    private UsersDetails getUserDetailsForm(String username){
        User user = userService.getUser(username);
        UsersDetails usersDetails = new UsersDetails(
                user.getLogin(),
                passwordEncoder.encode(user.getPassword()),
                USER.getGrantedAuthority(),
                true,
                true,
                true,
                true
        );

        return usersDetails;
    }
}
