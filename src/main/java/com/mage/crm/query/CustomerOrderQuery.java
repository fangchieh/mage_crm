package com.mage.crm.query;

import com.mage.crm.base.BaseQuery;

public class CustomerOrderQuery extends BaseQuery {
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}