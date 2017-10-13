package com.best.publishing.so;


import java.io.Serializable;
import java.util.Date;

public abstract class AbstractSo extends PagingSo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date createdTimeFrom;

    private Date createdTimeTo;

    private String status;

    public Date getCreatedTimeFrom() {
        return createdTimeFrom;
    }

    public void setCreatedTimeFrom(Date createdTimeFrom) {
        this.createdTimeFrom = createdTimeFrom;
    }

    public Date getCreatedTimeTo() {
        return createdTimeTo;
    }

    public void setCreatedTimeTo(Date createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
