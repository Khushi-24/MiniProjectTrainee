package com.example.MiniProjectApis.Repository;

import com.example.MiniProjectApis.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String userEmail);

    @Query(value = "SELECT * FROM `user` where user.email = ?1 And user.deleted_date IS Null", nativeQuery = true)
    User findByEmail(String userEmail);

}
