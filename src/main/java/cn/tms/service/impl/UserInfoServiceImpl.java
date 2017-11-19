package cn.tms.service.impl;

import cn.tms.dao.IUserInfoDAO;
import cn.tms.entity.Role;
import cn.tms.entity.UserInfo;
import cn.tms.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 2017-9-27 09:42:36
 */
@Service("useService")
public class UserInfoServiceImpl implements IUserInfoService {
    //植入dao对象
    @Resource(name = "IUserInfoDAO")
    IUserInfoDAO userInfoDAO;


    public UserInfo isLogin(UserInfo info) {
        return userInfoDAO.isLogin(info);
    }

    public List<UserInfo> getAllUserInfo(Integer userid) {
        return this.userInfoDAO.getAllUserInfo(userid);
    }


    public List<Role> getRoleByUserId(Integer userid) {
        return this.userInfoDAO.getRoleByUserId(userid);
    }

    public UserInfo getUserInfoById(Integer userid) {
        return this.userInfoDAO.getUserInfoById(userid);
    }

    public Integer checkUserRoleIsExist(Integer userid, Integer roleid) {
        return this.userInfoDAO.checkUserRoleIsExist(userid,roleid);
    }

    public List<UserInfo> findAllUserInfo(Map<String, Object> page) {
        return this.userInfoDAO.findAllUserInfo(page);
    }

    public Integer getUserInfoCountByPage(Integer userid) {
        return this.userInfoDAO.getUserInfoCountByPage(userid);
    }
}
