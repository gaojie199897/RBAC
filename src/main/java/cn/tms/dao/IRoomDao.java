package cn.tms.dao;

import cn.tms.entity.Role;
import cn.tms.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
@Repository
public interface IRoomDao {


    /**
     * h获取所有的机房
     * @return
     */
    public List<Room> findAllRooms();

}
