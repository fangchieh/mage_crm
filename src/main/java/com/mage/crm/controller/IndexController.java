package com.mage.crm.controller;


import com.mage.crm.base.BaseController;
import com.mage.crm.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request) throws UnsupportedEncodingException {
        /**
         * 从request中，获取cookie 得到userName,和trueName
         */
        /*Cookie[]  cookies = request.getCookies();
        for (int i = 0; i <cookies.length ; i++) {
            Cookie cookie = cookies[i];
            if("userName".equals(cookie.getName())){
                request.setAttribute("userName",cookie.getValue());
            }else if("trueName".equals(cookie.getName())){
                request.setAttribute("trueName", URLDecoder.decode(cookie.getValue(),"utf-8"));
            }
        }*/
        request.setAttribute("userName", CookieUtil.getCookieValue(request, "userName"));
        request.setAttribute("trueName", CookieUtil.getCookieValue(request, "trueName"));
        return "main";
    }
}
