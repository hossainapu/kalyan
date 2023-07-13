package com.hellokoding.auth.service;

import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.db.User;
import com.hellokoding.auth.model.dto.LoginInfo;
import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!Utils.isEmpty(user.getRoleName())){
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoleName()));
        }

        UserDetails details = buildUserForAuthentication(user,grantedAuthorities);
        return details;
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private LoginInfo buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new LoginInfo(user);
    }
}
