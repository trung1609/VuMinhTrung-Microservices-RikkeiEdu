package com.trung.userservice.security.principal;

import com.trung.userservice.entity.Users;
import com.trung.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserDetailServiceCustom implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        List<SimpleGrantedAuthority> authorities = users.getUserRoles().stream()
                .map(userRoles -> new SimpleGrantedAuthority(userRoles.getRoleId().getName().toUpperCase()))
                .toList();

        return UserPrincipal.builder()
                .user(users)
                .authorities(authorities)
                .build();
    }
}
