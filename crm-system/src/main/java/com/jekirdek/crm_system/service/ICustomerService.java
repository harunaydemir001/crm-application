package com.jekirdek.crm_system.service;


import com.jekirdek.crm_system.dto.CustomerDTO;
import com.jekirdek.crm_system.entity.Customer;

import java.util.List;

public interface ICustomerService {

    CustomerDTO create(Customer customer);

    CustomerDTO update(Customer customer);

    CustomerDTO read(Long id);

    void delete(Long id);

    List<CustomerDTO> filter(CustomerDTO customerDTO);
}
