package com.hilcoe.crms.exception;

public class CategoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(Long id) {
        super("Category not found with id: " + id);
    }
}
