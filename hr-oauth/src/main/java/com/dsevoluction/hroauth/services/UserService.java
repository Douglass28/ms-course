package com.dsevoluction.hroauth.services;

import com.dsevoluction.hroauth.entities.User;
import com.dsevoluction.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserFeignClient userFeignClient;


    public User FindByEmail(String email){
        User user = userFeignClient.findByEmail(email).getBody();
        if (email == null){
            logger.error("email not found :" + email);
            throw new  IllegalArgumentException("email not found");
        }
        logger.info("email found :" + email);
        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClient.findByEmail(username).getBody();

        return new org.springframework.security.core.userdetails.User(user.getUsername(),  user.getPassword(),
                user.getAuthorities());
    }
}
