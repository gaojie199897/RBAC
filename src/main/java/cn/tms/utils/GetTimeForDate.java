package cn.tms.utils;

import cn.tms.entity.MyTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/26.
 */
public class GetTimeForDate {




    public static Integer  getDay(String day){
        return Integer.parseInt(day);
    }


    public static List<MyTime> getDate(){
        List<MyTime> aweekDate  = new ArrayList<MyTime>();
        SimpleDateFormat dfday = new SimpleDateFormat("dd");
        String format = dfday.format(new Date());
        Integer day = GetTimeForDate.getDay(format)-1;    //日

        SimpleDateFormat dfmonth = new SimpleDateFormat("MM");
        String format2 = dfmonth.format(new Date());
        Integer month = GetTimeForDate.getDay(format2);   //月份

        SimpleDateFormat dfyear = new SimpleDateFormat("yyyy");
        String format1 = dfyear.format(new Date());    //年

        MyTime time = null;
        for(int i =0;i<7;i++){
            time = new MyTime();
            time.setId(i);
            day = ++day;
            if(day>30){
                month=++month;
                day=1;
            }
            time.setTime(format1+"-"+month+"-"+day);
            aweekDate.add(time);
        }
        return aweekDate;
    }


    public static void test(String event,String str){
        Integer c =0;
        if(event.equals("ABC")){
            Integer num = Integer.parseInt(str.split("#")[0]+Integer.parseInt(str.split("#")[0]));
            System.out.println(num);
        }
    }

}
