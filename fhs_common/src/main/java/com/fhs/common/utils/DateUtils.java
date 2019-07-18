/*
 * 文 件 名:  DateUtils.java
 * 版    权:
 * 描    述:  <描述>
 * 修 改 人:  matao
 * 修改时间: 2015年9月9日 下午2:59:10
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.fhs.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 日期工具类
 *
 * @author matao
 * @version [版本号, 2015年9月9日 下午2:59:10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DateUtils {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIME_PATTERN_NO_MSE = "yyyy-MM-dd HH:mm";

    public static final String DATETIME_PATTERN_DATE = "yyyy-MM-dd";

    public static final String DATETIME_PATTERN_MONTH = "yyyy年MM月";

    public static final String DATETIME_PATTERN_DATE_NO_ = "yyyyMMdd";

    public static final String DATE_FULL_STR_SSS = "yyyy-MM-dd HH:mm:ss SSS";

    public static final String DATE_FULL_SS_NO_SP = "yyyyMMddHHmmss";

    public static final String DATETIME_NO_HOUR = "yyyyMMdd";

    public static final String DATETIME_YEAR = "yyyy";

    public static final String DATETIME_MONTH = "MM";

    /**
     * @return
     * @Author yaoyang
     * @Description 将时间戳(单位 ： 秒)格式化为指定格式
     * @Date 16:42 2018/12/19
     * @Param
     **/
    public static String timestampFormat(Long timestamp, String format) {
        // 初始化日期类
        Date date = new Date();
        // 设置时间
        date.setTime(timestamp);
        // 设置日期格式
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    /**
     * @param dateStr 当前时间
     * @param days    时间区间 例如 7 14 30天  必传项
     * @return
     * @desc 根据当前时间和天数  获取周区间
     */
    public static LinkedList<String> getWxDateArea(String dateStr, Integer days) {
        if (CheckUtils.isNullOrEmpty(days) || days == 0) {
            return new LinkedList<String>();
        }
        if (CheckUtils.isNullOrEmpty(dateStr)) {
            dateStr = formartDate(getSpecifiedNDayBefore(new Date(), 1), DATETIME_PATTERN_DATE);
            System.out.println("上传的时间为空，获取的前一天时间为:" + dateStr);
        }
        Date yesterDayDate = parseStr(dateStr, DATETIME_PATTERN_DATE);//将前一天时间转为时间格式  yyyy-MM-dd
        LinkedList<String> resultLinkList = new LinkedList<>();
        String mondays = "";
        Date dateStart = getSpecifiedNDayBefore(yesterDayDate, days - 1);
        System.out.println("获取的最前时间为:" + formartDate(dateStart, DATETIME_PATTERN_DATE));
        for (int i = 0; i < days; i++) {
            if (i == 0) {
                mondays = getCurrentWeekFirstDate(yesterDayDate.getTime());
                resultLinkList.addFirst(mondays);
                mondays = "";
            } else {
                mondays = getCurrentWeekFirstDate(getSpecifiedNDayBefore(yesterDayDate, i).getTime());
                if (!resultLinkList.getFirst().equals(mondays)) {
                    if (parseStr(mondays, DATETIME_PATTERN_DATE).getTime() < dateStart.getTime()) {
                        if (!resultLinkList.getFirst().equals(formartDate(dateStart, DATETIME_PATTERN_DATE))) {
                            resultLinkList.addFirst(formartDate(dateStart, DATETIME_PATTERN_DATE));
                        }
                    } else {
                        resultLinkList.addFirst(mondays);
                    }
                }
                mondays = "";
            }
        }
        if (resultLinkList.size() > 0) {
            if (!resultLinkList.getFirst().equals(parseStr(resultLinkList.getFirst(), DATETIME_PATTERN_DATE).getTime())) {
                resultLinkList.removeFirst();
            }
        }
        return resultLinkList;
    }

    /**
     * 获取多少天之前的日期
     * @param date  日期
     * @param count 多少天
     * @return 多少天之前的日期
     */
    public static Date getSpecifiedNDayBefore(Date date, Integer count) {// 可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - count);
        return c.getTime();
    }



    /**
     * 获取某天当天凌晨0点0分0秒0毫秒 的时间戳
     * @param date 日期
     * @return 当天凌晨0点0分0秒0毫秒 的时间戳
     */
    public static long toBeforeDawnMillisecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    /**
     * 获取2个日期相减后的分钟数
     *
     * @param bigDate 大的日期
     * @param minDate 小的日期
     * @return 相差的分钟数
     */
    public static Integer getSubMinute(String bigDate, String minDate) {
        return (int) ((DateUtils.parseStr(bigDate, DateUtils.DATETIME_PATTERN).getTime() -
                DateUtils.parseStr(minDate, DateUtils.DATETIME_PATTERN).getTime()) / 1000 / 60);
    }

    /**
     * 字符时间转date时间
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parseStr(String dateString, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            Date parse = simpleDateFormat.parse(dateString);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 日期转为字符串
     *
     * @param value
     * @return
     */
    public static String doConvertToString(Object value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_PATTERN);
        String result = null;
        if (value instanceof Date) {
            result = simpleDateFormat.format(value);
        }
        return result;
    }

    /**
     * 格式化一个指定的date
     *
     * @return 格式化后的字符串
     */
    public static String formartDate(Date time, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(time);
    }

    /**
     * 比较是否滞后于系统时间
     *
     * @param value
     * @return
     */
    public static boolean afterToSystemDate(Object value) {
        boolean result = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_PATTERN_NO_MSE);
        Date date = null;
        try {
            date = simpleDateFormat.parse((String) value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result = date.after(new Date());
        return result;
    }

    public static boolean afterToSysDateInterval(String value, int minutes) {
        Date date = null;
        date = new Date(Long.valueOf(value));
        Date sysDate = new Date();
        long between = (sysDate.getTime() - date.getTime()) / 1000;// 除以1000是为了转换成秒
        return between > minutes;
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return
     */
    public static String getCurrentTimeMillis() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 获取当前日期的格式化后的字符串
     *
     * @param formart 格式化字符串
     * @return 日期字符串
     *
     * <pre>
     * <li>Author: jackwong</li>
     * <li>Date: 2017年10月12日</li>
     *         </pre>
     */
    public static String getCurrentDateStr(String formart) {
        return formartDate(new Date(), formart);
    }

    /**
     * 当天的时间戳
     *
     * @param formart
     * @return
     */
    public static Long getCurrentDateLong(String formart) {
        return parseStr(getCurrentDateStr(formart), formart).getTime();
    }


    /**
     * 获得指定日期的前一天
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayBefore(Date date) {// 可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return c.getTime();
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2)
            throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(Date str1, Date str2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = str1.getTime();
        long time2 = str2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        // day = diff / (24 * 60 * 60 * 1000);
        // hour = (diff / (60 * 60 * 1000) - day * 24);
        // min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return sec + "";
    }

    /**
     * 格式化时间，如果时间是今天，则显示HH:mm；如果是昨天，显示昨天；如果是之前，则显示 YYYY-MM-DD <一句话功能简述> <功能详细描述>
     *
     * @param <T>
     * @param listBean
     * @param fieldName 更改的日期字段
     * @return
     */
    public static <T> List<T> formatDateByFieldName(List<T> listBean, String fieldName) {
        Calendar cal = Calendar.getInstance();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        if (listBean != null && listBean.size() > 0) {
            String tempFieldNameValue = null;
            for (T bean : listBean) {
                tempFieldNameValue = ConverterUtils.toString(ReflectUtils.getValue(bean, fieldName));
                if (StringUtil.isEmpty(tempFieldNameValue)) {
                    continue;
                }
                if (tempFieldNameValue.substring(0, 10).equals(today)) {
                    ReflectUtils.setValue(bean, fieldName, tempFieldNameValue.substring(11, 16));
                } else if (tempFieldNameValue.substring(0, 10).equals(yesterday)) {
                    ReflectUtils.setValue(bean, fieldName, "今天");
                } else {
                    ReflectUtils.setValue(bean, fieldName, tempFieldNameValue.substring(0, 10));
                }
            }
        }
        return listBean;
    }

    /**
     * 获取指定日某天所在周的第一天 <一句话功能简述> <功能详细描述>
     *
     * @param longTime 指定某天.getTime();
     * @return
     */
    public static String getCurrentWeekFirstDate(Long longTime) {
        Date date = null;
        if (longTime != null) {
            date = new Date(longTime);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DATE, 0);
        Date result = new Date(calendar.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(result);
    }

    /**
     * 获取指定日某天所在周的最后一天 <一句话功能简述> <功能详细描述>
     *
     * @param longTime 指定某天.getTime();
     * @return
     */
    public static String getCurrentWeekLastDate(Long longTime) {
        Date date = null;
        if (longTime != null) {
            date = new Date(longTime);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DATE, 6);
        Date result = new Date(calendar.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(result);
    }

    /**
     * 获取指定日某天所在月的第一天 <一句话功能简述> <功能详细描述>
     *
     * @param longTime
     * @return
     */
    public static String getCurrentMonthFirstDate(Long longTime) {
        Date date = null;
        if (longTime != null) {
            date = new Date(longTime);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date result = new Date(calendar.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(result);
    }

    /**
     * 获取指定日某天所在月的最后一天 <一句话功能简述> <功能详细描述>
     *
     * @param longTime
     * @return
     */
    public static String getCurrentMonthLastDate(Long longTime) {
        Date date = null;
        if (longTime != null) {
            date = new Date(longTime);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date result = new Date(calendar.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(result);
    }

    /**
     * 获取指定日某天所在年的第一天 <一句话功能简述> <功能详细描述>
     *
     * @param longTime
     * @return
     */
    public static String getCurrentYearFirstDate(Long longTime) {
        Date date = null;
        if (longTime != null) {
            date = new Date(longTime);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year + "-01-01";
    }

    /**
     * 获取指定日某天所在年的最后一天 <一句话功能简述> <功能详细描述>
     *
     * @param longTime
     * @return
     */
    public static String getCurrentYearLastDate(Long longTime) {
        Date date = null;
        if (longTime != null) {
            date = new Date(longTime);
        } else {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year + "-12-31";
    }

    /**
     * 通过放入的日期列表获取列表中的最大日期和最小日期
     *
     * @param putInDateList "2016-9-2", "2016-9-1", "2016-8-23", "2016-10-23"
     * @return map.put(" startDate ", " 2016 - 8 - 23 "); map.put("endDate", "2016-10-23");
     */
    public static Map<String, String> getMaxDateAndMinDate(String putInDateList) {
        Map<String, String> dateMap = new HashMap<String, String>();
        if (StringUtil.isEmpty(putInDateList)) {
            return null;
        } else {
            String[] allDates = putInDateList.split(",");
            String startDate = allDates[0];
            String endDate = allDates[0];
            for (int i = 1; i < allDates.length; i++) {
                if (compareDate(startDate, allDates[i])) {
                    startDate = allDates[i];
                }
                if (!compareDate(endDate, allDates[i])) {
                    endDate = allDates[i];
                }
            }
            dateMap.put("startDate", startDate);
            dateMap.put("endDate", endDate);
            return dateMap;
        }
    }

    /**
     * 比较两个日期字符串的大小
     *
     * @param source
     * @param target
     * @return source大于目录，返回true,否则返回false
     */
    public static boolean compareDate(String source, String target) {
        return compareDate( source,  target, DATETIME_PATTERN_DATE);
    }

    /**
     * 比较两个yyyy-MM-dd HH:mm:SS 大小
     *
     * @param source source
     * @param target target
     * @return source大于目录，返回true,否则返回false
     */
    public static boolean compareDate(String source, String target,String formart) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formart);
            Date sourceDate = simpleDateFormat.parse(source);
            Date targetDate = simpleDateFormat.parse(target);
            if (sourceDate.getTime() > targetDate.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查传入的时间+miuntes分钟后 与当前的时间 相减， 是否在time分钟之内
     *
     * @param requestDate 传入的时间 yyyy-MM-dd HH:mm:ss
     * @param minutes     时间长度
     * @param time        超过时间
     * @return
     */
    public static boolean checkRequestDateIsOvertime(String requestDate, String minutes, String time) {
        SimpleDateFormat sf = new SimpleDateFormat(DATETIME_PATTERN);
        try {
            long s = sf.parse(requestDate).getTime();
            s += Integer.parseInt(minutes) * 60 * 1000;
            s += Integer.parseInt(time) * 60 * 1000;
            if (s <= new Date().getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取时间戳
     *
     * @param time
     * @return timeStemp
     * @throws ParseException
     */
    public static Integer formartDateStemp(String time) {
        Long timeStemp = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(time);
            timeStemp = date.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ConverterUtils.toInteger(timeStemp / 1000);
    }

    /**
     * 当前时间+分钟
     *
     * @param min 要添加的分钟
     */
    public static long addOrMinus(int min)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MINUTE, minute + min);
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 时间戳转日期  时间戳精确到毫秒
     *
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

    /**
     * 时间戳转日期字符串  时间戳精确到秒
     *
     * @param se_time
     * @return 日期字符串
     */
    public static String stamp2StrTime(String se_time) {
        String second_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        long second_time = Long.valueOf(se_time);
        second_StrTime = sdf.format(new Date(second_time * 1000));
        return second_StrTime;
    }

    /**
     * 根据localDateTime 获取对应的字符串时间
     * 获取失败后默认返回当前时间的字符串时间
     *
     * @return
     */
    public static String getDataStrByLocalDateTime(LocalDateTime localDateTime) {
        try {
            return new SimpleDateFormat(DateUtils.DATETIME_PATTERN_DATE).format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleDateFormat(DateUtils.DATETIME_PATTERN_DATE).format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        }
    }

    /**
     * 获取前几日的时间数据
     *
     * @param howDays
     * @return
     */
    public static List<String> getBeforeDateStrByHowDays(Integer howDays) {
        List<String> beforeDateList = new ArrayList<String>();
        int i = 1;//从当日开始
        while (i <= howDays) {
            beforeDateList.add(DateUtils.getDataStrByLocalDateTime(LocalDateTime.now().plusDays(-i)));
            i++;
        }
        ListSort(beforeDateList);
        return beforeDateList;
    }

    /**
     * 时间倒叙排列
     *
     * @param list
     */
    private static void ListSort(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dt1 = format.parse(o1);
                    Date dt2 = format.parse(o2);
                    if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

}
