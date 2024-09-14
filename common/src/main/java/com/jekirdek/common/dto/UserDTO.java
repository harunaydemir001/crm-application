package com.jekirdek.common.dto;


import com.jekirdek.common.constant.GeneralErrorCodeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Schema(name = "User", description = "All details about the User")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO implements Serializable {
    @Schema(hidden = true)
    Long id;
    @NotNull
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    @Schema(example = "username", description = "User Name of the User")
    String username;
    @Schema(example = "8Hgkbvc.", description = "Password of the User")
    String password;
    @Schema(example = "USER", description = "Role of the User")
    String userRole;
}