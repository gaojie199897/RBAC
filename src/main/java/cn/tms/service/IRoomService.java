package cn.tms.service;

import cn.tms.entity.Role;
import cn.tms.entity.Room;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
public interface IRoomService {

    /**
     * h获取所有的机房
     * @return
     */
    public List<Room> findAllRooms();

}
