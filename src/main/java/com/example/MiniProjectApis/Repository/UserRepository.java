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

    @Query(value = "SELECT * FROM `user` where user.role = ?1 And user.deleted_date IS Null", nativeQuery = true)
    List<User> findByRole(String role);

    @Query(value = "SELECT COUNT(*) FROM `user` WHERE role = \"User\" And deleted_date IS Null", nativeQuery = true)
    Long countOfUsers();

    @Query(value = "SELECT COUNT(*) FROM `user` WHERE role = \"Admin\" And deleted_date IS Null", nativeQuery = true)
    Long countOfAdmin();

    @Query(value = "SELECT COUNT(*) FROM `user` WHERE role = \"Land Owner\" And deleted_date IS Null", nativeQuery = true)
    Long countOfLandOwner();

}
