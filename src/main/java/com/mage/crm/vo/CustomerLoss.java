package com.mage.crm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mage.crm.base.BaseVO;

import java.util.Date;

public class CustomerLoss extends BaseVO {
    private Integer id;
    private String cusNo;
    private String cusName;
    private String cusManager;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastOrderTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date comfirmLossTime;

    private Integer state;
    private String lossReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo == null ? null : cusNo.trim();
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    public String getCusManager() {
        return cusManager;
    }

    public void setCusManager(String cusManager) {
        this.cusManager = cusManager == null ? null : cusManager.trim();
    }

    public Date getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(Date lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public Date getComfirmLossTime() {
        return comfirmLossTime;
    }

    public void setComfirmLossTime(Date comfirmLossTime) {
        this.comfirmLossTime = comfirmLossTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLossReason() {
        return lossReason;
    }

    public void setLossReason(String lossReason) {
        this.lossReason = lossReason == null ? null : lossReason.trim();
    }
}
