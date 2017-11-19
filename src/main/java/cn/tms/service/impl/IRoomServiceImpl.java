package cn.tms.service.impl;

import cn.tms.dao.IRoomDao;
import cn.tms.entity.Room;
import cn.tms.service.IRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/26.
 */
@Service("iRoomServiceImpl")
public class IRoomServiceImpl implements IRoomService {



    @Resource
    private IRoomDao iRoomDao;


    public List<Room> findAllRooms() {
        return this.iRoomDao.findAllRooms();
    }
}
