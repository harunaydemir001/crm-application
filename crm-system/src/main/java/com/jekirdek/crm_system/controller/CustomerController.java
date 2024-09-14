package com.jekirdek.crm_system.controller;

import com.jekirdek.common.factory.ResponseFactory;
import com.jekirdek.common.model.Response;
import com.jekirdek.crm_system.dto.CustomerDTO;
import com.jekirdek.crm_system.entity.Customer;
import com.jekirdek.crm_system.service.ICustomerService;
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
        name = "APIs for Customer in CRM",
        description = "CRUD REST APIs in Cinema to CREATE,READ, UPDATE, DELETE And FILTER movie details")
@RequestMapping("/customer")
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @Operation(summary = "Save Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer Created", content = {
                    @Content(schema =
                    @Schema(implementation = Response.class))
            })
    })
    @PostMapping
    public ResponseEntity<Response> save(@Parameter(description = "Customer DTO", required = true) @RequestBody Customer customer) {
        return ResponseFactory.createResponse(iCustomerService.create(customer), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Parameter(description = "Customer DTO", required = true) @RequestBody Customer customer) {
        return ResponseFactory.createResponse(iCustomerService.update(customer), HttpStatus.OK);
    }

    @Operation(summary = "Get Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = {
                    @Content(schema = @Schema(implementation = Response.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@Parameter(description = "Customer Id", required = true) @PathVariable(value = "id") Long id) {
        return ResponseFactory.createResponse(iCustomerService.read(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@Parameter(description = "Customer Id", required = true) @PathVariable(value = "id") Long id) {
        iCustomerService.delete(id);
        return ResponseFactory.createSuccessResponse();
    }

    @Operation(summary = "Filter Customer", description = "Filtered Customer By Selection Field")
    @PostMapping("/filter")
    public ResponseEntity<Response> filter(@Parameter(description = "Customer DTO") @RequestBody() CustomerDTO customerDTO) {
        return ResponseFactory.createResponse(iCustomerService.filter(customerDTO), HttpStatus.OK);
    }
}
