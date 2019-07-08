package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.SaleChanceDao;
import com.mage.crm.query.SaleChanceQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService {
    @Resource
    private SaleChanceDao saleChanceDao;

    /**
     * 插入数据前的检测
     * 1.客户名称不能为空
     * 2.联系人不能为空
     * 3.联系电话不能为空
     * 额外添加的数据.
     * is_valid=1
     * createDate
     * updateDate
     * 如果分配人不为空state=0 否在为1
     * 开发结果 0-3代表 未开发 开发中 开发成功 开发失败
     *
     * @param saleChance
     */
    public void insert(SaleChance saleChance) {
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        saleChance.setIsValid(1);
        saleChance.setState(0);
        saleChance.setDevResult(0);
        if (!StringUtils.isBlank(saleChance.assignMan)) {
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }
        AssertUtil.isTrue(saleChanceDao.insert(saleChance) < 1, "数据插入失败");
    }

    public void checkParams(String customerName, String linkMan, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "联系电话不能为空");
    }

    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        //引入分页信息,BaseQuery参数page,rows,必须一样
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getRows());
        List<SaleChance> list = saleChanceDao.querySaleChancesByParams(saleChanceQuery);
        PageInfo<SaleChance> pageInfo = new PageInfo<>(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    /**
     * 修改前的检测
     * 1.客户名称不能为空
     * 2.联系人不能为空
     * 3.联系电话不能为空
     * 额外数据补
     * 1 更新时间
     * 2 当前分配人不为空
     *
     * @param saleChance
     */
    public void update(SaleChance saleChance) {
        checkParams(saleChance.customerName, saleChance.linkMan, saleChance.linkPhone);
        saleChance.setUpdateDate(new Date());
        saleChance.setState(0);
        if (!StringUtils.isBlank(saleChance.assignMan)) {
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }
        AssertUtil.isTrue(saleChanceDao.update(saleChance) < 1, "数据更新失败");
    }

    public void delete(Integer[] ids) {
        AssertUtil.isTrue(saleChanceDao.delete(ids) < 1, "营销机会删除失败");
    }

    public SaleChance querySaleChanceById(String id) {
        return saleChanceDao.querySaleChanceById(id);
    }

    public void updateSaleChanceDevResult(Integer devResult, Integer saleChanceId) {
        AssertUtil.isTrue(saleChanceDao.updateSaleChanceDevResult(devResult, saleChanceId) < 1, "修改开发结果失败");
    }
}