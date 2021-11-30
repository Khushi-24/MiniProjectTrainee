package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Dtos.*;
import com.example.MiniProjectApis.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //User signUp api
    @PostMapping("/signUpNewUser")
    public ResponseEntity<?> signUpNewUser(@Valid @RequestBody UserSignUpDto userSignUpDto){
        UserSignUpDto dto = userService.signUpNewUser(userSignUpDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //view profile
    @PostMapping("/viewProfile/{userId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> viewProfile(@PathVariable Long userId){
        UserDto dto = userService.viewProfile(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // update profile
    @PostMapping("/updateProfile")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateProfileDto updateProfileDto){
        UpdateProfileDto dto = userService.updateProfile(updateProfileDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // delete user
    @DeleteMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // add admin
    @PostMapping("/addNewAdmin")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> addNewAdmin(@Valid @RequestBody AdminDto adminDto){
        AdminDto dto = userService.addNewAdmin(adminDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //update admin
    @PutMapping("/updateAdmin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody UpdateAdminDto updateAdminDto){
        UpdateAdminDto dto = userService.updateAdmin(updateAdminDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // delete admin
    @DeleteMapping("/deleteAdmin/{adminId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId){
        userService.deleteAdmin(adminId);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

    // reset password
    @PostMapping("/resetPassword")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetAdminPassword resetAdminPassword){
        userService.resetPassword(resetAdminPassword);
        return ResponseEntity.ok("Password reset successful.");
    }

    // add LandOwner
    @PostMapping("/addLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addLandOwner(@Valid @RequestBody LandOwnerDto landOwnerDto){
        LandOwnerDto dto = userService.addLandOwner(landOwnerDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //update admin
    @PutMapping("/updateLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateLandOwner(@Valid @RequestBody UpdateLandOwnerDetails updateLandOwnerDetails){
        UpdateLandOwnerDetails dto = userService.updateLandOwner(updateLandOwnerDetails);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // delete admin
    @DeleteMapping("/deleteLandOwner/{landOwnerId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteLandOwner(@PathVariable Long landOwnerId){
        userService.deleteLandOwner(landOwnerId);
        return ResponseEntity.ok("Land Owner deleted successfully.");
    }

    //approve landOwner
    @PostMapping("/approveLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> approveLandOwner(@Valid @RequestBody ApproveLandOwnerDto approveLandOwnerDto){
        ApproveLandOwnerDto dto = userService.approveLandOwner(approveLandOwnerDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
