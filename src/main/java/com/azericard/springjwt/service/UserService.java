package com.azericard.springjwt.service;

import com.azericard.springjwt.entity.User;
import com.azericard.springjwt.exception.GeneralException;
import com.azericard.springjwt.exception.UserNotFoundException;
import com.azericard.springjwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        List<User> users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return users;
    }

    public User getOne(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(UserNotFoundException::new);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
