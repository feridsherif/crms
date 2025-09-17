package com.hilcoe.crms.dto;

import java.util.List;

public class PaginatedResponseDTO<T> {
	private List<T> content;
	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean hasNext;
	private boolean hasPrevious;
	private String sort;
	private Object filter;
	private long firstElementIndex;
	private long lastElementIndex;
	private String nextPageUrl;
	private String previousPageUrl;

	public PaginatedResponseDTO() {
	}

	public PaginatedResponseDTO(List<T> content, int page, int size, long totalElements, int totalPages) {
		this(content, page, size, totalElements, totalPages, false, false, null, null, 0, 0, null, null);
	}

	public PaginatedResponseDTO(List<T> content, int page, int size, long totalElements, int totalPages,
			boolean hasNext, boolean hasPrevious, String sort, Object filter,
			long firstElementIndex, long lastElementIndex,
			String nextPageUrl, String previousPageUrl) {
		this.content = content;
		this.page = page;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
		this.sort = sort;
		this.filter = filter;
		this.firstElementIndex = firstElementIndex;
		this.lastElementIndex = lastElementIndex;
		this.nextPageUrl = nextPageUrl;
		this.previousPageUrl = previousPageUrl;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Object getFilter() {
		return filter;
	}

	public void setFilter(Object filter) {
		this.filter = filter;
	}

	public long getFirstElementIndex() {
		return firstElementIndex;
	}

	public void setFirstElementIndex(long firstElementIndex) {
		this.firstElementIndex = firstElementIndex;
	}

	public long getLastElementIndex() {
		return lastElementIndex;
	}

	public void setLastElementIndex(long lastElementIndex) {
		this.lastElementIndex = lastElementIndex;
	}

	public String getNextPageUrl() {
		return nextPageUrl;
	}

	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	public String getPreviousPageUrl() {
		return previousPageUrl;
	}

	public void setPreviousPageUrl(String previousPageUrl) {
		this.previousPageUrl = previousPageUrl;
	}
}