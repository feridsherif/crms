package com.hilcoe.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.RestaurantTable;
import com.hilcoe.crms.exception.RestaurantTableNotFoundException;
import com.hilcoe.crms.mapper.RestaurantTableMapper;
import com.hilcoe.crms.repository.RestaurantTableRepository;

@Service
public class RestaurantTableService {
	@Autowired
	private RestaurantTableMapper restaurantTableMapper;

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	public RestaurantTableDTO createTable(RestaurantTableDTO dto) {
		RestaurantTable table = restaurantTableMapper.toEntity(dto);
		table.setStatus(RestaurantTable.RestaurantTableStatus.AVAILABLE); // Always set to AVAILABLE on creation
		table = restaurantTableRepository.save(table);
		return restaurantTableMapper.toDTO(table);
	}

	public void deleteTable(Long id) {
		if (!restaurantTableRepository.existsById(id)) {
			throw new RestaurantTableNotFoundException(id);
		}
		restaurantTableRepository.deleteById(id);
	}

	public List<RestaurantTableDTO> getAllTables() {
		return restaurantTableRepository.findAll().stream().map(restaurantTableMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PaginatedResponseDTO<RestaurantTableDTO> getPaginatedTables(int page, int size) {
		Page<RestaurantTable> tablePage = restaurantTableRepository.findAll(PageRequest.of(page, size));
		List<RestaurantTableDTO> dtos = tablePage.getContent().stream().map(restaurantTableMapper::toDTO)
				.collect(Collectors.toList());
		return new PaginatedResponseDTO<>(dtos, tablePage.getNumber(), tablePage.getSize(),
				tablePage.getTotalElements(), tablePage.getTotalPages(), tablePage.hasNext(), tablePage.hasPrevious(),
				null, // sort
				null, // filter
				tablePage.getNumber() * tablePage.getSize() + 1,
				tablePage.getNumber() * tablePage.getSize() + dtos.size(), null, // nextPageUrl
				null // previousPageUrl
		);
	}

	public PaginatedResponseDTO<RestaurantTableDTO> getPaginatedTablesAdvanced(Integer tableNumber, Long branchId,
			String status, int page, int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<RestaurantTable> tablePage = restaurantTableRepository.findAll(pageable);
		java.util.List<RestaurantTable> filtered = tablePage.getContent().stream()
				.filter(t -> tableNumber == null
						|| (t.getTableNumber() != null && t.getTableNumber().equals(tableNumber)))
				.filter(t -> branchId == null || (t.getBranch() != null && t.getBranch().getBranchId() != null
						&& t.getBranch().getBranchId().equals(branchId)))
				.filter(t -> status == null || (t.getStatus() != null && t.getStatus().name().equalsIgnoreCase(status)))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<RestaurantTableDTO> dtos = filtered.stream().map(restaurantTableMapper::toDTO)
				.collect(java.util.stream.Collectors.toList());
		return new PaginatedResponseDTO<>(dtos, tablePage.getNumber(), tablePage.getSize(),
				tablePage.getTotalElements(), tablePage.getTotalPages(), tablePage.hasNext(), tablePage.hasPrevious(),
				sort.toString(), null, tablePage.getNumber() * tablePage.getSize() + 1,
				tablePage.getNumber() * tablePage.getSize() + dtos.size(), null, null);
	}

	public RestaurantTableDTO getTableById(Long id) {
		RestaurantTable table = restaurantTableRepository.findById(id)
				.orElseThrow(() -> new RestaurantTableNotFoundException(id));
		return restaurantTableMapper.toDTO(table);
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
}