package com.jekirdek.crm_system.dto;

import com.jekirdek.common.constant.GeneralErrorCodeConstants;
import com.jekirdek.crm_system.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Customer}
 */

@Getter
@Setter
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Schema(name = "Customer", description = "All details about the Customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO implements Serializable {

    @Schema(hidden = true)
    Long id;
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    @Schema(example = "firstName", description = "First Name of the Customer")
    @NotNull
    String firstName;
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    @Schema(example = "lastName", description = "Last Name of the Customer")
    @NotNull
    String lastName;
    @Email
    @NotNull
    @UniqueElements
    @Schema(example = "harunaydemir001@gmail.com", description = "Email of the Customer")
    String email;
    @Schema(example = "Marmara", description = "Region of the Customer")
    String region;
    @Schema(description = "Registration Date of the Customer")
    Date registrationDate;
}