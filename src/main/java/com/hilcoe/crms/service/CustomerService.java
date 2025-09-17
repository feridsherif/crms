package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Customer;
import com.hilcoe.crms.exception.CustomerNotFoundException;
import com.hilcoe.crms.mapper.CustomerMapper;
import com.hilcoe.crms.repository.CustomerRepository;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserRepository userRepository;

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

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(id));
        return customerMapper.toDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
            .map(customerMapper::toDTO)
            .collect(Collectors.toList());
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(id));
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);
            customer.setUser(user);
        } else {
            customer.setUser(null);
        }
        customer.setLoyaltyId(dto.getLoyaltyId());
        customer.setNotes(dto.getNotes());
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }

    public PaginatedResponseDTO<CustomerDTO> getPaginatedCustomers(int page, int size) {
        Page<Customer> customerPage = customerRepository.findAll(PageRequest.of(page, size));
        List<CustomerDTO> dtos = customerPage.getContent().stream()
            .map(customerMapper::toDTO)
            .collect(Collectors.toList());
        return new PaginatedResponseDTO<>(
            dtos,
            customerPage.getNumber(),
            customerPage.getSize(),
            customerPage.getTotalElements(),
            customerPage.getTotalPages(),
            customerPage.hasNext(),
            customerPage.hasPrevious(),
            null, // sort
            null, // filter
            customerPage.getNumber() * customerPage.getSize() + 1,
            customerPage.getNumber() * customerPage.getSize() + dtos.size(),
            null, // nextPageUrl
            null  // previousPageUrl
        );
    }
}