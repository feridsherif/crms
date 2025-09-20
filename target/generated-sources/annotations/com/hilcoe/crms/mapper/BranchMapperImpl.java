package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.entity.Branch;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T10:08:04+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchDTO toDTO(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchDTO branchDTO = new BranchDTO();

        branchDTO.setAddress( branch.getAddress() );
        branchDTO.setName( branch.getName() );
        branchDTO.setPhone( branch.getPhone() );

        return branchDTO;
    }

    @Override
    public Branch toEntity(BranchDTO branchDTO) {
        if ( branchDTO == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setAddress( branchDTO.getAddress() );
        branch.setName( branchDTO.getName() );
        branch.setPhone( branchDTO.getPhone() );

        return branch;
    }

    @Override
    public BranchResponseDTO toResponseDTO(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchResponseDTO branchResponseDTO = new BranchResponseDTO();

        branchResponseDTO.setAddress( branch.getAddress() );
        branchResponseDTO.setBranchId( branch.getBranchId() );
        branchResponseDTO.setName( branch.getName() );
        branchResponseDTO.setPhone( branch.getPhone() );

        return branchResponseDTO;
    }
}
