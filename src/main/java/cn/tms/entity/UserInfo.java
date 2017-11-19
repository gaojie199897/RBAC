package cn.tms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 2017-09-27.
 * 用户实体
 */
public class UserInfo implements Serializable {
    private Integer userid;
    private String username;
    private String userpwd;
    private Integer usertype;
    private String usertel;
    private String useremail;


    private List<Role> roles ;
    private String  rolename;


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getUsertel() {
        return usertel;
    }
    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}


