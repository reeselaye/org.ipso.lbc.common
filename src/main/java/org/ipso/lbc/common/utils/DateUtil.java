/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 李倍存 创建于 2015/2/19 20:20。电邮 1174751315@qq.com。
 */
public class DateUtil {
    public static final long MILLISECONDS_PER_SECOND = 1000;
    public static final long MILLISECONDS_PER_MINUTE = 60 * MILLISECONDS_PER_SECOND;
    public static final long MILLISECONDS_PER_HOUR = 60 * MILLISECONDS_PER_MINUTE;
    public static final long MILLISECONDS_PER_QUARTER_DAY = 6 * MILLISECONDS_PER_HOUR;
    public static final long MILLISECONDS_PER_HALF_DAY = 2*MILLISECONDS_PER_QUARTER_DAY;
    public static final long MILLISECONDS_PER_DAY = 2 * MILLISECONDS_PER_HALF_DAY;
    public static Date getDateBefore(Date date, Integer before) {
        if (before == 0){
            return new Date(date.getTime());
        }
        Date d = new Date(date.getTime());
        for (int i = 0; i < before; i++) {
            d.setTime(d.getTime() - 86400000);
        }
        return d;
    }

    public static Date getDateAfter(Date date, Integer after) {
        if (after == 0){
            return new Date(date.getTime());
        }
        Date d = new Date(date.getTime());
        for (int i = 0; i < after; i++) {
            d.setTime(d.getTime() + 86400000);
        }
        return d;
    }

    public static String getDateStringAfter(String ds,Integer after){
        Date date = Date2StringAdapter.toDate(ds);
        return Date2StringAdapter.toString(getDateAfter(date,after));
    }
    public static String getDateStringBefore(String ds,Integer before){
        Date date = Date2StringAdapter.toDate(ds);
        return Date2StringAdapter.toString(getDateBefore(date,before));
    }
    public static Integer getISOWeekday(Date date) {
        Integer n=initCalendar(date).get(Calendar.DAY_OF_WEEK);
        return n==1?7:n-1;
    }
    public static Integer getISOWeekday(String ds) {
        return getISOWeekday(Date2StringAdapter.toDate(ds));
    }
    public static Integer getMonth(Date date){
        return initCalendar(date).get(Calendar.MONTH)+1;
    }
    public static Integer getYear(Date date){
        return initCalendar(date).get(Calendar.YEAR);
    }
    public static Integer getDay(Date date){
        return initCalendar(date).get(Calendar.DAY_OF_MONTH);
    }
    public static String getToday(){
        return Date2StringAdapter.toString(new Date(new java.util.Date().getTime()));
    }
    public static String getNow(){
        return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new java.util.Date());
    }
    private static Calendar initCalendar(Date date){
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
