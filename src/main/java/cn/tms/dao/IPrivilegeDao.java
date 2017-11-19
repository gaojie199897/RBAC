package cn.tms.dao;

import cn.tms.entity.Privilege;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyu on 2017/10/20.
 */
@Repository("iPrivilegeDao")
public interface IPrivilegeDao {





    /**
     * 1.根据 用户编号  获取所有权限
     * @param id
     * @return
     */
    public List<Privilege> getAllPrivilegesByEmpId(Integer id);


    /**
     * 获取所有的权限
     * @return
     */
    public List<Privilege> getAllPriv();

    /**
     * 根据角色id 获取角色权限
     * @param roleid
     * @return
     */
    public List<Privilege> getAllRrivByRoleId(Integer roleid);


    /**
     * 检验添加的权限是否已经存在
     * @param roleid
     * @param privalegeId
     * @return
     */
    public Integer checkPrivIsExist(Integer roleid,Integer privalegeId);


    /**
     * 为角色添加权限
     * @param roleid
     * @param privalegeId
     */
    public void AddPrivForRole(Integer roleid,Integer privalegeId);


    /**
     * 删除权限
     * @param roleid
     * @param privalegeId
     */
    public void  delePrivForRole(Integer roleid,Integer privalegeId);


    /**
     * 根据 角色删除当前角色所有的权限id
     * @param roleid
     */
    public void  delePrivForTree(Integer roleid);







}
