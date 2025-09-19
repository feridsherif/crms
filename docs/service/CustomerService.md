# CustomerService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the CustomerService.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Customer;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.exception.CustomerNotFoundException;
import com.hilcoe.crms.mapper.CustomerMapper;
import com.hilcoe.crms.repository.CustomerRepository;
import com.hilcoe.crms.repository.UserRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, exceptions, mappers, and repositories.

## Service Declaration
```java
@Service
public class CustomerService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
```
- Injects dependencies for mapping, customer persistence, and user persistence.

### Create Customer
```java
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = customerMapper.toEntity(dto);
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);
            customer.setUser(user);
        } else {
            customer.setUser(null);
        }
        if (dto.getUserId() != null && customerRepository.existsByUser_UserId(dto.getUserId())) {
            throw new com.hilcoe.crms.exception.CustomerAlreadyExistsForUserException(dto.getUserId());
        }
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }
```
- Maps DTO to entity, links to a user if provided, checks for duplicates, saves, and returns the DTO.

### Delete Customer
```java
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }
```
- Checks if a customer exists by ID, throws if not, otherwise deletes.

---

This file provides a detailed explanation of each section and method in the `CustomerService` class, helping developers understand its structure and logic. (Continue this pattern for the rest of the class.)
