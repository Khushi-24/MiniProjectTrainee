package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Common.ApiResponse;
import com.example.MiniProjectApis.Dtos.DashBoardCountApiDto;
import com.example.MiniProjectApis.Service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DashBoardController {

    private final DashBoardService dashBoardService;


    //DashBoard Api
    //get Count of All Modules
    @GetMapping("/getCountOfAll")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getCountOfAll(){
        DashBoardCountApiDto  countOfAll = dashBoardService.getCountOfAll();
        return ApiResponse.sendOkResponse("Success", countOfAll);
    }
}
