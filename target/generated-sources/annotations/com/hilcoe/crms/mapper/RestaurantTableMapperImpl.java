package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.dto.RestaurantTableResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.RestaurantTable;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T17:03:02+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RestaurantTableMapperImpl implements RestaurantTableMapper {

    @Override
    public RestaurantTableDTO toDTO(RestaurantTable entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantTableDTO restaurantTableDTO = new RestaurantTableDTO();

        restaurantTableDTO.setBranchId( entity.getBranchId() );
        restaurantTableDTO.setCapacity( entity.getCapacity() );
        restaurantTableDTO.setLocation( entity.getLocation() );
        restaurantTableDTO.setTableId( entity.getTableId() );
        restaurantTableDTO.setTableNumber( entity.getTableNumber() );

        return restaurantTableDTO;
    }

    @Override
    public RestaurantTable toEntity(RestaurantTableDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RestaurantTable restaurantTable = new RestaurantTable();

        restaurantTable.setBranch( branchIdToBranch( dto.getBranchId() ) );
        restaurantTable.setCapacity( dto.getCapacity() );
        restaurantTable.setLocation( dto.getLocation() );
        restaurantTable.setTableNumber( dto.getTableNumber() );

        return restaurantTable;
    }

    @Override
    public RestaurantTableResponseDTO toResponseDTO(RestaurantTable entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantTableResponseDTO restaurantTableResponseDTO = new RestaurantTableResponseDTO();

        restaurantTableResponseDTO.setBranchId( entityBranchBranchId( entity ) );
        restaurantTableResponseDTO.setCapacity( entity.getCapacity() );
        restaurantTableResponseDTO.setLocation( entity.getLocation() );
        restaurantTableResponseDTO.setStatus( entity.getStatus() );
        restaurantTableResponseDTO.setTableId( entity.getTableId() );
        restaurantTableResponseDTO.setTableNumber( entity.getTableNumber() );

        return restaurantTableResponseDTO;
    }

    private Long entityBranchBranchId(RestaurantTable restaurantTable) {
        if ( restaurantTable == null ) {
            return null;
        }
        Branch branch = restaurantTable.getBranch();
        if ( branch == null ) {
            return null;
        }
        Long branchId = branch.getBranchId();
        if ( branchId == null ) {
            return null;
        }
        return branchId;
    }
}
