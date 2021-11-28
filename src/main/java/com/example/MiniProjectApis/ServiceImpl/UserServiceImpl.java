package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.AlreadyExistsException;
import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.*;
import com.example.MiniProjectApis.Entities.User;
import com.example.MiniProjectApis.Repository.UserRepository;
import com.example.MiniProjectApis.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    
    private final ModelMapper modelMapper = new ModelMapper();

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserSignUpDto signUpNewUser(UserSignUpDto userSignUpDto) {
        if(userRepository.existsByEmail(userSignUpDto.getEmail())){
            throw new AlreadyExistsException(HttpStatus.CONFLICT, "User already exists.");
        }else{
            if(userSignUpDto.getPhoneNumber().length() == 10){
                User user = modelMapper.map(userSignUpDto, User.class);
                user.setRole("User");
                user.setPassword(getEncodedPassword(userSignUpDto.getPassword()));
                userRepository.save(user);
                return userSignUpDto;
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Phone no. length should be 10");
            }
        }

    }

    @Override
    public UserDto viewProfile(Long userId) {
        if(userId != null ){
            User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND, "User doesn't exists."));
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDto.setPassword(null);
            return userDto;
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "User id can't be null.");
        }
    }

    @Override
    public UpdateProfileDto updateProfile(UpdateProfileDto updateProfileDto) {
        User user = userRepository.findById(updateProfileDto.getId()).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND, "User doesn't exists."));
        user.setName(updateProfileDto.getName());
        user.setEmail(updateProfileDto.getEmail());
        user.setCountryCode(updateProfileDto.getCountryCode());
        user.setProfilePicture(updateProfileDto.getProfilePicture());
        user.setPhoneNumber(updateProfileDto.getPhoneNumber());
        userRepository.save(user);
        return updateProfileDto;
    }

    @Override
    public AdminDto addNewAdmin(AdminDto adminDto) {
        if(userRepository.existsByEmail(adminDto.getEmail())){
            throw new AlreadyExistsException(HttpStatus.CONFLICT, "Admin already exists.");
        }else{
            User user = modelMapper.map(adminDto, User.class);
            user.setPassword(getEncodedPassword(adminDto.getPassword()));
            user.setRole("Admin");
            userRepository.save(user);
            adminDto.setPassword(null);
            return adminDto;
        }
    }

    @Override
    public UpdateAdminDto updateAdmin(UpdateAdminDto updateAdminDto) {
        User user = userRepository.findById(updateAdminDto.getId()).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Admin Not Found"));
        if (user.getRole().equals("Admin")) {
            if (userRepository.existsByEmail(updateAdminDto.getEmail())) {
                if (user.getEmail().equals(updateAdminDto.getEmail())) {
                    updateAdminDetails(user, updateAdminDto);
                } else {
                    throw new AlreadyExistsException(HttpStatus.CONFLICT, "Admin Email already exists.");
                }
            } else {
                updateAdminDetails(user, updateAdminDto);
            }
        } else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Admin");
        }
        updateAdminDto.setPassword(null);
        return updateAdminDto;
    }

    public void updateAdminDetails(User user, UpdateAdminDto updateAdminDto){
        if (updateAdminDto.getPassword() != null){
            if(updateAdminDto.getPassword().length() >= 8 && updateAdminDto.getPassword().length()<=16){
                user.setName(updateAdminDto.getName());
                user.setEmail(updateAdminDto.getEmail());
                user.setPassword(getEncodedPassword(updateAdminDto.getPassword()));
                userRepository.save(user);
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Password size can't be less than 8 character and more than 16 characters");
            }
        }else{
            user.setName(updateAdminDto.getName());
            user.setEmail(updateAdminDto.getEmail());
            userRepository.save(user);
        }
    }


    @Override
    public void deleteAdmin(Long adminId) {
        User user = userRepository.findById(adminId).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND, "Admin doesn't exists."));
        if(user.getRole().equals("Admin")){
            user.setDeletedTimeStamp(new Date());
            userRepository.save(user);
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Admin");
        }

    }

    @Override
    public void resetPassword(ResetAdminPassword resetAdminPassword) {
        User user = userRepository.findById(resetAdminPassword.getId()).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND, "User doesn't exists."));
        if(user.getRole().equals("Admin")){
            user.setPassword(getEncodedPassword(resetAdminPassword.getPassword()));
            userRepository.save(user);
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Admin");
        }

    }

    @Override
    public LandOwnerDto addLandOwner(LandOwnerDto landOwnerDto) {
        if(userRepository.existsByEmail(landOwnerDto.getEmail())){
            User user1 = userRepository.findByEmail(landOwnerDto.getEmail());
            if(user1.getDeletedTimeStamp() != null){
                return addOwner(landOwnerDto);
            }else{
                throw new AlreadyExistsException(HttpStatus.CONFLICT, "Land Owner Email already exists.");
            }
        }else{
            return addOwner(landOwnerDto);
        }
    }

    public LandOwnerDto addOwner(LandOwnerDto landOwnerDto){
        User user = modelMapper.map(landOwnerDto, User.class);
        user.setPassword(getEncodedPassword(landOwnerDto.getPassword()));
        user.setRole("Land Owner");
        user.setIsApproved(false);
        userRepository.save(user);
        landOwnerDto.setPassword(null);
        return landOwnerDto;
    }

    @Override
    public UpdateLandOwnerDetails updateLandOwner(UpdateLandOwnerDetails updateLandOwnerDetails) {
        User user = userRepository.findById(updateLandOwnerDetails.getId()).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Land Owner Not Found"));
        if (user.getRole().equals("Land Owner")) {
            if(user.getDeletedTimeStamp() == null){
                if (userRepository.existsByEmail(updateLandOwnerDetails.getEmail())) {
                    if (user.getEmail().equals(updateLandOwnerDetails.getEmail())) {
                        updateLandOwnerDetails(user, updateLandOwnerDetails);
                    } else {
                        User user1 = userRepository.findByEmail(updateLandOwnerDetails.getEmail());
                        if(user1.getDeletedTimeStamp() != null){
                            updateLandOwnerDetails(user, updateLandOwnerDetails);
                        }else{
                            throw new AlreadyExistsException(HttpStatus.CONFLICT, "Land Owner Email already exists.");
                        }
                    }
                } else {
                    updateLandOwnerDetails(user, updateLandOwnerDetails);
                }
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Land Owner is not Active");
            }
        } else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Land Owner");
        }
        updateLandOwnerDetails.setPassword(null);
        return updateLandOwnerDetails;
    }

    public void updateLandOwnerDetails(User user, UpdateLandOwnerDetails updateLandOwnerDetails){
        if (updateLandOwnerDetails.getPassword() != null){
            if(updateLandOwnerDetails.getPassword().length() >= 8 && updateLandOwnerDetails.getPassword().length()<=16){
                user.setName(updateLandOwnerDetails.getName());
                user.setEmail(updateLandOwnerDetails.getEmail());
                user.setPassword(getEncodedPassword(updateLandOwnerDetails.getPassword()));
                userRepository.save(user);
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Password size can't be less than 8 character and more than 16 characters");
            }
        }else{
            user.setName(updateLandOwnerDetails.getName());
            user.setEmail(updateLandOwnerDetails.getEmail());
            userRepository.save(user);
        }
    }

    @Override
    public void deleteLandOwner(Long landOwnerId) {
        User user = userRepository.findById(landOwnerId).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND, "Land Owner doesn't exists."));
        if(user.getDeletedTimeStamp() == null){
            if(user.getRole().equals("Land Owner")){
                user.setDeletedTimeStamp(new Date());
                userRepository.save(user);
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Land Owner");
            }
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Land Owner is not Active");
        }
    }

    @Override
    public ApproveLandOwnerDto approveLandOwner(ApproveLandOwnerDto approveLandOwnerDto) {
        User user = userRepository.findById(approveLandOwnerDto.getId()).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND, "Land Owner doesn't exists."));
        if(user.getDeletedTimeStamp() == null){
            if(user.getRole().equals("Land Owner")){
                if(approveLandOwnerDto.getIsApproved().equals(false) || approveLandOwnerDto.getIsApproved().equals(true)){
                    user.setIsApproved(approveLandOwnerDto.getIsApproved());
                    userRepository.save(user);
                }else{
                    throw new BadRequestException(HttpStatus.BAD_REQUEST, "Approval can be in terms of true or false.");
                }
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Land Owner");
            }
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Land Owner is not Active");
        }

        return approveLandOwnerDto;
    }


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
