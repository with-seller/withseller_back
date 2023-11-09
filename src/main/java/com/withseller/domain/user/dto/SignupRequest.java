package com.withseller.domain.user.dto;

import com.withseller.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class  SignupRequest {
    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotEmpty(message = "상호명을 입력해주세요.")
    private String companyName;

    @NotEmpty(message = "대표자명을 입력해주세요.")
    private String representativeName;

    @NotEmpty(message = "사업자 등록번호를 입력해주세요.")
    private long registrationNumber;

    private String signupSource;

    @NotEmpty(message = "통합 이용약관에 동의해주세요.")
    private String termsAgreement;

    @NotEmpty(message = "개인정보 수집에 동의해주세요.")
    private String privacyAgreement;

    private String promotionAgreement;

    public User toEntity(SignupRequest signupRequest) {
        return User.builder()
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .phoneNumber(signupRequest.getPhoneNumber())
                .companyName(signupRequest.getCompanyName())
                .representativeName(signupRequest.getRepresentativeName())
                .registrationNumber(signupRequest.getRegistrationNumber())
                .signupSource(signupRequest.getSignupSource())
                .termsAgreement(signupRequest.getTermsAgreement())
                .privacyAgreement(signupRequest.getPrivacyAgreement())
                .promotionAgreement(signupRequest.getPromotionAgreement())
                .build();
    }
}
