package com.minimi.backend.member.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email; //id로 사용
    private String username; //nickname으로 사용
    private String password;
    private String role;

    @AllArgsConstructor
    @Getter
    public static class request {
        private String email;
        private String username;
        private String password;
    }
    @AllArgsConstructor
    @Getter
    public static class patch {
        private String username;
        private String password;
    }
    @Getter
    @AllArgsConstructor
    public static class response {
        @NotBlank
        @Email
        private String email;
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String username;
    }

    @Getter
    public static class loginRequest {
        private String email;
        private String password;
    }
    @Getter
    @AllArgsConstructor
    public static class loginResponse {
        private String email;
        private String password;
        private String username;
        private String role;
    }

}