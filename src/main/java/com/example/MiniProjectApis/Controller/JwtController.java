package com.example.MiniProjectApis.Controller;

import com.example.MiniProjectApis.Config.JwtServiceImpl;
import com.example.MiniProjectApis.Config.JwtUtil;
import com.example.MiniProjectApis.Dtos.AuthenticateResponse;
import com.example.MiniProjectApis.Dtos.JwtRequest;
import com.example.MiniProjectApis.Dtos.JwtResponse;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtController {

    private final JwtServiceImpl jwtService;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        JwtResponse jwtResponse = jwtService.createJwtToken(jwtRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);

    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthenticateResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }
}
