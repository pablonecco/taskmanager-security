package com.example.taskmanager.services.implementation;

import com.example.taskmanager.entities.RoleEntity;
import com.example.taskmanager.entities.UserEntity;
import com.example.taskmanager.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService {
    @Bean
    PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return buildUser(user, buildGrantedAuthorities(user.getRoles()));
    }

    private User buildUser(UserEntity user, List<GrantedAuthority> grantedAuthorities) {
        return new User(user.getUsername(), user.getPassword(), true,
                true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
                grantedAuthorities);
    }

    private List<GrantedAuthority> buildGrantedAuthorities(Set<RoleEntity> userRoles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(RoleEntity userRole: userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<>(grantedAuthorities);
    }
}
