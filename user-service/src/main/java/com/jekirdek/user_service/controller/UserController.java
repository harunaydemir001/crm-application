package com.jekirdek.user_service.controller;


import com.jekirdek.common.factory.ResponseFactory;
import com.jekirdek.common.model.Response;
import com.jekirdek.common.dto.UserDTO;
import com.jekirdek.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "APIs for User in CRM")
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created", content = {
                    @Content(schema =
                    @Schema(implementation = Response.class))
            })
    })
    @PostMapping("/create")
    public ResponseEntity<Response> create(@Parameter(description = "Customer DTO", required = true) @RequestBody UserDTO userDTO) {
        return ResponseFactory.createResponse(userService.create(userDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@Parameter(description = "User Id", required = true) @PathVariable(value = "id") Long id) {
        return ResponseFactory.createResponse(userService.get(id), HttpStatus.OK);
    }
}
