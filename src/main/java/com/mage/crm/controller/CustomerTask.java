package com.mage.crm.controller;

import com.mage.crm.service.CustomerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomerTask {
    @Resource
    public CustomerService customerService;

    //每天的12点12分0秒触发这个任务
    @Scheduled(cron = "20 40 21 * * ?")
    public void lossTask() {
        customerService.updateCustomerLossState();
        System.out.println("你好，我被触发了--");
    }
}