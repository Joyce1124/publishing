package com.best.publishing.so;

/**
 * 分页用到的基类So， 前台分页机制，必须要下面几个属相
 *
 */

public class PagingSo {
    /**
     * 每页查询数量
     */
    private int rows = 0;

    /**
     * 查询第几页
     */
    private int page = 0;


    /**
     * 用于排序的列名
     */
    private String sidx;

    /**
     * 排序的方式desc/asc
     */
    private String sord;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

}
