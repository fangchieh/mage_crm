package com.mage.crm.dao;

import com.mage.crm.vo.OrderDetails;

import java.util.List;

public interface OrderDetailsDao {
    public List<OrderDetails> queryOrderDetailsByOrderId(Integer orderId);
}