package com.wjb.util;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public class Pagination<T> {
    private int pages;
    private int recordCount;
    private int current;
    private List<T> recordset;

    /**
    *@Author:
    *@params: recordCount 总记录数  rows 页行数  current 当前页
    *@Date:11:14 2017/10/24
    */
    public Pagination(int recordCount, int rows, int current) {
        pages = 0;
        this.recordCount = recordCount;
        pages =  recordCount / rows;
        // 总页数
        pages = (recordCount % rows == 0) ? pages : pages + 1;
        // 当前页
        this.current =  current <= 1 ? 1 : current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<T> getRecordset() {
        return recordset;
    }

    public void setRecordset(List<T> recordset) {
        this.recordset = recordset;
    }
}
