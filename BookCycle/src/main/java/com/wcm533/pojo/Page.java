package com.wcm533.pojo;

import java.util.List;

/**
 * @ClassName Page
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 15:35
 **/
public class Page<T> {

    public static final Integer PAGE_INDEX_SIZE=8;
    public static final Integer PAGE_MANAGER_SIZE=12;
    //当前页码
    private Integer pageNo;
    //记录总数
    private Integer pageTotalCount;
    //总页码
    private Integer pageTotal;
    //当前页显示的数量
    private Integer pageSize;
    //当前页图书数据
    private List<T> pageItems;
    //请求地址
    private String pageUrl;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotalCount, Integer pageTotal, Integer pageSize, List<T> pageItems,String pageUrl) {
        this.pageNo = pageNo;
        this.pageTotalCount = pageTotalCount;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageItems = (List<T>) pageItems;
        this.pageUrl=pageUrl;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageItems() {
        return (List<T>) pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = (List<T>) pageItems;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //检查数据有效性，
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotalCount=" + pageTotalCount +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageItems=" + pageItems +
                ", url='" + pageUrl + '\'' +
                '}';
    }
}
