package com.azericard.springjwt.security;

import com.azericard.springjwt.entity.User;
import com.azericard.springjwt.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public static UserDetails mapper(User dbUser) {
        return new UserDetailsImpl(
                dbUser.getId(),
                dbUser.getUsername(),
                dbUser.getPassword(),
                dbUser.getRoles()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).map(UserDetailsServiceImpl::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User  `%s` not found", username)));
    }
}
