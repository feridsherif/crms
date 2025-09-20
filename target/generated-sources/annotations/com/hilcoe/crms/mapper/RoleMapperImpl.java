package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.entity.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T10:08:04+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDTO(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setDescription( entity.getDescription() );
        roleDTO.setName( entity.getName() );
        roleDTO.setRoleId( entity.getRoleId() );

        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setDescription( dto.getDescription() );
        role.setName( dto.getName() );
        role.setRoleId( dto.getRoleId() );

        return role;
    }
}
