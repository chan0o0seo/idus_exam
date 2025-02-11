package com.example.idusexam.user;


import com.example.idusexam.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    Page<User> findAll(Pageable pageable);

    @Query("Select u.email FROM User u WHERE u.name like %:name%")
    List<String> findByNameContaining(String name);

    @Query("Select u.email FROM User u WHERE u.email like %:email%")
    List<String> findByEmailContaining(String email);

    @Query("SELECT u.email from User u")
    List<String> findAllEmails();



}
