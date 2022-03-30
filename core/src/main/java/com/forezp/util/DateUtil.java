package com.forezp.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 16:45
 * Version 1.0
 */
public class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 定义常见的时间格式
     */
    public static String[] dateFormat = {
            "yyyy-MM-dd", 				//0
            "yyyy-MM-dd HH:mm:ss",		//1
            "yyyy/MM/dd HH:mm:ss", 		//2
            "yyyy年MM月dd日HH时mm分ss秒", 	//3
            "yyyy/MM/dd", 				//4
            "yy-MM-dd", 				//5
            "yy/MM/dd", 				//6
            "yyyy年MM月dd日", 				//7
            "HH:mm:ss", 				//8
            "yyyyMMddHHmmss", 			//9
            "yyyyMM", 					//10
            "yyyyMMdd", 				//11
            "yyyy.MM.dd", 				//12
            "yy.MM.dd", 				//13
            "yyyy-MM-dd'T'HH:mm:ssZ" 	//14
    };

    /**
     * 周几
     */
    private static String[] weekDay = {"周日","周一","周二","周三","周四","周五","周六"};

    /**
     * 得到Timestamp格式的当天时间（去掉时分秒）
     * @return
     */
    public static Timestamp convCurrentTimeToSqlTimestamp(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return new Timestamp(cal.getTimeInMillis());
    }
    /**
     * 得到Timestamp格式的当天最大时间
     * @return
     */
    public static Timestamp convDayTimeToSqlTimestamp(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return new Timestamp(cal.getTimeInMillis());
    }
    /**
     * 获得当前格林尼治时间
     * @return
     */
    public static Timestamp getBeijingTimeStamp(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return Timestamp.valueOf(DateUtil.toDateStr(cal, 1));
    }
    /**
     * 将yyyy-MM-dd'T'HH:mm:ssZ格式转换为北京时间的Timestamp
     * @param time
     * @return
     */
	public static Timestamp parseToBeijingTimeStamp(String time){
		 Timestamp res=null;
		 try{
			 Date date= DateUtil.parseDate(time, 14);
			 Calendar cal = Calendar.getInstance();
		     cal.setTime(date);
		     cal.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		     res= Timestamp.valueOf(DateUtil.toDateStr(cal, 1));
		 }catch(Exception e){
			 res = getBeijingTimeStamp();
		 }
		 return res;
	}

    /**
     * 将时间戳转换为北京时间的Timestamp
     */
    public static Timestamp parseLongToBeijingTimeStamp(long time){
        Timestamp res=null;
        try{
            Date date= new Date(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            res= Timestamp.valueOf(DateUtil.toDateStr(cal, 1));
        }catch(Exception e){
            logger.error("e:{}",e);
            res = getBeijingTimeStamp();
        }
        return res;
    }
    /**
     * 得到Timestamp格式的n（days）天后的时间（去掉时分秒）
     * @param days 表示当前时间多少天后
     * @return
     */
    public static Timestamp getTimestampAfterDays(Integer days){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return new Timestamp(cal.getTimeInMillis());
    }
    public static String timeStampWithTimeZone(Timestamp ctime){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String res = "";
        res = sdf.format(ctime);
        return res;
    }

    public static String timeStampWithTimeZone(Timestamp ctime,TimeZone timeZone){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String res = "";
        sdf.setTimeZone(timeZone);
        res = sdf.format(ctime);
        return res;
    }


    /**
     * 获取中文周几
     * @param day  1-7  SUN-SAT
     * @return
     */
    public static String getWeekDay(int day) {
        return weekDay[day-1];
    }

    /**
     * 获取当前时间。
     *
     * @return 返回当前时间
     */
    public static Calendar getCurrentDate() {
        return Calendar.getInstance();
    }


    /**
     * 将日期格式从 java.util.Calendar 转到 java.sql.Timestamp 格式
     *
     * @param date
     *            java.util.Calendar 格式表示的日期
     * @return java.sql.Timestamp 格式表示的日期
     */
    public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTimeInMillis());
    }

    /**
     * 将日期格式从 java.util.Timestamp 转到 java.util.Calendar 格式
     *
     * @param date
     *            java.sql.Timestamp 格式表示的日期
     * @return java.util.Calendar 格式表示的日期
     */
    public static Calendar convSqlTimestampToUtilCalendar(Timestamp date) {
        return getCalendar(date);
    }

    /**
     * 解析一个字符串，形成一个Calendar对象，适应各种不同的日期表示法
     *
     * @param dateStr
     *            期望解析的字符串，注意，不能传null进去，否则出错
     * @return 返回解析后的Calendar对象 <br>
     * <br>
     *         可输入的日期字串格式如下： <br>
     *         "yyyy-MM-dd HH:mm:ss", <br>
     *         "yyyy/MM/dd HH:mm:ss", <br>
     *         "yyyy年MM月dd日HH时mm分ss秒", <br>
     *         "yyyy-MM-dd", <br>
     *         "yyyy/MM/dd", <br>
     *         "yy-MM-dd", <br>
     *         "yy/MM/dd", <br>
     *         "yyyy年MM月dd日", <br>
     *         "HH:mm:ss", <br>
     *         "yyyyMMddHHmmss", <br>
     *         "yyyyMMdd", <br>
     *         "yyyy.MM.dd", <br>
     *         "yy.MM.dd"
     */
    public static Calendar parseDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr) || dateStr.trim().length() == 0) {
            return null;
        }

        Date result = parseDate(dateStr, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);

        return cal;
    }

    /**
     * 将一个日期转成日期时间格式，格式这样 2002-08-05 21:25:21
     *
     * 请参考toDateStrWithTime；
     * @param date
     *            期望格式化的日期对象
     * @return 返回格式化后的字符串 <br>
     * @deprecated
     *
     * <br>
     *         例： <br>
     *         调用： <br>
     *         Calendar date = new GregorianCalendar(); <br>
     *         String ret = DateUtils.toDateTimeStr(date); <br>
     *         返回： <br>
     *         ret = "2002-12-04 09:13:16";
     */
    public static String toDateTimeStr(Calendar date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
    }
    /**
     * 将一个日期转成日期时间格式，格式这样 2002-08-05 21:25:21
     *
     * @param date
     *            期望格式化的日期对象
     * @return 返回格式化后的字符串 <br>
     * <br>
     *         例： <br>
     *         调用： <br>
     *         Calendar date = new GregorianCalendar(); <br>
     *         String ret = DateUtils.toDateTimeStr(date); <br>
     *         返回： <br>
     *         ret = "2002-12-04 09:13:16";
     */
    public static String toDateStrWithTime(Calendar date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[1]).format(date.getTime());
    }

    /**
     * 将一个日期转成日期时间格式，格式这样 2002-08-05 21:25:21
     *
     * @param date
     *            期望格式化的日期对象
     * @return 返回格式化后的字符串 <br>
     * <br>
     *         例： <br>
     *         调用： <br>
     *         Calendar date = new GregorianCalendar(); <br>
     *         String ret = DateUtils.toDateTimeStr(date); <br>
     *         返回： <br>
     *         ret = "2002-12-04 09:13:16";
     */
    public static String toDateTimeStr(int format, Calendar date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[format]).format(date.getTime());
    }



    /**
     * 将一个日期转成日期格式，格式这样 2002-08-05
     *
     * @param date
     *            期望格式化的日期对象
     * @return 返回格式化后的字符串 <br>
     * <br>
     *         例： <br>
     *         调用： <br>
     *         Calendar date = new GregorianCalendar(); <br>
     *         String ret = DateUtils.toDateStr(calendar); <br>
     *         返回： <br>
     *         ret = "2002-12-04";
     */
    public static String toDateStr(Calendar date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
    }
    public static String toDateStr(Calendar date,int index) {
        if (date == null) {
            return "";
        }
        if(index>=dateFormat.length) {
            index = 1;
        }
        SimpleDateFormat  format = new SimpleDateFormat(dateFormat[index]);
        format.setTimeZone(date.getTimeZone());
        return format.format(date.getTime());
    }

    public static String toDateStrByFormatIndex(Calendar date, int formatIndex) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[formatIndex]).format(date
                .getTime());
    }

    public static int calendarMinus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }

        d1.set(Calendar.HOUR_OF_DAY, 0);
        d1.set(Calendar.MINUTE, 0);
        d1.set(Calendar.SECOND, 0);

        d2.set(Calendar.HOUR_OF_DAY, 0);
        d2.set(Calendar.MINUTE, 0);
        d2.set(Calendar.SECOND, 0);

        long t1 = d1.getTimeInMillis();
        long t2 = d2.getTimeInMillis();
        long daylong = 3600L * 24L * 1000L;
        t1 = t1 - t1 % (daylong);
        t2 = t2 - t2 % (daylong);

        long t = t1 - t2;
        int value = (int) (t / (daylong));
        return value;
    }

    /**
     * @param d1
     * @param d2
     * @return
     */
    public static long calendarminus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        return (d1.getTimeInMillis() - d2.getTimeInMillis()) / (3600 * 24000);
    }

    /**
     * 内部方法，根据某个索引中的日期格式解析日期
     *
     * @param dateStr
     *            期望解析的字符串
     * @param index
     *            日期格式的索引
     * @return 返回解析结果
     */
    public static Date parseDate(String dateStr, int index) {
        if (dateStr == null || "".equals(dateStr) || dateStr.trim().length() == 0) {
            return null;
        }

        DateFormat df = null;
        try {
            df = new SimpleDateFormat(dateFormat[index]);

            return df.parse(dateStr);
        } catch (ParseException pe) {
            return parseDate(dateStr, index + 1);
        } catch (ArrayIndexOutOfBoundsException aioe) {
            logger.error("e:{}",aioe);
            return null;
        }
    }

    /**
     * 字符转日期,字符串格式："yyyy-MM-dd"，例如2006-01-01
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr) || dateStr.trim().length() == 0) {
            return null;
        }
        return parseDate(dateStr, 3);
    }

    /**
     * DATE to String，支持多种格式
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, int index) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[index]).format(date);
    }

    /**
     * DATE to String，转换结果格式为："yyyy-MM-dd"，例如2006-01-01
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(dateFormat[0]).format(date);
    }

    /**
     * 将日期格式从 java.util.Date 转到 java.sql.Timestamp 格式 convUtilDateToSqlTimestamp <br>
     *
     * @param date
     *            java.util.Date 格式表示的日期
     * @return Timestamp java.sql.Timestamp 格式表示的日期
     */
    public static Timestamp convUtilDateToSqlTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static Calendar convUtilDateToUtilCalendar(Date date) {
        return getCalendar(date);
    }

    private static Calendar getCalendar(Date date) {
        if (date == null) {
            return null;
        } else {
            java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            return gc;
        }
    }


    /**
     * 内部方法，根据默认的日期格式“yyyy-MM-dd”解析日期
     *
     * @param dateStr
     *            期望解析的字符串
     * @return 返回解析结果
     */
    public static Timestamp parseTimestamp(String dateStr) {
        DateFormat df = null;
        try {
            df = new SimpleDateFormat(dateFormat[0]);
            return new Timestamp(df.parse(dateStr).getTime());
        } catch (ParseException pe) {
            return null;
        } catch (ArrayIndexOutOfBoundsException aioe) {
            logger.error("e:{}",aioe);
            return null;
        }
    }

    /**
     * 内部方法，根据默认的日期格式“yyyy-MM-dd”解析日期
     *
     * @param dateStr
     *            期望解析的字符串
     * @return 返回解析结果"yyyy-MM-dd 23:59:59"
     */
    public static Timestamp parseTimestampEnd(String dateStr) {
        DateFormat df = null;
        try {
            df = new SimpleDateFormat(dateFormat[0]);
            return new Timestamp(df.parse(dateStr).getTime()+24*3600*1000-1000);
        } catch (ParseException pe) {
            return null;
        } catch (ArrayIndexOutOfBoundsException aioe) {
            logger.error("e:{}",aioe);
            return null;
        }
    }
    public static int calcMonthDays(Calendar date) {
        if (date == null) {
            return 0;
        }
        Calendar t1 = (Calendar) date.clone();
        Calendar t2 = (Calendar) date.clone();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        t1.set(year, month, 1);
        t2.set(year, month + 1, 1);
        t2.add(Calendar.DAY_OF_YEAR, -1);
        return calendarMinus(t2, t1) + 1;
    }

    /**
     * 对外接口
     *
     * @param date
     *            传入日期类型
     * @return 返回大写日期字符串
     */
    public static String toUpperCase(Date date) {
        if (date == null) {
            return "";
        }
        return dataToUpper(date);
    }

    /**
     * 日期转化为大小写
     * @param date
     * @return
     */
    private static String dataToUpper(Date date) {
        if (date == null) {
            return "";
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return numToUpper(year) + "年" + monthToUppder(month) + "月"
                + dayToUppder(day) + "日";
    }

    /**
     * 将数字转化为大写
     * @param num
     * @return
     */
    private static String numToUpper(int num) {
        // String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String[] u = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }

    /**
     * 对外接口
     *
     * @param date
     *            传入日期类型
     * @return 返回大写日期字符串如：二〇一二年三月十三日
     */
    public static String toUpperCases(Date date) {
        if (date == null) {
            return "";
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return numToUpper1(year) + "年" + monthToUppder(month) + "月"
                + dayToUppder(day) + "日";
    }

    /**
     * 将数字转化为大写
     * @param num
     * @return
     */
    private static String numToUpper1(int num) {
        // String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String[] u = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }

    /**
     * 月转化为大写
     * @param month
     * @return
     */
    private static String monthToUppder(int month) {
        if (month < 10) {
            return numToUpper(month);
        } else if (month == 10) {
            return "十";
        } else {
            return "十" + numToUpper(month - 10);
        }
    }

    /**
     * 日转化为大写
     * @param day
     * @return
     */
    private static String dayToUppder(int day) {
        if (day < 20) {
            return monthToUppder(day);
        } else {
            char[] str = String.valueOf(day).toCharArray();
            if (str[1] == '0') {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十";
            } else {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十"
                        + numToUpper(Integer.parseInt(str[1] + ""));
            }
        }
    }

    public static int calcWorkday(Calendar c1, Calendar c2){
        int count = 0;
        if (c1==null) {
            return count;
        }
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(c1.getTime());

        //如果结束时间在开始时间之后
        while (c2.after(tempCal)
                && !((tempCal.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) && (tempCal
                .get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)))) {
            int day = tempCal.get(Calendar.DAY_OF_WEEK);
            if (!(day == 1 || day == 7)) {
                count++;
            }
            tempCal.add(Calendar.DATE, 1);
        }
        return count;
    }
    /**
     * 获得大写的年份，便于年度、年
     *<p>
     *方法描述|
     *
     *</p>
     *<p>
     *2012-3-20
     *</p>
     *<p>
     *Author:石佩|TEL:18971612939|QQ:408370389
     *</p>
     * @param date
     * @return
     */

    public static String getUpperYear(Date date){
        if (date == null) {
            return "";
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        return numToUpper1(year);
    }
    /**
     * 获得大写的月份
     *<p>
     *方法描述|
     *
     *</p>
     *<p>
     *2012-3-20
     *</p>
     *<p>
     *Author:石佩|TEL:18971612939|QQ:408370389
     *</p>
     * @param date
     * @return
     */
    public static String getUpperMonth(Date date){
        if (date == null) {
            return "";
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int month = ca.get(Calendar.MONTH) + 1;
        return monthToUppder(month);
    }
    /**
     * 获得大写的日期天
     *<p>
     *方法描述|
     *
     *</p>
     *<p>
     *2012-3-20
     *</p>
     *<p>
     *Author:石佩|TEL:18971612939|QQ:408370389
     *</p>
     * @param date
     * @return
     */
    public static String getUpperDay(Date date){
        if (date == null) {
            return "";
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return dayToUppder(day);
    }

    /**
     * 得到Timestamp格式的n（days）天后的时间（去掉时分秒）
     * @param days 表示当前时间多少天后
     * @return
     */
    public static Calendar getCalAfterDays(Date date ,Integer days){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }

    /**
     * 得到Timestamp格式的n（days）天后的时间（去掉时分秒）
     * @param days 表示date时间多少天后
     * @return
     */
    public static Calendar getsetCalAfterDays(Date date ,Integer days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }


    public static long minus(Calendar d1, Calendar d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }

        d1.set(Calendar.HOUR_OF_DAY, 0);
        d1.set(Calendar.MINUTE, 0);
        d1.set(Calendar.SECOND, 1);
        d2.set(Calendar.HOUR_OF_DAY, 0);
        d2.set(Calendar.MINUTE, 0);
        d2.set(Calendar.SECOND, 0);

        return (d1.getTimeInMillis() - d2.getTimeInMillis()) / (3600 * 24000);
    }

    /**
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            sb.append(hour+"小时");
        }
        if(minute > 0) {
            sb.append(minute+"分种");
        }
        // 一分钟之内
        if(StringUtils.isBlank(sb.toString()) && (second > 0 || milliSecond > 0)) {
            sb.append("1分种");
        }
        return sb.toString();
    }

    /**
     * 时间间隔
     */
    public static String getDurationByDateStr(String dateStr1, String dateStr2, String simpleDateFormat) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(simpleDateFormat);
        Date date1 = df.parse(dateStr1);
        Date date2 = df.parse(dateStr2);
        long l = date1.getTime()-date2.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        if (day != 0){
            return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
        }else if(hour != 0){
            return ""+hour+"小时"+min+"分"+s+"秒";
        }else if(min != 0){
            return ""+min+"分"+s+"秒";
        }else {
            return ""+s+"秒";
        }
    }

    /**
     * 时间间隔
     */
    public static String getDurationByDate(Date date1, Date date2, String simpleDateFormat) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(simpleDateFormat);
        long l = date1.getTime()-date2.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        if (day != 0){
            return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
        }else if(hour != 0){
            return ""+hour+"小时"+min+"分"+s+"秒";
        }else if(min != 0){
            return ""+min+"分"+s+"秒";
        }else {
            return ""+s+"秒";
        }
    }
}
