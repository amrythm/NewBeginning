package org.pharmEasy.service;

import org.pharmEasy.dao.UserDao;
import org.pharmEasy.db.model.MyUserDetails;
import org.pharmEasy.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByEmail(email);

        UserDetails userDetails;

        if(user.isPresent()) {
            userDetails = new MyUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("Not found email " + email);
        }

        return userDetails;
    }
}
