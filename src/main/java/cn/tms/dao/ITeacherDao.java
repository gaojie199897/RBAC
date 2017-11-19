package cn.tms.dao;

import cn.tms.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/25.
 */
@Repository
public interface ITeacherDao {


    public List<Teacher> findAllTeas();


}
