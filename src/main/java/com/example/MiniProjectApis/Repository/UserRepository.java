package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String userEmail);

    User findByEmail(String userEmail);
}
