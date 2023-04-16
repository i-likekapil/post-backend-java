package com.kapil.assignment.config;

import com.kapil.assignment.entity.UserEntity;
import com.kapil.assignment.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.X11.XSystemTrayPeer;

import java.util.ArrayList;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 6:28 pm
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username "+username);
        if(username == null) return null;

        final UserEntity userEntity = userRepo.findUserEntitiesByEmail(username);
        try {
            //System.out.println("name ---------------------" + userEntity);

        }catch (Exception e){
            System.out.println("userservice m h error"+e);
        }
        if(username.equals(userEntity.getEmail())){
            return new User(username,userEntity.getPassword(),new ArrayList<>());
        }
        /*
        if(username.equals("user1")){
            return new User("user1","user1",new ArrayList<>());
        }
        if(username.equals("user2")){
            return new User("user2","user2",new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("user not found");
        }*/
        throw new UsernameNotFoundException("user not found");
    }
}
