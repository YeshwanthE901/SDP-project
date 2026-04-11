package com.homevalueplus.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLoginResponse {

    private String token;
    private String username;
    private String email;
    private String message;
}
