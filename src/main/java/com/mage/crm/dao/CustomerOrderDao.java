package com.mage.crm.dao;

import com.mage.crm.query.CustomerOrderQuery;
import com.mage.crm.vo.CustomerOrder;

import java.util.List;
import java.util.Map;

public interface CustomerOrderDao {
    public List<CustomerOrder> queryOrdersByCid(CustomerOrderQuery customerOrderQuery);

    public Map<String, Object> queryOrderInfoById(String orderId);
}

