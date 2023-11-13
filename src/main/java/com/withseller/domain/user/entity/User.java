package com.withseller.domain.user.entity;

import com.withseller.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "users")
public class User extends BaseTimeEntity implements UserDetails {
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

    @Column(name = "registration_number", nullable = false)
    private Long registrationNumber;

    @Column(name = "signup_source")
    private String signupSource;

    @Column(name = "terms_agreement", length = 1, nullable = false)
    private String termsAgreement;

    @Column(name = "privacy_agreement", length = 1, nullable = false)
    private String privacyAgreement;

    @ColumnDefault("'N'")
    @Column(name = "promotion_agreement", length = 1)
    private String promotionAgreement;

    @ElementCollection
    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
