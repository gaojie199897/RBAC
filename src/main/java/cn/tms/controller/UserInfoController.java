package cn.tms.controller;

import cn.tms.entity.*;
import cn.tms.service.IPrivilegeService;
import cn.tms.service.IRoomService;
import cn.tms.service.IUserInfoService;
import cn.tms.service.impl.NewsTypeServiceImpl;
import cn.tms.utils.GetTimeForDate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Happy on 2017-08-21.
 */
@Controller
public class UserInfoController {

    //植入用户的service
    @Resource(name ="useService" )
    IUserInfoService userInfoService;


    @Resource
    private IRoomService iRoomService;

    @Resource(name = "iPrivilegeServiceImpl")
    private IPrivilegeService iPrivilegeService;

    @Autowired
    NewsTypeServiceImpl newsTypeServicenewsTypeService;


    //注销方法
    @RequestMapping("/doLoginOut")
    public String doLoginout(HttpServletRequest request){
        request.getSession().setAttribute("userinfo","");
        return "login";
    }


    //登录方法
    @RequestMapping("/doLogin")
    @ResponseBody
    public  Object doLogin(HttpSession session,UserInfo info){
        UserInfo user = userInfoService.isLogin(info);
        if (user!=null&&user.getUsername()!=null){
            //登录成功,将用户名放入Session

            session.setAttribute("userinfo",user);
            List<Room> allRooms = this.iRoomService.findAllRooms();
            session.setAttribute("list",allRooms);
            return "y";
        }else {
            //登录失败
            return "login";
        }
    }

    @RequestMapping("/doMain")
    public  String doMain(HttpServletRequest request){
        List<Privilege> rootMenus=new ArrayList<Privilege>();   //一级菜单集合
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userinfo");
        List<NewsType> combobox = newsTypeServicenewsTypeService.combobox();
        request.getSession().setAttribute("combobox",combobox);
        //根据用户id拿到权限集和
        List<Privilege> allPrivilegesByEmpId = this.iPrivilegeService.getAllPrivilegesByEmpId(userInfo.getUserid());
        for (Privilege item :
                allPrivilegesByEmpId) {
            System.out.println(item.getName()+"-------------------------------");
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
        }
        request.setAttribute("menulist",rootMenus);
        return "main";
    }





    //获取所有的管理员信息，admin管理所有的管理员权限
    @RequestMapping("/doFoundAdmins")
    @ResponseBody
    public Object getAllAdmin(HttpServletRequest request, HttpServletResponse response,String page, String rows) throws IOException {

        UserInfo userInfo =(UserInfo)request.getSession().getAttribute("userinfo");
        int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2": rows);//每页多少行
        currentpage = (currentpage-1)*pagesize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("currentpage",currentpage);
        map.put("pagesize",pagesize);
        map.put("userid",userInfo.getUserid());



        List<UserInfo> allUserInfo = this.userInfoService.findAllUserInfo(map);
        for (UserInfo item:
             allUserInfo) {
            List<Role> roleByUserId = this.userInfoService.getRoleByUserId(item.getUserid());
            StringBuilder  sb=new StringBuilder("");
            for (Role rs :
                    roleByUserId) {
                sb.append(rs.getRolename());
                sb.append(",");
            }
            item.setRolename(sb.toString());
        }
        JSONObject jsonObject   = new JSONObject();
        jsonObject.put("total",this.userInfoService.getUserInfoCountByPage(userInfo.getUserid()));
        jsonObject.put("rows",allUserInfo);
        response.getWriter().write(jsonObject.toString());//转化为JSOn格式
        return null;
    }

    //根据用户id获取用户 角色列表填充下拉框
    @RequestMapping("/cmbRoles")
    public Object cmbRole(){

        return  "";
    }

    //根据用户id获取用户对象本身
    @RequestMapping("/douserInfoByUserId")
    @ResponseBody
    public Object doUserInfo(Integer userid){

        UserInfo userInfoById = this.userInfoService.getUserInfoById(userid);
        List<Role> roleByUserId = this.userInfoService.getRoleByUserId(userid);
        StringBuilder sb = new StringBuilder("");
        for (Role item:
             roleByUserId) {
           sb.append(item.getRolename());
           sb.append(",");
        }
        userInfoById.setRolename(sb.toString());
        return userInfoById;
    }



    @ResponseBody
    @RequestMapping("/findAllRolesByUid")
    public Object getAllRolse(Integer userid,HttpServletRequest request){
        List<Role> allRoles = this.userInfoService.getRoleByUserId(userid);
        return  allRoles;
    }


    @ResponseBody
    @RequestMapping("/checkUserRoleIsExist")
    public Object checkUserRoleIsExist(Integer userid,Integer roleid){
        Integer integer = this.userInfoService.checkUserRoleIsExist(userid, roleid);
        if(integer>0){
            return "f";
        }else{
            return "y";
        }
    }



    //转向机房和教员设定界面
    @RequestMapping("/labWeekSet")
   public String labWeekSet(){
        return "/page/userManage";
   }




}
