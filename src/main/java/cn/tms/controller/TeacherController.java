package cn.tms.controller;

import cn.tms.entity.Teacher;
import cn.tms.service.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/25.
 */
@Controller
public class TeacherController {


    @Resource
    private ITeacherService iTeacherService;


    @ResponseBody
    @RequestMapping("/findAllTeas")
    public Object  findAllTeas(){
        List<Teacher> allTeas = this.iTeacherService.findAllTeas();
        return allTeas;
    }



}
