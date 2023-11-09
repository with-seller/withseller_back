package com.withseller.domain.user.service;

import com.withseller.domain.user.dto.SignupRequest;
import com.withseller.domain.user.entity.User;
import com.withseller.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void signup() {
        //given
        String email = "a@a.com";

        SignupRequest signupRequest = SignupRequest.builder()
                .email(email)
                .password("1234")
                .phoneNumber("01012341234")
                .companyName("hm_company")
                .representativeName("hmmini")
                .registrationNumber(123561)
                .signupSource("web surfing")
                .termsAgreement("Y")
                .privacyAgreement("Y")
                .promotionAgreement("Y")
                .build();

        User user = signupRequest.toEntity(signupRequest);

        //when
        userRepository.save(user);

        //then
        User findUser = userRepository.findById(email).orElse(null);

        assertEquals(findUser.getEmail(), user.getEmail());

        System.out.println(findUser);
    }
}