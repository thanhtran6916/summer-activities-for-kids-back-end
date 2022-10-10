//package com.example.backend.api;
//
//import com.example.backend.dto.request.UserRequest;
//import com.example.backend.dto.response.JwtResponse;
//import com.example.backend.dto.UserDTO;
//import com.example.backend.entity.UserPrincipal;
//import com.example.backend.message.BaseResponse;
//import com.example.backend.service.JwtService;
//import com.example.backend.service.UserService;
//import com.example.backend.util.Constant;
//import com.example.backend.util.MessageUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//@Slf4j
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;
//    private final UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<BaseResponse> login(@Valid @RequestBody UserRequest userRequest) {
//        BaseResponse response = new BaseResponse();
//        try {
//            Authentication authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//            JwtResponse jwtResponse = jwtService.createTokenLogin(userPrincipal);
//            response.setData(jwtResponse);
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        response.setErrorCode(Constant.ERROR_CODE);
//        response.setMessage(MessageUtils.getMessage("login.fail"));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<BaseResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
//        BaseResponse response = new BaseResponse();
//        UserDTO userDTO = userService.registerUser(userRequest);
//        if (ObjectUtils.isEmpty(userDTO)) {
//            response.setErrorCode(Constant.ERROR_CODE);
//            response.setMessage(MessageUtils.getMessage("register.user.fail"));
//        }
//        response.setData(userDTO);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/change/user")
//    public ResponseEntity<BaseResponse> changeUsernameAndPassword(@Valid @RequestBody UserRequest userRequest) {
//        BaseResponse response = new BaseResponse();
//        UserDTO userDTO = userService.changeUsernameAndPassword(userRequest);
//        if (ObjectUtils.isEmpty(userDTO)) {
//            response.setErrorCode(Constant.ERROR_CODE);
//            response.setMessage(MessageUtils.getMessage("change.user.fail"));
//        }
//        response.setData(userDTO);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}
