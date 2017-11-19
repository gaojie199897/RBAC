package cn.tms.controller;

import cn.tms.service.impl.NewsTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mr.Gao on 2017/11/8.
 */
@Controller
public class NewsTypeController {
    @Autowired
    NewsTypeServiceImpl  service;

    @ResponseBody
    @RequestMapping("/combobox")
    public Object combobox(){
        return service.combobox();
    }
}
