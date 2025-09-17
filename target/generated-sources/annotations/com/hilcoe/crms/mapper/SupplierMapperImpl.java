package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-16T19:12:55+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public Supplier toEntity(SupplierDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName( dto.getName() );
        supplier.setContact( dto.getContact() );
        supplier.setPhone( dto.getPhone() );
        supplier.setTerms( dto.getTerms() );

        return supplier;
    }

    @Override
    public SupplierResponseDTO toResponseDTO(Supplier entity) {
        if ( entity == null ) {
            return null;
        }

        SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();

        supplierResponseDTO.setSupplierId( entity.getSupplierId() );
        supplierResponseDTO.setName( entity.getName() );
        supplierResponseDTO.setContact( entity.getContact() );
        supplierResponseDTO.setPhone( entity.getPhone() );
        supplierResponseDTO.setTerms( entity.getTerms() );

        return supplierResponseDTO;
    }
}
