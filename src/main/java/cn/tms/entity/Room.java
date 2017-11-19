package cn.tms.entity;

import java.io.Serializable;

/**
 * Created by zhangyu on 2017/10/25.
 */
public class Room implements Serializable{

    private Integer  id;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
