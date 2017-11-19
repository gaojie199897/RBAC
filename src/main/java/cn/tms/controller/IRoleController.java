package cn.tms.controller;

import cn.tms.entity.Privilege;
import cn.tms.entity.Role;
import cn.tms.service.IPrivilegeService;
import cn.tms.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
@Controller
public class IRoleController {



    @Resource
    private IRoleService iRoleService;

    @Resource(name = "iPrivilegeServiceImpl")
    private IPrivilegeService iPrivilegeService;


    @ResponseBody
    @RequestMapping("/findAllRoles")
    public Object getAllRolse(){
        List<Role> allRoles = this.iRoleService.getAllRoles();
        for (Role item :
                allRoles) {
            List<Privilege> allPrivilegesByEmpId = this.iPrivilegeService.getAllRrivByRoleId(item.getId());
            StringBuilder builder  = new StringBuilder("");
            for (Privilege priv :
                    allPrivilegesByEmpId) {
                builder.append(priv.getName());
                builder.append(" ");
            }
            item.setPrivname(builder.toString());
        }
        return  allRoles;
    }




    @ResponseBody
    @RequestMapping("/addRoleForUser")
    public String addRoleByUserId(Integer userid, Integer roleid){
        this.iRoleService.AddRoleForUser(userid,roleid);
        return "y";
    }


    @ResponseBody
    @RequestMapping("/deleRoleForUser")
    public String deleRoleByUserId(Integer userid,Integer roleid){
        this.iRoleService.DeleRoleForUser(userid,roleid);
        return "y";
    }






}
