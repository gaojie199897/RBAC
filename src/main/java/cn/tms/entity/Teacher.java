package cn.tms.entity;

import java.io.Serializable;

/**
 * Created by zhangyu on 2017/10/25.
 */
public class Teacher implements Serializable {

    private Integer  id;


    private String teaname;


    private Integer teatype;

    private Integer teasex;

    private Integer teaage;

    private  String teatel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }

    public Integer getTeatype() {
        return teatype;
    }

    public void setTeatype(Integer teatype) {
        this.teatype = teatype;
    }

    public Integer getTeasex() {
        return teasex;
    }

    public void setTeasex(Integer teasex) {
        this.teasex = teasex;
    }

    public Integer getTeaage() {
        return teaage;
    }

    public void setTeaage(Integer teaage) {
        this.teaage = teaage;
    }

    public String getTeatel() {
        return teatel;
    }

    public void setTeatel(String teatel) {
        this.teatel = teatel;
    }
}
