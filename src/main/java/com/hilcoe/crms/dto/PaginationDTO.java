package com.hilcoe.crms.dto;

public class PaginationDTO {
    private int page = 0;
    private int size = 10;
    private String sort;

    public PaginationDTO() {}

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public String getSort() { return sort; }
    public void setSort(String sort) { this.sort = sort; }
}