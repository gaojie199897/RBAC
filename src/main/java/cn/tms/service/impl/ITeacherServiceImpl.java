package cn.tms.service.impl;

import cn.tms.dao.ITeacherDao;
import cn.tms.entity.Teacher;
import cn.tms.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/25.
 */
@Service("iTeacherServiceImpl")
public class ITeacherServiceImpl implements ITeacherService {


    @Resource
    private ITeacherDao iTeacherDao;


    public List<Teacher> findAllTeas() {
        return this.iTeacherDao.findAllTeas();
    }
}
