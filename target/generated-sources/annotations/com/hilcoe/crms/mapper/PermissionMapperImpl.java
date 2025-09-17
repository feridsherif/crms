package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PermissionResponseDTO;
import com.hilcoe.crms.entity.Permission;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T09:18:42+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDTO toDTO(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionDTO permissionDTO = new PermissionDTO();

        permissionDTO.setPermissionId( entity.getPermissionId() );
        permissionDTO.setName( entity.getName() );
        permissionDTO.setDescription( entity.getDescription() );

        return permissionDTO;
    }

    @Override
    public Permission toEntity(PermissionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setName( dto.getName() );
        permission.setDescription( dto.getDescription() );

        return permission;
    }

    @Override
    public PermissionResponseDTO toResponseDTO(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();

        permissionResponseDTO.setPermissionId( entity.getPermissionId() );
        permissionResponseDTO.setName( entity.getName() );
        permissionResponseDTO.setDescription( entity.getDescription() );

        return permissionResponseDTO;
    }
}
