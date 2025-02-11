package com.example.idusexam.user;


import com.example.idusexam.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);


    public Page<User> findAll(Pageable pageable);

    @Query("Select u FROM User u WHERE u.name like %:name%")
    public Page<User> findByNameContaining(String name, Pageable pageable);

    @Query("Select u FROM User u WHERE u.email like %:email%")
    public Page<User> findByEmailContaining(String email, Pageable pageable);

    public Optional<User> findTopByUserEmailOrderByOrderTimeDesc(String email);


}
