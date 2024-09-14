package com.jekirdek.crm_system.entity;

import com.jekirdek.common.constant.GeneralErrorCodeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "customer", schema = "crm")
@Builder(setterPrefix = "with")
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @Schema(hidden = true)
    Long id;
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    @NotNull
    @Schema(example = "firstName", description = "First Name of the Customer")
    String firstName;
    @Size(min = 3, max = 30, message = GeneralErrorCodeConstants.LENGTH_NOT_VALID)
    @NotNull
    @Schema(example = "lastName", description = "Last Name of the Customer")
    String lastName;
    @Email
    @Column(unique = true, nullable = false)
    @Schema(example = "harunaydemir001@gmail.com", description = "Email of the Customer")
    String email;
    @Schema(example = "Marmara", description = "Region of the Customer")
    String region;
    Date registrationDate;

    @PrePersist
    private void onCreate() {
            setRegistrationDate(new Date());
    }
}
