package com.example.idusexam.user;


import com.example.idusexam.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        // password encode
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<User> findAll(int page,int size) {
        Pageable pageable = PageRequest.of(1,5);
        return userRepository.findAll(pageable);
    }

    public Page<User> findByName(String name,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findByNameContaining(name,pageable);
    }

    public Page<User> findByEmail(String email,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findByEmailContaining(email,pageable);
    }

    public Optional<User> getLastOrderForUser(String email) {
        return userRepository.findTopByUserEmailOrderByOrderTimeDesc(email);
    }
}
