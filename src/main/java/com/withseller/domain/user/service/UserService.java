package com.withseller.domain.user.service;

import com.withseller.domain.common.dto.CommonResponse;
import com.withseller.domain.user.dto.LoginRequest;
import com.withseller.domain.user.dto.SignupRequest;
import com.withseller.domain.user.entity.User;
import com.withseller.domain.user.repository.UserRepository;
import com.withseller.exception.user.NotFoundUserException;
import com.withseller.exception.user.NotMatchPasswordException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Transactional
    public ResponseEntity<CommonResponse> signup(SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User user = signupRequest.toEntity(signupRequest);
        userRepository.save(user);

        CommonResponse commonResponse = CommonResponse.builder()
                .status("SUCCESS")
                .message("회원 가입이 완료되었습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    public ResponseEntity<CommonResponse> login(LoginRequest loginRequest) {
        User user = userRepository.findById(loginRequest.getEmail()).orElseThrow(NotFoundUserException::new);

        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!matches) throw new NotMatchPasswordException();

        CommonResponse commonResponse = CommonResponse.builder()
                .status("SUCCESS")
                .message("로그인에 성공하였습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
