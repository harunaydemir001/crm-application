package com.jekirdek.authentication_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {
    @Schema(example = "username", description = "User Name of the User")
    String username;
    @Schema(example = "8Hgkbvc.", description = "Password of the User")
    String password;
}
