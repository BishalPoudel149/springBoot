package com.jcCoder.springboottut.security;

import com.jcCoder.springboottut.entity.UserEntity.Role;
import com.jcCoder.springboottut.entity.UserEntity.UserEntity;
import com.jcCoder.springboottut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserEntity user= userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User name Not Found"));
            return new User(user.getUsername(),user.getPassword(),mapRoleToGrantedAuthority(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRoleToGrantedAuthority(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
