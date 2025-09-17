package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.OrderItemDTO;
import com.hilcoe.crms.dto.OrderItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.entity.OrderItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T15:31:26+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO toDTO(OrderItem entity) {
        if ( entity == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setMenuItemId( entityMenuItemMenuItemId( entity ) );
        orderItemDTO.setQuantity( entity.getQuantity() );

        return orderItemDTO;
    }

    @Override
    public OrderItem toEntity(OrderItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setMenuItem( mapIdToMenuItem( dto.getMenuItemId() ) );
        orderItem.setQuantity( dto.getQuantity() );
        orderItem.setMenuItemId( dto.getMenuItemId() );

        return orderItem;
    }

    @Override
    public OrderItemResponseDTO toResponseDTO(OrderItem entity) {
        if ( entity == null ) {
            return null;
        }

        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();

        orderItemResponseDTO.setMenuItemId( entityMenuItemMenuItemId( entity ) );
        orderItemResponseDTO.setOrderItemId( entity.getOrderItemId() );
        orderItemResponseDTO.setQuantity( entity.getQuantity() );
        orderItemResponseDTO.setUnitPrice( entity.getUnitPrice() );

        return orderItemResponseDTO;
    }

    private Long entityMenuItemMenuItemId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        MenuItem menuItem = orderItem.getMenuItem();
        if ( menuItem == null ) {
            return null;
        }
        Long menuItemId = menuItem.getMenuItemId();
        if ( menuItemId == null ) {
            return null;
        }
        return menuItemId;
    }
}
