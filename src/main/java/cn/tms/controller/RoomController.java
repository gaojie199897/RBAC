package cn.tms.controller;

import cn.tms.entity.Privilege;
import cn.tms.entity.Role;
import cn.tms.entity.Room;
import cn.tms.service.IPrivilegeService;
import cn.tms.service.IRoleService;
import cn.tms.service.IRoomService;
import cn.tms.utils.GetTimeForDate;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
@Controller
public class RoomController {



    @Resource
    private IRoomService iRoomService;



    @Before(value = "")
    public void getAllRoomS(HttpServletRequest request){

    }

    @ResponseBody
    @RequestMapping("/findAllRooms")
    public Object findAllRooms(HttpServletRequest request){
        List<Room> allRooms = this.iRoomService.findAllRooms();
        return allRooms;
    }


    @ResponseBody
    @RequestMapping("/getAweekTime")
    public Object getAweek(){

        return GetTimeForDate.getDate();
    }








}
