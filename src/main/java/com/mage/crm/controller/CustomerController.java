package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.base.exceptions.ParamsException;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.CustomerQuery;
import com.mage.crm.service.CustomerService;
import com.mage.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;

    @RequestMapping("queryAllCustomers")
    @ResponseBody
    public List<Customer> queryAllCustomers() {
        return customerService.queryAllCustomers();
    }

    @RequestMapping("index")
    public String index() {
        return "customer";
    }

    @ResponseBody
    @RequestMapping("queryCustomersByParams")
    public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomersByParams(customerQuery);
    }

    @ResponseBody
    @RequestMapping("insert")
    public MessageModel insert(Customer customer) {
        MessageModel messageModel = new MessageModel();
        try {
            customerService.insert(customer);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        messageModel.setResult("客户信息添加成功");
        return messageModel;
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Customer customer) {

        MessageModel messageModel = new MessageModel();
        try {
            customerService.update(customer);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        messageModel.setResult("客户信息修改成功");
        return messageModel;
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids) {

        MessageModel messageModel = new MessageModel();
        try {
            customerService.delete(ids);
        } catch (ParamsException e) {
            e.printStackTrace();
            messageModel.setCode(e.getCode());
            messageModel.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_DODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        messageModel.setResult("客户信息删除成功");
        return messageModel;
    }

    @RequestMapping("openCustomerOtherInfo/{type}/{id}")
    public String index(@PathVariable("type") String type, @PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.queryCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer_order";
    }
}
