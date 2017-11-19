package cn.tms.entity;

import java.io.Serializable;

/**
 * Created by Happy on 2017-10-16.
 * 角色类
 */
public class Role implements Serializable {

    private Integer id;// 编号

    private String rolename;// 角色名称


    private String privname; //所有的权限


    public String getPrivname() {
        return privname;
    }

    public void setPrivname(String privname) {
        this.privname = privname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
