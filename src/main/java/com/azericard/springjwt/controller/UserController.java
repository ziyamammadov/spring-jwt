package com.azericard.springjwt.controller;

import com.azericard.springjwt.entity.User;
import com.azericard.springjwt.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        service.signUp(user);
    }

    @GetMapping("/all")
    public List<User> get_all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get_one(@PathVariable long id) {
        return service.getOne(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody User user) {
        service.delete(user);
    }
}
