package com.henry.service;

import com.henry.model.user.User;
import com.henry.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public final class UserServiceImpl implements DefaultService<User,Long> {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}
