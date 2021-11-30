package com.example.MiniProjectApis.Dtos;

import com.example.MiniProjectApis.Entities.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddActivityDto extends  UpdateActivityDto{


    List<ImageDto> imageDtoList;

}
