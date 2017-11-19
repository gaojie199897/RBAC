package cn.tms.service.impl;

import cn.tms.dao.IPrivilegeDao;
import cn.tms.entity.Privilege;
import cn.tms.service.IPrivilegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/20.
 */
@Service("iPrivilegeServiceImpl")
public class IPrivilegeServiceImpl implements IPrivilegeService {

    @Resource
    private IPrivilegeDao iPrivilegeDao;

    public List<Privilege> getAllPrivilegesByEmpId(Integer id) {
        return this.iPrivilegeDao.getAllPrivilegesByEmpId(id);
    }

    public List<Privilege> getAllPriv() {
        return this.iPrivilegeDao.getAllPriv();
    }

    public List<Privilege> getAllRrivByRoleId(Integer roleid) {
        return this.iPrivilegeDao.getAllRrivByRoleId(roleid);
    }

    public Integer checkPrivIsExist(Integer roleid, Integer privalegeId) {
        return this.iPrivilegeDao.checkPrivIsExist(roleid,privalegeId);
    }

    public void AddPrivForRole(Integer roleid, Integer privalegeId) {
        this.iPrivilegeDao.AddPrivForRole(roleid,privalegeId);
    }

    public void delePrivForRole(Integer roleid, Integer privalegeId) {
        this.iPrivilegeDao.delePrivForRole(roleid,privalegeId);
    }


    public void delePrivForTree(Integer roleid){
        this.iPrivilegeDao.delePrivForTree(roleid);
    }
}
