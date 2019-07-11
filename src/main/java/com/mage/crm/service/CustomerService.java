package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.CustomerDao;
import com.mage.crm.query.CustomerQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.Customer;
import com.mage.crm.vo.CustomerLoss;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;

    public List<Customer> queryAllCustomers() {
        return customerDao.queryAllCustomers();
    }

    public Customer queryCustomerById(Integer id) {
        return customerDao.queryCustomerById(id);
    }

    public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery) {
        PageHelper.startPage(customerQuery.getPage(), customerQuery.getRows());
        List<Customer> list = customerDao.queryCustomersByParams(customerQuery);
        PageInfo<Customer> pageInfo = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    /**
     * 插入数据前的检查
     * <p>
     * 额外数据的补录
     * 创建时间 更新时间
     * 流失状态为0 为流失
     * 有效状态为1
     *
     * @param customer
     */
    public void insert(Customer customer) {
        checkParams(customer.getName(), customer.getFr(), customer.getPhone());
        customer.setState(0);
        customer.setIsValid(1);
        customer.setKhno("KH" + new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()));
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDao.insert(customer) < 1, "客户信息添加失败");
    }

    public void checkParams(String customerName, String fr, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(fr), "法人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "联系电话不能为空");
    }

    public void update(Customer customer) {
        checkParams(customer.getName(), customer.getFr(), customer.getPhone());
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDao.update(customer) < 1, "客户信息修改失败");
    }

    public void delete(Integer[] ids) {
        AssertUtil.isTrue(customerDao.delete(ids) < 1, "客户信息删除失败");
    }

    public void updateCustomerLossState() {
        List<CustomerLoss> list = customerDao.queryCustomerLoss();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CustomerLoss customerLoss = list.get(i);
                customerLoss.setState(0);
                customerLoss.setIsValid(1);
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
            }
        }
        AssertUtil.isTrue(customerDao.insertBatch(list) < 1, "客户流失数据添加失败");
    }

}

