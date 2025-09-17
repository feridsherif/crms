package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.entity.Branch;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T15:31:26+0300",
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

        branchDTO.setName( branch.getName() );
        branchDTO.setAddress( branch.getAddress() );
        branchDTO.setPhone( branch.getPhone() );

        return branchDTO;
    }

    @Override
    public Branch toEntity(BranchDTO branchDTO) {
        if ( branchDTO == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setName( branchDTO.getName() );
        branch.setAddress( branchDTO.getAddress() );
        branch.setPhone( branchDTO.getPhone() );

        return branch;
    }

    @Override
    public BranchResponseDTO toResponseDTO(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchResponseDTO branchResponseDTO = new BranchResponseDTO();

        branchResponseDTO.setBranchId( branch.getBranchId() );
        branchResponseDTO.setName( branch.getName() );
        branchResponseDTO.setAddress( branch.getAddress() );
        branchResponseDTO.setPhone( branch.getPhone() );

        return branchResponseDTO;
    }
}
