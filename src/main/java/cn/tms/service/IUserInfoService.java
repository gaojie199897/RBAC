package cn.tms.service;

import cn.tms.entity.Role;
import cn.tms.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Happy on 2017-09-27.
 */
public interface IUserInfoService {
    //登录的方法
    public UserInfo isLogin(UserInfo info);

    public List<UserInfo> getAllUserInfo(Integer userid);

    public List<Role> getRoleByUserId(Integer userid);

    public UserInfo getUserInfoById(Integer userid);

    public Integer checkUserRoleIsExist(Integer userid,Integer roleid);

    /**
     * 分页
     * @param page
     * @return
     */
    public List<UserInfo> findAllUserInfo(Map<String, Object> page);

    public Integer getUserInfoCountByPage(Integer userid);

}
