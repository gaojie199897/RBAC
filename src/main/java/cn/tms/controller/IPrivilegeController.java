package cn.tms.controller;

import cn.tms.entity.Privilege;
import cn.tms.service.IPrivilegeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
@Controller
public class IPrivilegeController {




    @Resource(name = "iPrivilegeServiceImpl")
    private IPrivilegeService iPrivilegeService;



    @ResponseBody
    @RequestMapping("/checkPrivIsExist")
    public Object checkPrivIsExist(Integer  roleId,Integer privalegeId){
        Integer integer = this.iPrivilegeService.checkPrivIsExist(roleId, privalegeId);
        if(integer>0){
            return "f";
        }else{
            return "y";
        }
    }

    @ResponseBody
    @RequestMapping("/doAddPriv")
    public Object addPrivForRole(Integer  roleId,Integer privalegeId){
        this.iPrivilegeService.AddPrivForRole(roleId,privalegeId);
        return "y";
    }


    //tree树方式 添加权限
    @ResponseBody
    @RequestMapping("/doAddPrivForTree")
    public Object addPrivForTree(Integer  roleid,String privalegeId){
        System.out.println(roleid+"-------------"+privalegeId);
        //先删除角色
        if(roleid!=null&&privalegeId!=null){
            this.iPrivilegeService.delePrivForTree(roleid);
        }
        String[] split = privalegeId.split(",");
        for(int  i=0;i<split.length;i++){
            //添加角色
             this.iPrivilegeService.AddPrivForRole(roleid,Integer.parseInt(split[i].trim()));
            System.out.println(split[i].trim());
        }
        return "y";
    }



    @ResponseBody
    @RequestMapping("/getAllPriv")
    public Object getAllPriv(){
        List<Privilege> allPriv = this.iPrivilegeService.getAllPriv();
        return allPriv;
    }


    @ResponseBody
    @RequestMapping("/forcmbByPriv")
    public  Object findPrivForCmb(Integer id){
        List<Privilege> allRrivByRoleId = this.iPrivilegeService.getAllRrivByRoleId(id);
        return allRrivByRoleId;
    }


    @ResponseBody
    @RequestMapping("/delePriv")
    public Object delePrivForUser(Integer  roleId,String privalegeId){
        String[] split = privalegeId.split(",");
        for (int i=0;i<split.length;i++){
            this.iPrivilegeService.delePrivForRole(roleId,Integer.parseInt(split[i].trim()));
        }
        return "y";
    }


    @ResponseBody
    @RequestMapping("getAllMenusJson")
    public Object getPriv(HttpServletRequest request){
        List<Privilege> rootMenus=new ArrayList<Privilege>();   //一级菜单集合
        //根据用户id拿到权限集和
        List<Privilege> privilegeList = this.iPrivilegeService.getAllPriv();

        for (Privilege item:privilegeList){
            Privilege childMenu=item;
            int pid = childMenu.getParent();
            if (pid==0){
                rootMenus.add(item);
            }else{
                for (Privilege innerMenu:privilegeList){
                    Integer id = innerMenu.getId();
                    if (id==pid){
                        Privilege parentMenu=innerMenu;
                        parentMenu.getChildren().add(childMenu);
                        break;
                    }
                }
            }
        }
       /* for (Privilege item :
                allPrivilegesByEmpId) {
            int pid =item.getParent();
            if(pid==0){
                rootMenus.add(item);
            }
        }
        //遍历二级菜单 加入一级菜单的子菜单
        for (Privilege item :
                allPrivilegesByEmpId) {
            int pid =item.getParent();
            for (Privilege itemss :
                    rootMenus) {
                int id = itemss.getId();
                if(id==pid){
                    itemss.getChildren().add(item);
                }
            }
        }*/
        return rootMenus;
    }


     //根据角色id获取所有的权限
    @ResponseBody
    @RequestMapping("/findAddPrivByRoleid")
    public Object findAddPrivByRoleid(Integer roleid){
        List<Privilege> allRrivByRoleId = this.iPrivilegeService.getAllRrivByRoleId(roleid);
        return allRrivByRoleId;
    }





}
