package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.UserDTO;
import com.hilcoe.crms.dto.UserResponseDTO;
import com.hilcoe.crms.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T17:03:02+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setRoleIds( mapRolesToIds( entity.getRoles() ) );
        userDTO.setEmail( entity.getEmail() );
        userDTO.setUserId( entity.getUserId() );
        userDTO.setUsername( entity.getUsername() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setRoles( mapIdsToRoles( dto.getRoleIds() ) );
        user.setEmail( dto.getEmail() );
        user.setUsername( dto.getUsername() );

        return user;
    }

    @Override
    public UserResponseDTO toResponseDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setRoleIds( mapRolesToIds( entity.getRoles() ) );
        userResponseDTO.setEmail( entity.getEmail() );
        userResponseDTO.setUserId( entity.getUserId() );
        userResponseDTO.setUsername( entity.getUsername() );

        return userResponseDTO;
    }
}
