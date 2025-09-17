package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.entity.WaiterRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T09:18:42+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class WaiterRequestMapperImpl implements WaiterRequestMapper {

    @Override
    public WaiterRequest toEntity(WaiterRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        WaiterRequest waiterRequest = new WaiterRequest();

        waiterRequest.setTableId( dto.getTableId() );
        waiterRequest.setRequestType( dto.getRequestType() );

        return waiterRequest;
    }

    @Override
    public WaiterRequestResponseDTO toResponseDTO(WaiterRequest entity) {
        if ( entity == null ) {
            return null;
        }

        WaiterRequestResponseDTO waiterRequestResponseDTO = new WaiterRequestResponseDTO();

        waiterRequestResponseDTO.setRequestId( entity.getRequestId() );
        waiterRequestResponseDTO.setTableId( entity.getTableId() );
        waiterRequestResponseDTO.setBranchId( entity.getBranchId() );
        waiterRequestResponseDTO.setRequestType( entity.getRequestType() );
        if ( entity.getStatus() != null ) {
            waiterRequestResponseDTO.setStatus( entity.getStatus().name() );
        }
        waiterRequestResponseDTO.setHandledBy( entity.getHandledBy() );

        return waiterRequestResponseDTO;
    }
}
