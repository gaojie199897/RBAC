package cn.tms.service;

import cn.tms.entity.Privilege;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/20.
 */
public interface IPrivilegeService {

    public List<Privilege> getAllPrivilegesByEmpId(Integer id);


    public List<Privilege> getAllPriv();

    public List<Privilege> getAllRrivByRoleId(Integer roleid);


    public Integer checkPrivIsExist(Integer roleid,Integer privalegeId);

    public void AddPrivForRole(Integer roleid,Integer privalegeId);

    public void  delePrivForRole(Integer roleid,Integer privalegeId);

    /**
     * 根据 角色删除当前角色所有的权限id
     * @param roleid
     */
    public void  delePrivForTree(Integer roleid);


}
