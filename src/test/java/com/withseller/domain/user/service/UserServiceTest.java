package com.withseller.domain.user.service;

import com.withseller.domain.user.dto.SignupRequest;
import com.withseller.domain.user.entity.User;
import com.withseller.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
                .registrationNumber(123561L)
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

    @Test
    void passwordEncodeTest() {
        String text = "encodingTest";
        String encodeText = passwordEncoder.encode(text);

        String text2 = "encodingTest";
        String encodeText2 = passwordEncoder.encode(text2);

        System.out.println("text = " + text);
        System.out.println("encodeText = " + encodeText);

        System.out.println("text2 = " + text2);
        System.out.println("encodeText2 = " + encodeText2);

        assertTrue(passwordEncoder.matches(text, encodeText));
    }
}