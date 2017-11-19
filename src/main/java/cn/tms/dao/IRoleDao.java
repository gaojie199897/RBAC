package cn.tms.dao;

import cn.tms.entity.Role;
import java.util.List;
/**
 * Created by zhangyu on 2017/10/21.
 */
public interface IRoleDao {


    public List<Role> getAllRoles();


    /**
     * 根据id获取到角色信息
     * @param id
     * @return
     */
    public Role findRoleById(Integer id);

    public void AddRoleForUser(Integer userid,Integer roleid);


    public void DeleRoleForUser(Integer  userid,Integer roleid);


}
