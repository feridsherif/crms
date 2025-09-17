package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.entity.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T09:18:42+0300",
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

        roleDTO.setRoleId( entity.getRoleId() );
        roleDTO.setName( entity.getName() );
        roleDTO.setDescription( entity.getDescription() );

        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( dto.getRoleId() );
        role.setName( dto.getName() );
        role.setDescription( dto.getDescription() );

        return role;
    }
}
