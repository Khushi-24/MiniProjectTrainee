package com.example.MiniProjectApis.ServiceImpl;

import com.example.MiniProjectApis.CustomException.BadRequestException;
import com.example.MiniProjectApis.CustomException.NotFoundException;
import com.example.MiniProjectApis.Dtos.PropertyDto;
import com.example.MiniProjectApis.Entities.Property;
import com.example.MiniProjectApis.Entities.PropertyImages;
import com.example.MiniProjectApis.Entities.User;
import com.example.MiniProjectApis.Repository.PropertyImageRepository;
import com.example.MiniProjectApis.Repository.PropertyRepository;
import com.example.MiniProjectApis.Repository.UserRepository;
import com.example.MiniProjectApis.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PropertyRepository propertyRepository;

    private final UserRepository userRepository;

    private final PropertyImageRepository propertyImageRepository;

    @Override
    public PropertyDto addProperty(PropertyDto propertyDto) {
        User user = userRepository.findById(propertyDto.getUserId()).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                "Land Owner doesn't exists."));
        if(user.getRole().equals("Land Owner")){
            if(user.getDeletedTimeStamp() == null){
                Property property = modelMapper.map(propertyDto, Property.class);
                propertyRepository.save(property);
                return propertyDto;
            }
            else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Land Owner is not Active.");
            }
        }else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Id doesn't represent a Land Owner.");
        }
    }
}
