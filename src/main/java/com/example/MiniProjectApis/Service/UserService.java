package com.example.MiniProjectApis.Service;

import com.example.MiniProjectApis.Dtos.*;

public interface UserService {
    UserSignUpDto signUpNewUser(UserSignUpDto userSignUpDto);

    UserDto viewProfile(Long userId);

    UpdateProfileDto updateProfile(UpdateProfileDto updateProfileDto);

    AdminDto addNewAdmin(AdminDto adminDto);

    UpdateAdminDto updateAdmin(UpdateAdminDto updateAdminDto);

    void deleteAdmin(Long adminId);

    void resetPassword(ResetAdminPassword resetAdminPassword);

    LandOwnerDto addLandOwner(LandOwnerDto landOwnerDto);

    UpdateLandOwnerDetails updateLandOwner(UpdateLandOwnerDetails updateLandOwnerDetails);

    void deleteLandOwner(Long landOwnerId);

    ApproveLandOwnerDto approveLandOwner(ApproveLandOwnerDto approveLandOwnerDto);

    void deleteUser(Long userId);
}
