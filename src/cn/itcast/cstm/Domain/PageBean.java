package cn.itcast.cstm.Domain;

import java.util.List;

public class PageBean<T> {
    private int pageNum;//当前页码
//    private int totalPage;//总页数
    private int totalRecords;//总记录数
    private int pageSize;//每页的记录数
    private List<T> beanList;//当前页的记录

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPage() {
        /*
        * 通过总记录数和每页的记录数来计算总页数
        * */
        int tp = totalRecords/pageSize;
        return totalRecords%pageSize==0 ? tp : tp+1;
    }

//    public void setTotalPage(int totalPage) {
//        this.totalPage = totalPage;
//    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public PageBean() {
    }

    public PageBean(int pageNum, int totalRecords, int pageSize, List<T> beanList) {
        this.pageNum = pageNum;
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.beanList = beanList;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", totalPage=" + getTotalPage() +
                ", totalRecords=" + totalRecords +
                ", pageSize=" + pageSize +
                ", beanList=" + beanList +
                '}';
    }
}
