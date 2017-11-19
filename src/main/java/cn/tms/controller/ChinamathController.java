package cn.tms.controller;

import cn.tms.entity.Chinamath;
import cn.tms.entity.NewsType;
import cn.tms.entity.Privilege;
import cn.tms.service.impl.ChinamathServiceImpl;
import cn.tms.service.impl.NewsTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Gao on 2017/11/6.
 */
@Controller
public class ChinamathController {
    @Autowired
    ChinamathServiceImpl service;


    @ResponseBody
    @RequestMapping("/selectallChinaMath")
    public Object getTree(){
        List<Chinamath> rootMenus=new ArrayList<Chinamath>();
        List<Chinamath> select = service.select();
        for (Chinamath item:select){
            Chinamath childMenu=item;
            String pid = childMenu.getParentcode();
            if (pid.equals("0")){
                rootMenus.add(item);
            }else{
                for (Chinamath innerMenu:select){
                    String id = innerMenu.getSyscode();
                    if (id.equals(pid)){
                        Chinamath parentMenu=innerMenu;
                        parentMenu.getChildren().add(childMenu);
                        break;
                    }
                }
            }
        }
        return  rootMenus;
    }
}
