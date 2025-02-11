package com.example.idusexam.user;


import com.example.idusexam.order.OrderRepository;
import com.example.idusexam.order.model.Order;
import com.example.idusexam.user.model.User;
import com.example.idusexam.user.model.UserDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User save(User user) {

        // 유효성 검사
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // 유효성 검사 실패 시 예외 처리
        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                System.out.println(violation.getMessage());
            }
            throw new IllegalArgumentException("유효하지 않은 입력이 있습니다.");
        }

        // password encode
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    @Transactional (readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Transactional (readOnly = true)
    public Page<Order> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        return orderRepository.findTopByUserEmailOrderByOrderTimeDesc(getAllUserEmails(),pageable);
    }
    @Transactional(readOnly = true)
    public Page<Order> findByName(String name,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return orderRepository.findTopByUserEmailOrderByOrderTimeDesc(getAllUserEmailsByName(name),pageable);
    }
    @Transactional(readOnly = true)
    public Page<Order> findByEmail(String email,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return orderRepository.findTopByUserEmailOrderByOrderTimeDesc(getAllUserEmailsByEmail(email),pageable);
    }
    @Transactional(readOnly = true)
    public List<String> getAllUserEmails() {
        return userRepository.findAllEmails();  // 예시
    }
    @Transactional(readOnly = true)
    public List<String> getAllUserEmailsByName(String name) {
        return  userRepository.findByNameContaining(name);
    }
    @Transactional(readOnly = true)
   public List<String> getAllUserEmailsByEmail(String email) {
        return  userRepository.findByEmailContaining(email);
    }
}
