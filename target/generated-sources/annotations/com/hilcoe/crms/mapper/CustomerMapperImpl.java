package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.entity.Customer;
import com.hilcoe.crms.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T17:03:02+0300",
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
        customerDTO.setCreatedAt( entity.getCreatedAt() );
        customerDTO.setCustomerId( entity.getCustomerId() );
        customerDTO.setLoyaltyId( entity.getLoyaltyId() );
        customerDTO.setName( entity.getName() );
        customerDTO.setNotes( entity.getNotes() );
        customerDTO.setPhone( entity.getPhone() );
        customerDTO.setUpdatedAt( entity.getUpdatedAt() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCreatedAt( dto.getCreatedAt() );
        customer.setCustomerId( dto.getCustomerId() );
        customer.setLoyaltyId( dto.getLoyaltyId() );
        customer.setName( dto.getName() );
        customer.setNotes( dto.getNotes() );
        customer.setPhone( dto.getPhone() );
        customer.setUpdatedAt( dto.getUpdatedAt() );

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
