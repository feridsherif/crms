package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T10:08:04+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setBranchId( dto.getBranchId() );
        order.setTableId( dto.getTableId() );

        return order;
    }

    @Override
    public OrderFullResponseDTO toFullResponseDTO(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderFullResponseDTO orderFullResponseDTO = new OrderFullResponseDTO();

        orderFullResponseDTO.setBranchId( entity.getBranchId() );
        orderFullResponseDTO.setOrderId( entity.getOrderId() );
        orderFullResponseDTO.setStaffId( entity.getStaffId() );
        if ( entity.getStatus() != null ) {
            orderFullResponseDTO.setStatus( entity.getStatus().name() );
        }
        orderFullResponseDTO.setTableId( entity.getTableId() );
        orderFullResponseDTO.setTotalAmount( entity.getTotalAmount() );

        return orderFullResponseDTO;
    }

    @Override
    public OrderResponseDTO toResponseDTO(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setOrderId( entity.getOrderId() );
        if ( entity.getStatus() != null ) {
            orderResponseDTO.setStatus( entity.getStatus().name() );
        }

        return orderResponseDTO;
    }
}
