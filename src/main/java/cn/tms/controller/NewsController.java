package cn.tms.controller;

import cn.tms.entity.News;
import cn.tms.entity.UserInfo;
import cn.tms.service.impl.NewsServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Gao on 2017/11/8.
 */
@Controller
public class NewsController {
   @Autowired
    NewsServiceImpl service;

    @ResponseBody
    @RequestMapping("selectList")
    public Object selectList(HttpServletResponse response, String syscode, Integer page, Integer rows,Integer id,String name) throws Exception {
        Integer pageindex=(page==null||page==0)?1:page;
        Integer pageSize=(rows==null||rows==0)?2:rows;

        System.out.println(name+"+++++++++++++++++++++++++++++++++++++");
        System.out.println(id+"+++++++++++++++++++++++++++++++++++++");
        String str;
        if(name!=null){
            byte[] b=name.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
             str=new String(b,"utf-8");//采用utf-8去接string
        }else{
            str=null;
        }

        System.out.println(id+"****"+name);
        Map<String ,Object> map=new HashMap<String ,Object>();
        map.put("syscode",syscode);
        map.put("pageindex",(pageindex-1)*pageSize);
        map.put("pagesize",pageSize);
        map.put("id",id);
        map.put("name",str);
        List<News> newss = service.selectList(map);
        for (News item:newss) {
            Date update_time = item.getUpdate_time();
            SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
            item.setDate(sdf.format(update_time));
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("total",this.service.count(map));
        jsonObject.put("rows",newss);
        response.getWriter().write(jsonObject.toString());//转化为JSOn格式
        return null;
    }
    @ResponseBody
    @RequestMapping("/addNews")
    public Object addNews(News news, HttpServletRequest request){
        System.out.println(news.getUserid());
        System.out.println(news.getAbstracts());
        System.out.println(news.getColor());
        System.out.println(news.getContext());
        System.out.println(news.getNewtitle());
        System.out.println(news.getFile()+"----------------File-------+++++++++++++++++++++++++");
        System.out.println(news.getQuanzhong());
        System.out.println(news.getSyscode());
        System.out.println(news.getTypesss());
        System.out.println(news.getUpdate_time());
        UserInfo userinfo = (UserInfo)request.getSession().getAttribute("userinfo");
        System.out.println("----------------------------------------");
        news.setUserid(userinfo.getUserid());
        news.setTypesss("1");
        int i = service.addNews(news);
        return i;
    }
    @ResponseBody
    @RequestMapping("/UpdateSelect")
    public Object UpdateSelect(Integer id){
        System.out.println(id+"-++-+-+-+-+-+-+++-+-+-+");
        News selectdan = service.selectdan(id);
        return selectdan;
    }
}
