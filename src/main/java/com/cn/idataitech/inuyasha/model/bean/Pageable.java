package com.cn.idataitech.inuyasha.model.bean;

import java.util.List;


public class Pageable<T> {

    private long total;
    private long pageSize;
    private long totalPage;
    private long currentPage;
    private long start;
    private List<T> items;
    private Object extra;

    public Pageable(long total, long pageSize, long currentPage) {
        this.total = total;
        this.pageSize = pageSize;
        this.totalPage = (long) Math.ceil(total * 1.0 / pageSize);
        this.currentPage = Math.max(Math.min(currentPage, totalPage), 1);
        this.start = (this.currentPage - 1) * pageSize;
    }

    public long getTotal() {
        return total;
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public long getStart() {
        return start;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}

	
