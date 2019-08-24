package com.bdqn.entity;

public class Page {
    private Integer totalCount;
    private Integer currentPageNo;
    private Integer totalPageCount;
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = (page-1)*5;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Page() {
    }

    public Page(Integer totalCount, Integer currentPageNo, Integer totalPageCount, Integer page) {
        this.totalCount = totalCount;
        this.currentPageNo = currentPageNo;
        this.totalPageCount = totalPageCount;
        this.setPage(page) ;
    }
}
