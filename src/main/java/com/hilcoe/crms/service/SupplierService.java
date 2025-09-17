package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.repository.SupplierRepository;
import com.hilcoe.crms.exception.SupplierNotFoundException;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierResponseDTO addSupplier(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setPhone(dto.getPhone());
        supplier.setTerms(dto.getTerms());
        Supplier saved = supplierRepository.save(supplier);
        return toResponseDTO(saved);
    }

    public SupplierResponseDTO updateSupplier(Long id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new SupplierNotFoundException(id));
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setPhone(dto.getPhone());
        supplier.setTerms(dto.getTerms());
        Supplier updated = supplierRepository.save(supplier);
        return toResponseDTO(updated);
    }

    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new SupplierNotFoundException(id);
        }
        supplierRepository.deleteById(id);
    }

    public SupplierResponseDTO getSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new SupplierNotFoundException(id));
        return toResponseDTO(supplier);
    }

    public List<SupplierResponseDTO> getSuppliers() {
        return supplierRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public PaginatedResponseDTO<SupplierResponseDTO> getSuppliersPaginated(Pageable pageable) {
        Page<Supplier> page = supplierRepository.findAll(pageable);
        List<SupplierResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(Collectors.toList());
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
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    private SupplierResponseDTO toResponseDTO(Supplier supplier) {
        return new SupplierResponseDTO(
            supplier.getSupplierId(),
            supplier.getName(),
            supplier.getContact(),
            supplier.getPhone(),
            supplier.getTerms()
        );
    }
}
