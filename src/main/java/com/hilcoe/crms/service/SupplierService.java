package com.hilcoe.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.exception.SupplierNotFoundException;
import com.hilcoe.crms.repository.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private SupplierRepository supplierRepository;

	public SupplierResponseDTO addSupplier(SupplierDTO dto, Long userId) {
		Supplier supplier = new Supplier();
		supplier.setName(dto.getName());
		supplier.setContact(dto.getContact());
		supplier.setPhone(dto.getPhone());
		supplier.setTerms(dto.getTerms());
		Supplier saved = supplierRepository.save(supplier);
		auditLogService.log(userId, "CREATE", "Supplier", saved.getSupplierId(), saved);
		return toResponseDTO(saved);
	}

	public void deleteSupplier(Long id, Long userId) {
		Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
		auditLogService.log(userId, "DELETE", "Supplier", supplier.getSupplierId(), supplier);
		supplierRepository.deleteById(id);
	}

	public SupplierResponseDTO getSupplier(Long id) {
		Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
		return toResponseDTO(supplier);
	}

	public List<SupplierResponseDTO> getSuppliers() {
		return supplierRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public PaginatedResponseDTO<SupplierResponseDTO> getSuppliersPaginated(Pageable pageable) {
		Page<Supplier> page = supplierRepository.findAll(pageable);
		List<SupplierResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
				.collect(Collectors.toList());
		int pageNum = page.getNumber();
		int pageSize = page.getSize();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		String sort = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<SupplierResponseDTO> getSuppliersPaginatedAdvanced(String name, String contact,
			String phone, String terms, int page, int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
		java.util.List<Supplier> filtered = supplierPage.getContent().stream()
				.filter(s -> name == null
						|| (s.getName() != null && s.getName().toLowerCase().contains(name.toLowerCase())))
				.filter(s -> contact == null
						|| (s.getContact() != null && s.getContact().toLowerCase().contains(contact.toLowerCase())))
				.filter(s -> phone == null
						|| (s.getPhone() != null && s.getPhone().toLowerCase().contains(phone.toLowerCase())))
				.filter(s -> terms == null
						|| (s.getTerms() != null && s.getTerms().toLowerCase().contains(terms.toLowerCase())))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<SupplierResponseDTO> content = filtered.stream().map(this::toResponseDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = supplierPage.getNumber();
		int pageSize = supplierPage.getSize();
		long totalElements = supplierPage.getTotalElements();
		int totalPages = supplierPage.getTotalPages();
		boolean hasNext = supplierPage.hasNext();
		boolean hasPrevious = supplierPage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	private SupplierResponseDTO toResponseDTO(Supplier supplier) {
		return new SupplierResponseDTO(supplier.getSupplierId(), supplier.getName(), supplier.getContact(),
				supplier.getPhone(), supplier.getTerms());
	}

	public SupplierResponseDTO updateSupplier(Long id, SupplierDTO dto, Long userId) {
		Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
		Supplier before = new Supplier();
		before.setSupplierId(supplier.getSupplierId());
		before.setName(supplier.getName());
		before.setContact(supplier.getContact());
		before.setPhone(supplier.getPhone());
		before.setTerms(supplier.getTerms());
		supplier.setName(dto.getName());
		supplier.setContact(dto.getContact());
		supplier.setPhone(dto.getPhone());
		supplier.setTerms(dto.getTerms());
		Supplier updated = supplierRepository.save(supplier);
		java.util.HashMap<String, Object> data = new java.util.HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Supplier", updated.getSupplierId(), data);
		return toResponseDTO(updated);
	}
}