package com.project.server.dto;

import java.util.List;

public class PageDto<T> {

    // The current page number.
    protected int page;

    // The number of items in each page.
    protected int size;

    // The total number of items.
    protected long total;

    // The items we select in this page.
    protected List<T> list;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
