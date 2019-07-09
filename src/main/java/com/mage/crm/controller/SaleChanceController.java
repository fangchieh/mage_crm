package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.base.exceptions.ParamsException;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.SaleChanceQuery;
import com.mage.crm.service.SaleChanceService;
import com.mage.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Resource
    public SaleChanceService saleChanceService;

    @RequestMapping("index/{type}")
    public String index(@PathVariable("type") String type) {
        switch (type) {
            case "1":
                return "sale_chance";
            case "2":
                return "cus_dev_plan";
            default:
                return "error";
        }
    }

    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        Map<String, Object> map = saleChanceService.querySaleChancesByParams(saleChanceQuery);
        return map;
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(SaleChance saleChance) {
        MessageModel messageModel = new MessageModel();
        try {
            saleChanceService.insert(saleChance);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }

        messageModel.setResult("营销机会添加成功");
        return messageModel;
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(SaleChance saleChance) {

        MessageModel messageModel = new MessageModel();
        try {
            saleChanceService.update(saleChance);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        messageModel.setResult("营销机会修改成功");
        return messageModel;
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids) {

        MessageModel messageModel = new MessageModel();
        try {
            saleChanceService.delete(ids);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        messageModel.setResult("营销机会删除成功");
        return messageModel;
    }

    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public MessageModel updateSaleChanceDevResult(Integer devResult, Integer
            saleChanceId) {

        MessageModel messageModel = new MessageModel();
        try {
            saleChanceService.updateSaleChanceDevResult(devResult, saleChanceId);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        messageModel.setResult("操作成功");
        return messageModel;
    }
}