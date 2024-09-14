package com.jekirdek.crm_system.service;

import com.jekirdek.common.util.LoggerMessageUtil;
import com.jekirdek.crm_system.dto.CustomerDTO;
import com.jekirdek.crm_system.entity.Customer;
import com.jekirdek.crm_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {


    private final CustomerRepository customerRepository;


    @Override
    public CustomerDTO create(Customer customer) {
        customerRepository.save(customer);

        String message = LoggerMessageUtil.buildMessageWithArgs("Customer is created successfully with id {}", customer.getId().toString());
        LoggerMessageUtil.logInfo(message);

        return getCustomerDTO(customer);
    }

    @Override
    public CustomerDTO update(Customer customer) {
        customerRepository.save(customer);

        String message = LoggerMessageUtil.buildMessageWithArgs("Customer is updated successfully with id {}", customer.getId().toString());
        LoggerMessageUtil.logInfo(message);

        return getCustomerDTO(customer);
    }

    @Override
    public CustomerDTO read(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        String message = LoggerMessageUtil.buildMessageWithArgs("Customer is reading successfully with id {}", id.toString());
        LoggerMessageUtil.logInfo(message);

        return getCustomerDTO(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
        String message = LoggerMessageUtil.buildMessageWithArgs("Customer is deleted successfully with id {}", id.toString());
        LoggerMessageUtil.logInfo(message);
    }

    @Override
    public List<CustomerDTO> filter(CustomerDTO customerDTO) {
        List<Customer> customerList = customerRepository.findAll();

        Predicate<Customer> predicate = getCustomerPredicate(customerDTO);

        String message = LoggerMessageUtil.buildMessage("Customers is filtered successfully");
        LoggerMessageUtil.logInfo(message);

        return customerList.stream()
                .filter(predicate)
                .map(this::getCustomerDTO)
                .toList();
    }


    private Predicate<Customer> getCustomerPredicate(CustomerDTO customerDTO) {
        Predicate<Customer> predicate = customer -> true;

        if (Objects.nonNull(customerDTO.getFirstName())) {
            predicate = predicate.and(customer -> customer.getFirstName().equals(customerDTO.getFirstName()));
        }

        if (Objects.nonNull(customerDTO.getLastName())) {
            predicate = predicate.and(customer -> customer.getLastName().equals(customerDTO.getLastName()));
        }

        if (Objects.nonNull(customerDTO.getEmail())) {
            predicate = predicate.and(customer -> customer.getEmail().equals(customerDTO.getEmail()));
        }

        if (Objects.nonNull(customerDTO.getRegion())) {
            predicate = predicate.and(customer -> customer.getRegion().equals(customerDTO.getRegion()));
        }

        if (Objects.nonNull(customerDTO.getRegistrationDate())) {
            predicate = predicate.and(customer -> customer.getRegistrationDate().equals(customerDTO.getRegistrationDate()));
        }
        return predicate;
    }


    private CustomerDTO getCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .withId(customer.getId())
                .withFirstName(customer.getFirstName())
                .withLastName(customer.getLastName())
                .withEmail(customer.getEmail())
                .withRegion(customer.getRegion())
                .withRegistrationDate(customer.getRegistrationDate())
                .build();
    }
}
