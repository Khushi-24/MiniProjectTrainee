package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Dtos.*;
import com.example.MiniProjectApis.Entities.User;
import com.example.MiniProjectApis.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //get All User List
    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getAllUsers(){
        List<UserListResponseDto> userList = userService.getAllUsers();
        return ApiResponse.sendOkResponse("Success", userList);
    }

    //get Count of Users
    @GetMapping("/getCountOfUsers")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfUsers(){
        Long countOfUsers = userService.getCountOfUsers();
        return ApiResponse.sendOkResponse("Success", countOfUsers);
    }

    //User signUp api
    @PostMapping("/signUpNewUser")
    public ResponseEntity<?> signUpNewUser(@Valid @RequestBody UserSignUpDto userSignUpDto){
        UserSignUpDto dto = userService.signUpNewUser(userSignUpDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //view profile
    @PostMapping("/viewProfile/{userId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> viewProfile(@PathVariable Long userId){
        UserDto dto = userService.viewProfile(userId);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    // update profile
    @PostMapping("/updateProfile")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateProfileDto updateProfileDto){
        UpdateProfileDto dto = userService.updateProfile(updateProfileDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    // delete user
    @DeleteMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ApiResponse.sendOkResponse("Success", null);
    }

    // add admin
    @PostMapping("/addNewAdmin")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> addNewAdmin(@Valid @RequestBody AdminDto adminDto){
        AdminDto dto = userService.addNewAdmin(adminDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get All Admin List
    @GetMapping("/getAllAdmin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getAllAdmin(){
        List<AdminListResponseDto> adminList = userService.getAllAdmin();
        return ApiResponse.sendOkResponse("Success", adminList);
    }

    //get Count of Admin
    @GetMapping("/getCountOfAdmin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfAdmin(){
        Long countOfAdmin = userService.getCountOfAdmin();
        return ApiResponse.sendOkResponse("Success", countOfAdmin);
    }

    //update admin
    @PutMapping("/updateAdmin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody UpdateAdminDto updateAdminDto){
        UpdateAdminDto dto = userService.updateAdmin(updateAdminDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    // delete admin
    @DeleteMapping("/deleteAdmin/{adminId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId){
        userService.deleteAdmin(adminId);
        return ApiResponse.sendOkResponse("Success", null);
    }

    // reset password
    @PostMapping("/resetPassword")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetAdminPassword resetAdminPassword){
        userService.resetPassword(resetAdminPassword);
        return ApiResponse.sendOkResponse("Success", null);
    }

    // add LandOwner
    @PostMapping("/addLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> addLandOwner(@Valid @RequestBody LandOwnerDto landOwnerDto){
        LandOwnerDto dto = userService.addLandOwner(landOwnerDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    //get Count of LandOwner
    @GetMapping("/getCountOfLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfLandOwner(){
        Long countOfLandOwner = userService.getCountOfLandOwner();
        return ApiResponse.sendOkResponse("Success", countOfLandOwner);
    }

    //get All LandOwner
    @GetMapping("/getAllLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getAllLandOwner(){
        List<LandOwnerList> landOwnerList = userService.getAllLandOwner();
        return ApiResponse.sendOkResponse("Success", landOwnerList);
    }

    //update admin
    @PutMapping("/updateLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateLandOwner(@Valid @RequestBody UpdateLandOwnerDetails updateLandOwnerDetails){
        UpdateLandOwnerDetails dto = userService.updateLandOwner(updateLandOwnerDetails);
        return ApiResponse.sendOkResponse("Success", dto);
    }

    // delete admin
    @DeleteMapping("/deleteLandOwner/{landOwnerId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteLandOwner(@PathVariable Long landOwnerId){
        userService.deleteLandOwner(landOwnerId);
        return ApiResponse.sendOkResponse("Success", null);
    }

    //approve landOwner
    @PostMapping("/approveLandOwner")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> approveLandOwner(@Valid @RequestBody ApproveLandOwnerDto approveLandOwnerDto){
        ApproveLandOwnerDto dto = userService.approveLandOwner(approveLandOwnerDto);
        return ApiResponse.sendOkResponse("Success", dto);
    }
}
