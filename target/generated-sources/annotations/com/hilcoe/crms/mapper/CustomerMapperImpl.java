package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.entity.Customer;
import com.hilcoe.crms.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T15:31:26+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setUserId( entityUserUserId( entity ) );
        customerDTO.setCustomerId( entity.getCustomerId() );
        customerDTO.setLoyaltyId( entity.getLoyaltyId() );
        customerDTO.setNotes( entity.getNotes() );
        customerDTO.setCreatedAt( entity.getCreatedAt() );
        customerDTO.setUpdatedAt( entity.getUpdatedAt() );
        customerDTO.setName( entity.getName() );
        customerDTO.setPhone( entity.getPhone() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( dto.getCustomerId() );
        customer.setLoyaltyId( dto.getLoyaltyId() );
        customer.setNotes( dto.getNotes() );
        customer.setCreatedAt( dto.getCreatedAt() );
        customer.setUpdatedAt( dto.getUpdatedAt() );
        customer.setName( dto.getName() );
        customer.setPhone( dto.getPhone() );

        return customer;
    }

    private Long entityUserUserId(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        User user = customer.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
