package com.withseller.domain.user.service;

import com.withseller.domain.user.dto.LoginRequest;
import com.withseller.domain.user.dto.SignupRequest;
import com.withseller.domain.user.entity.User;
import com.withseller.domain.user.repository.UserRepository;
import com.withseller.global.exception.user.NotFoundUserException;
import com.withseller.global.exception.user.NotMatchPasswordException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Transactional
    public ResponseEntity<String> signup(SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User user = signupRequest.toEntity(signupRequest);
        userRepository.save(user);

        return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        User user = userRepository.findById(loginRequest.getEmail()).orElseThrow(NotFoundUserException::new);

        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!matches) throw new NotMatchPasswordException();

        return ResponseEntity.ok("로그인에 성공하였습니다.");
    }
}
