package cn.tms.service;

import cn.tms.entity.Role;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/21.
 */
public interface IRoleService {

    public List<Role> getAllRoles();

    public void AddRoleForUser(Integer userid,Integer roleid);

    public void DeleRoleForUser(Integer  userid,Integer roleid);


    public Role findRoleById(Integer id);

}
