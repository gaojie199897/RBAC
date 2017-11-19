package cn.tms.entity;

import java.util.Date;

/**
 * Created by Mr.Gao on 2017/11/8.
 */
public class News {
    private int id;
    private String newtitle;
    private String syscode;
    private int clickcount;
    private Integer userid;
    private Date update_time;
    private String typesss;
    private String date;
    private UserInfo userInfo;
    private Chinamath chinamath;
    private String context;
    private String abstracts;
    private String file;
    private String quanzhong;
    private String color;

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getQuanzhong() {
        return quanzhong;
    }

    public void setQuanzhong(String quanzhong) {
        this.quanzhong = quanzhong;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Chinamath getChinamath() {
        return chinamath;
    }

    public void setChinamath(Chinamath chinamath) {
        this.chinamath = chinamath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewtitle() {
        return newtitle;
    }

    public void setNewtitle(String newtitle) {
        this.newtitle = newtitle;
    }

    public int getClickcount() {
        return clickcount;
    }

    public void setClickcount(int clickcount) {
        this.clickcount = clickcount;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getTypesss() {
        return typesss;
    }

    public void setTypesss(String typesss) {
        this.typesss = typesss;
    }
}
