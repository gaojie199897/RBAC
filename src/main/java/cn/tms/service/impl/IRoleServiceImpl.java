package cn.tms.service.impl;

import cn.tms.dao.IRoleDao;
import cn.tms.entity.Role;
import cn.tms.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
@Service("iRoleServiceImpl")
public class IRoleServiceImpl implements IRoleService {


    @Resource
    public IRoleDao iRoleDao;

    public List<Role> getAllRoles() {
        return this.iRoleDao.getAllRoles();
    }

    public void AddRoleForUser(Integer userid, Integer roleid) {
        this.iRoleDao.AddRoleForUser(userid,roleid);
    }

    public void DeleRoleForUser(Integer userid, Integer roleid) {
        this.iRoleDao.DeleRoleForUser(userid,roleid);
    }

    public Role findRoleById(Integer id) {
        return this.iRoleDao.findRoleById(id);
    }


}
