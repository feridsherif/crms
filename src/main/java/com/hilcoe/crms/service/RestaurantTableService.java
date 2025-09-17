package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.dto.RestaurantTableResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.RestaurantTable;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.exception.RestaurantTableNotFoundException;
import com.hilcoe.crms.mapper.RestaurantTableMapper;
import com.hilcoe.crms.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantTableService {
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private RestaurantTableMapper restaurantTableMapper;

    public RestaurantTableDTO createTable(RestaurantTableDTO dto) {
        RestaurantTable table = restaurantTableMapper.toEntity(dto);
        table.setStatus(RestaurantTable.RestaurantTableStatus.AVAILABLE); // Always set to AVAILABLE on creation
        table = restaurantTableRepository.save(table);
        return restaurantTableMapper.toDTO(table);
    }

    public RestaurantTableDTO getTableById(Long id) {
        RestaurantTable table = restaurantTableRepository.findById(id)
            .orElseThrow(() -> new RestaurantTableNotFoundException(id));
        return restaurantTableMapper.toDTO(table);
    }

    public List<RestaurantTableDTO> getAllTables() {
        return restaurantTableRepository.findAll().stream().map(restaurantTableMapper::toDTO).collect(Collectors.toList());
    }

    public RestaurantTableDTO updateTable(Long id, RestaurantTableDTO dto) {
        RestaurantTable table = restaurantTableRepository.findById(id)
            .orElseThrow(() -> new RestaurantTableNotFoundException(id));
        Branch branch = new Branch();
        branch.setBranchId(dto.getBranchId());
        table.setBranch(branch);
        table.setTableNumber(dto.getTableNumber());
        table.setCapacity(dto.getCapacity());
        table.setLocation(dto.getLocation());
        // table.setStatus(dto.getStatus()); // Removed, status not updatable from DTO
        table = restaurantTableRepository.save(table);
        return restaurantTableMapper.toDTO(table);
    }

    public void deleteTable(Long id) {
        if (!restaurantTableRepository.existsById(id)) {
            throw new RestaurantTableNotFoundException(id);
        }
        restaurantTableRepository.deleteById(id);
    }

    public PaginatedResponseDTO<RestaurantTableDTO> getPaginatedTables(int page, int size) {
        Page<RestaurantTable> tablePage = restaurantTableRepository.findAll(PageRequest.of(page, size));
        List<RestaurantTableDTO> dtos = tablePage.getContent().stream()
            .map(restaurantTableMapper::toDTO)
            .collect(Collectors.toList());
        return new PaginatedResponseDTO<>(
            dtos,
            tablePage.getNumber(),
            tablePage.getSize(),
            tablePage.getTotalElements(),
            tablePage.getTotalPages(),
            tablePage.hasNext(),
            tablePage.hasPrevious(),
            null, // sort
            null, // filter
            tablePage.getNumber() * tablePage.getSize() + 1,
            tablePage.getNumber() * tablePage.getSize() + dtos.size(),
            null, // nextPageUrl
            null  // previousPageUrl
        );
    }
}