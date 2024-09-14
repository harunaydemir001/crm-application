//package com.jekirdek.common.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.io.Serializable;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder(setterPrefix = "with")
//@Schema(name = "User", description = "All details about the User")
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class UserResponseDTO implements Serializable {
//    @Schema(hidden = true)
//    String encryptedId;
//    @Schema(example = "username", description = "User Name of the User")
//    String username;
//    @Schema(example = "8Hgkbvc.", description = "Password of the User")
//    String password;
//    @Schema(example = "USER", description = "Role of the User")
//    String userRole;
//}