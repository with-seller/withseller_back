package com.withseller.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "users")
public class User {
    @Id
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "representative_name", nullable = false)
    private String representativeName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "registration_number", nullable = false)
    private long registrationNumber;

    @Column(name = "signup_source")
    private String signupSource;

}
