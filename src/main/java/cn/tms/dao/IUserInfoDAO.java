package cn.tms.dao;

import cn.tms.entity.Role;
import cn.tms.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Happy on 2017-08-21.
 */
@Repository
public interface IUserInfoDAO {
    //登录的方法
    public UserInfo isLogin(UserInfo info);

    /**
     * 拿到所有的管理员 除了自己的
     * @return
     */
    public List<UserInfo> getAllUserInfo(Integer userid);

    /**
     * 根据用户id拿到角色
     * @param userid
     * @return
     */
    public List<Role> getRoleByUserId(Integer userid);


    /**
     * 根据用户id获取用户对象
     * @param userid
     * @return
     */
    public UserInfo getUserInfoById(Integer userid);


    /**
     * 检查用户添加的角色是否已经存在
     * @param userid
     * @param roleid
     * @return
     */
    public Integer checkUserRoleIsExist( Integer userid, Integer roleid);

    /**
     * 分页
     * @param page
     * @return
     */
    public List<UserInfo> findAllUserInfo(Map<String, Object> page);

    public Integer getUserInfoCountByPage(Integer userid);


}
