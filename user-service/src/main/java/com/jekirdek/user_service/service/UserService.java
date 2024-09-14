package com.jekirdek.user_service.service;

import com.jekirdek.common.util.LoggerMessageUtil;
import com.jekirdek.common.util.ResponseExceptionUtil;
import com.jekirdek.user_service.constant.Role;
import com.jekirdek.common.dto.UserDTO;
import com.jekirdek.user_service.entity.User;
import com.jekirdek.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    private final UserRepository userRepository;

//    private final ResponseExceptionUtil responseExceptionUtil;

    public UserDTO create(UserDTO userDTO) {
        User user = User.builder()
                .withUsername(userDTO.getUsername())
                .withPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .withUserRole(userDTO.getUserRole() == null ? Role.USER : userDTO.getUserRole())
                .build();

        User savedUser = userRepository.save(user);

        String message = LoggerMessageUtil.buildMessageWithArgs("User is created successfully with id {}", savedUser.getId());
        LoggerMessageUtil.logInfo(message);


        return getUserDTO(user);
    }

    public UserDTO get(Long id) {


        User user = userRepository.findById(id).orElseThrow();

        String message = LoggerMessageUtil.buildMessageWithArgs("User is got successfully with id {}", user.getId());
        LoggerMessageUtil.logInfo(message);

        return getUserDTO(user);
    }


    private UserDTO getUserDTO(User user) {
        return UserDTO.builder()
                .withUsername(user.getUsername())
                .withUserRole(user.getUserRole())
                .withId(user.getId())
                .withPassword(user.getPassword()).build();
    }
}
