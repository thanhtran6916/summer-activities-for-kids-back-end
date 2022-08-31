package com.example.backend.service;

import com.example.backend.dto.UserDTO;
import com.example.backend.dto.request.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.entity.UserPrincipal;
import com.example.backend.exception.CustomCodeException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.message.BaseResponse;
import com.example.backend.repository.UserRepository;
import com.example.backend.util.Constant;
import com.example.backend.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (!ObjectUtils.isEmpty(user)) {
                return userMapper.toUserDTO(user);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public UserDTO registerUser(UserRequest userRequest) {
        try {
            UserDTO userDuplicate = findByUsername(userRequest.getUsername());
            if (!ObjectUtils.isEmpty(userDuplicate)) {
                log.info("REGISTER ERROR USER DUPLICATE =====>>> " + Constant.gson.toJson(userRequest));
                throw new CustomCodeException(BaseResponse
                        .builder()
                        .errorCode(String.valueOf(HttpStatus.CONFLICT.value()))
                        .message(MessageUtils.getMessage("register.user.fail"))
                        .build());
            }

            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRoles(Constant.Common.REGISTER_ROLE);

            user = userRepository.save(user);
            user.setPassword(null);
            return userMapper.toUserDTO(user);
        } catch (Exception e) {
            log.info("REGISTER ERROR");
            log.info(e.getMessage());
        }
        return null;
    }

    @SneakyThrows
    @Override
    public UserDTO changeUsernameAndPassword(UserRequest userRequest) {
        // Lấy thông tin user hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO userCurrent = findByUsername(userPrincipal.getUsername());
        if (ObjectUtils.isEmpty(userCurrent)) {
            log.info("CHANGE USER EMPTY =====>>> " + Constant.gson.toJson(userRequest));
            throw new CustomCodeException(BaseResponse
                    .builder()
                    .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .message(MessageUtils.getMessage("user.empty"))
                    .build());
        }

        // check duplicate user
        UserDTO userDuplicate = findByUsername(userRequest.getUsername());
        if (!ObjectUtils.isEmpty(userDuplicate)) {
            log.info("CHANGE USER DUPLICATE =====>>> request " + Constant.gson.toJson(userRequest) + " : " + Constant.gson.toJson(userDuplicate));
            throw new CustomCodeException(BaseResponse.builder()
                    .errorCode(String.valueOf(HttpStatus.CONFLICT.value()))
                    .message(MessageUtils.getMessage("change.user.duplicate"))
                    .build());
        }

        // update new username and password
        try {
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setId(userCurrent.getId());
            user.setRoles(userCurrent.getRoles());

            user = userRepository.save(user);
            user.setPassword(null);
            return userMapper.toUserDTO(user);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (!ObjectUtils.isEmpty(user)) {
                return UserPrincipal.build(user);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }
}
