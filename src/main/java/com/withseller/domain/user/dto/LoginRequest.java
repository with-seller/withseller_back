package com.withseller.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "이메일을 입력해주세요.")
    String email;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    String password;

}
