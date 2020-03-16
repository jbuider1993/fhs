/**
 * 文 件 名：CalendarUtil.java
 * 创 建 人：王磊
 * 日       期：2013年8月7日
 * 修 改 人：王磊
 * 日       期：2013年8月7日
 * 描       述：工具类处理
 * 版 本 号：
 */
package com.fhs.common.utils;

import com.fhs.common.constant.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <日期处理工具类>
 *
 * @author jackwong
 * @version [版本号, 2013年8月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CalendarUtil
{
    /**
     * <根据指定的时间显示格式，将一个Date显示成指定格式的时间字符串>
     *
     * @param date 表示时间的Date对象
     * @param format 表示时间的显示格式字符串：如"yyyyMMdd"或"yyyyMMdd HH:mm"
     * @return 指定格式的时间字符串
     */
    public static String format(Date date, String format)
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    /**
     * <将一个格式化字符串时间值转换成Date对象>
     *
     * @param strTime 表示时间的格式化字符串值
     * @param format 表示时间的显示格式
     * @return 指定时间的Date对象
     * @throws ParseException
     */
    public static Date parse(String strTime, String format)
        throws ParseException
    {
        if (null != format && format.equals("long"))
        {
            return new Date(Long.parseLong(strTime));
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(strTime);
    }



    /**
     * 将一个long time转换成Calendar对象
     *
     * @param time 表示时间的long值
     * @return 指定时间的Calendar对象
     */
    public static Calendar getCalendar(long time)
    {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        return cl;
    }

    /**
     * 将一个String转换成Calendar对象
     *
     * @param lTime 表示时间的long值的字符串
     * @return 指定时间的Calendar对象
     */
    public static Calendar getCalendar(String lTime)
    {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(Long.parseLong(lTime));
        return cl;
    }

    /**
     * 将一个指定了时间显示格式的String转换成Calendar对象.by.ln
     *
     * @param strTime 表示时间的格式字符串
     * @param format 表示时间的显示格式的字符串
     * @return 指定时间的Calendar对象
     * @throws ParseException
     */
    public static Calendar getCalendar(String strTime, String format)
        throws ParseException
    {
        Calendar cl = Calendar.getInstance();
        cl.setTime(parse(strTime, format));
        return cl;
    }

    /**
     *
     * 将一个Calendar对象转换成指定时间显示格式的字符串
     *
     * @param cal 输入字符串
     * @param format 时间显示格式： 如"yyyyMMdd"或"yyyyMMdd HH:mm"，则返回标准时间格式的字符串 "long"， 则返回long型值的字符串
     * @return 指定时间显示格式的字符串
     */
    public static String calendarToString(Calendar cal, String format)
    {
        return format.equals("long") ? String.valueOf(cal.getTime().getTime()) : format(cal.getTime(), format);
    }

    /**
     * 将一个表示时间long值的String转换成一定时间显示格式的String
     *
     * @param lTime 表示时间的long值的字符串
     * @param format 时间显示格式： 如"yyyyMMdd"或"yyyyMMdd HH:mm"， 则返回标准时间格式的字符串 "long"，则返回long型值的字符串
     * @return 一定时间显示格式的String
     */
    public static String longDateStrToFormatDateStr(String lTime, String format)
    {
        Calendar cal = getCalendar(lTime);
        return calendarToString(cal, format);
    }

    /**
     * 将一个表示时间Calendar值的按小时调整，形成新的时间
     *
     * @param Calendar 表示待调整的时间串
     * @param hour 调整的小时数，正数为向后调整，负数为向前调整
     * @return 调整后的新时间Calendar
     */
    public static Calendar getAdjustCalendar(Calendar cal, int hour)
    {
        return getAdjustCalendar(cal, 0, 0, 0, hour, 0, 0);
    }

    /**
     * 将一个表示时间Calendar值的按分钟，形成新的时间
     *
     * @param Calendar 表示待调整的时间串
     * @param field "YEAR"仅调整年 "MONTH"仅调整月"DAY"，仅调整天； "HOUR",仅调整小时； "MIN",仅调整分钟； "SECOND",仅调整秒。
     * @param mount 调整的大小，正数为向后调整，负数为向前调整
     * @return 调整后的新时间Calendar
     */
    public static Calendar getAdjustCalendar(Calendar cal, int field, int mount)
    {
        Calendar t = null;
        switch (field)
        {
            case Calendar.YEAR:
                t = getAdjustCalendar(cal, mount, 0, 0, 0, 0, 0);
                break;
            case Calendar.MONTH:
                t = getAdjustCalendar(cal, 0, mount, 0, 0, 0, 0);
                break;
            case Calendar.DATE:
                t = getAdjustCalendar(cal, 0, 0, mount, 0, 0, 0);
                break;
            case Calendar.HOUR:
                t = getAdjustCalendar(cal, 0, 0, 0, mount, 0, 0);
                break;
            case Calendar.MINUTE:
                t = getAdjustCalendar(cal, 0, 0, 0, 0, mount, 0);
                break;
            case Calendar.SECOND:
                t = getAdjustCalendar(cal, 0, 0, 0, 0, 0, mount);
                break;
        }

        return t;
    }

    /**
     * 将一个表示时间Calendar值的按年，月，日，小时、分钟、秒调整，形成新的时间
     *
     * @param Calendar 表示待调整的时间串
     * @param hour 调整的小时数，正数为向后调整，负数为向前调整
     * @param min 调整的分钟数，正数为向后调整，负数为向前调整
     * @param sec 调整的秒数，正数为向后调整，负数为向前调整
     * @return 调整后的新时间Calendar
     */
    public static Calendar getAdjustCalendar(Calendar cal, int year, int month, int day, int hour, int min, int sec)
    {
        // GregorianCalendar.HOUR
        Calendar adjust = (Calendar)cal.clone();
        adjust.add(Calendar.YEAR, year);
        adjust.add(Calendar.MONTH, month);
        adjust.add(Calendar.DATE, day);
        adjust.add(Calendar.HOUR, hour);
        adjust.add(Calendar.MINUTE, min);
        adjust.add(Calendar.SECOND, sec);

        return adjust;
    }

    /**
     * 将当前时间以时间显示格式的字符串返回
     *
     * @param format 时间显示格式： 如"yyyyMMdd"或"yyyyMMdd HH:mm"， 则返回标准时间格式的字符串 "long"，则返回long型值的字符串
     * @return 指定的时间显示格式的当前时间字符串
     */
    public static String getCurrentTimeStr(String format)
    {
        Calendar cl = Calendar.getInstance();
        return calendarToString(cl, format);
    }

    /**
     * 获得固定间隔的日期，如前60小时dateAdd(-60)
     *
     * @param sourceDate 某一日期
     * @param amount 距某一日期的间隔日期长度，向前为负，向后为正
     * @return java.lang.String 固定间隔的日期 fu_yling
     */
    public static String timeAdd(String sourceDate, int amount, String format)
    {
        Calendar cal = new GregorianCalendar();
        java.sql.Date trialTime = java.sql.Date.valueOf(sourceDate);
        cal.setTime(trialTime);
        cal.add(GregorianCalendar.HOUR_OF_DAY, amount);
        return new SimpleDateFormat(format).format(cal.getTime());
    }

    /**
     * <将一个yyyy-MM-dd的字符串转换为yyyyMMdd>
     *
     * @param strDate yyyy-MM-dd的字符串
     * @return yyyyMMdd的字符串
     */
    public static String format(String strDate)
    {
        try
        {
            Date date = CalendarUtil.parse(strDate, "yyyy-MM-dd");
            return CalendarUtil.format(date, "yyyyMMdd");
        }
        catch (ParseException e)
        {
            return "";
        }
    }

    /**
     * <将一个日期字符串格式化指定的格式化返回>
     *
     * @param strDate 日期字符串
     * @param dateFormat 当前日期字符串的格式
     * @param returnFormat 需要返回的日期格式化
     * @return 返回returnFormat格式化的字符串
     */
    public static String format(String strDate, String dateFormat, String returnFormat)
    {
        try
        {
            Date date = CalendarUtil.parse(strDate, dateFormat);
            return CalendarUtil.format(date, returnFormat);
        }
        catch (ParseException e)
        {
            return "";
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *
     * 按格式输入起止日期，然后返回日期内的月份
     *
     * @param startTime
     * @param endTime
     * @param fromFormat 入参格式 ,yyyyMM
     * @param toFormat 返回的格式，yyyyMM
     * @return
     */
    public static List<String> getMonthBetween(Object startTime, Object endTime, String fromFormat, String toFormat)
    {
        String minDate = String.valueOf(startTime);
        String maxDate = String.valueOf(endTime);
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat fFormat = new SimpleDateFormat(fromFormat);
        SimpleDateFormat tFormat = new SimpleDateFormat(toFormat);

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try
        {
            min.setTime(fFormat.parse(minDate));
            max.setTime(fFormat.parse(maxDate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        // 最小设为1号，最大设为2号
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max))
        {
            result.add(tFormat.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 判断本天是不是周几(最后一个参数)
     *
     * @param year 年
     * @param month 月
     * @param date 日
     * @param weekDay 周几
     * @return 是否是周几
     */
    public static boolean isWeek(int year, int month, int date, int weekDay)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(year, (month - 1), date);
        return cal.get(Calendar.DAY_OF_WEEK) == weekDay;
    }

    /**
     * 获取传来的时间是周几
     *
     * @return 周几
     */
    public static int isWeek(Calendar cal)
    {
        cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return week == 0 ? 7 : week;
    }

    /**
     * 获取今天是周几
     *
     * @return 周几
     */
    public static int isWeek()
    {
        Calendar cal = Calendar.getInstance();
        return isWeek(cal);
    }

    /**
     * 获取当前输入月份所在季度的所有月份
     *
     * @param date
     * @param fromFormat 入参格式 yyyyMM
     * @param toFormat 返回格式 yyyyMM
     * @param returnAll true 返回所有 false 只返回比传入月份大的月(包括传入的月份)
     * @return
     */
    public static List<String> getSeasonDate(Object date, String fromFormat, String toFormat, boolean returnAll)
    {
        String dataStr = String.valueOf(date);
        List<String> season = new ArrayList<String>(3);
        SimpleDateFormat fFormat = new SimpleDateFormat(fromFormat);
        SimpleDateFormat tFormat = new SimpleDateFormat(toFormat);
        Calendar c = Calendar.getInstance();
        int nSeason = 0;
        try
        {
            c.setTime(fFormat.parse(dataStr));
            nSeason = getSeason(fFormat.parse(dataStr));
            if (nSeason == 1)
            {// 第一季度

                c.set(Calendar.MONTH, Calendar.JANUARY);
                season.add(0, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.FEBRUARY);
                season.add(1, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.MARCH);
                season.add(2, tFormat.format(c.getTime()));
            }
            else if (nSeason == 2)
            {// 第二季度
                c.set(Calendar.MONTH, Calendar.APRIL);
                season.add(0, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.MAY);
                season.add(1, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.JUNE);
                season.add(2, tFormat.format(c.getTime()));
            }
            else if (nSeason == 3)
            {// 第三季度
                c.set(Calendar.MONTH, Calendar.JULY);
                season.add(0, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.AUGUST);
                season.add(1, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.SEPTEMBER);
                season.add(2, tFormat.format(c.getTime()));
            }
            else if (nSeason == 4)
            {// 第四季度
                c.set(Calendar.MONTH, Calendar.OCTOBER);
                season.add(0, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.NOVEMBER);
                season.add(1, tFormat.format(c.getTime()));
                c.set(Calendar.MONTH, Calendar.DECEMBER);
                season.add(2, tFormat.format(c.getTime()));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        if (!returnAll)
        {
            for (int i = 0; i < season.size(); i++)
            {
                int nStr = Integer.parseInt(dataStr.substring(5, 7));
                int returnStr = Integer.parseInt(season.get(i).substring(5, 7));
                if (returnStr < nStr)
                {
                    season.remove(i);
                    i--;
                }
            }
        }
        return season;
    }

    /**
     * 获取季度 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date)
    {

        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month)
        {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     *
     * 将以逗号分隔的月份转换为月份名称
     *
     * @param months 201606格式，以逗号拼接
	 * @param quarterType 季度类型 0正常 1中国的季节季度类型
     * @return 名称
     */
    public static String getMonthName(String months, Integer quarterType)
    {
        if (months == null || months.equals(""))
        {
            return "";
        }
        String monthsName = "";
        String[] monthsStringArray = months.split(",");
        int monthNum = monthsStringArray.length;
        Integer[] monthsArray = new Integer[monthNum];
        for (int i = 0; i < monthsArray.length; i++)
        {
            monthsArray[i] = Integer.parseInt(monthsStringArray[i]);
        }
        Arrays.sort(monthsArray);
        for (int i = 0; i < monthsArray.length; i++)
        {
            monthsName += "," + monthsArray[i];
        }
        monthsName = monthsName.substring(1);

        Integer maxMonth = monthsArray[monthsArray.length - 1];
        Integer minMonth = monthsArray[0];
        int monthsNum = (maxMonth / 100 * 12 + maxMonth % 100) - (minMonth / 100 * 12 + minMonth % 100);
        if (monthsNum > monthsArray.length - 1 || monthsArray.length == 1)
        {
            return months;
        }
        monthsName = minMonth + "~" + maxMonth;
        int year = minMonth / 100;
        int firstMonth = minMonth%100%12;
        if(quarterType == 1 && monthNum == 3){
            firstMonth = (10 + firstMonth)%12;
        }
        switch (firstMonth + ":" + monthNum)
        {
            case "1:3":
                monthsName = year + "年第一季度";
                break;
            case "4:3":
                monthsName = year + "年第二季度";
                break;
            case "7:3":
                monthsName = year + "年第三季度";
                break;
            case "10:3":
                monthsName = year + "年第四季度";
                break;
            case "1:6":
                monthsName = year + "年上半年";
                break;
            case "7:6":
                monthsName = year + "年下半年";
                break;
            case "1:12":
                monthsName = year + "年全年";
                break;
        }
        return monthsName;
    }

    /**
     * 获取当月的天数
     *
     * @param month(月份:201602)
     * @param format(格式:yyyyMM)
     * @return
     */
    public static int getMonthDays(Object month, String format)
    {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat(format);
        try
        {
            rightNow.setTime(simpleDate.parse(String.valueOf(month)));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * <获取一个月份中的第n天>
     *
     * @param dateStr 传入的日期字符串
     * @param fromat 传入的日期格式
     * @param backFromat 返回日期格式
     * @param day 需要返回第几天
     * @return 返回指定格式的日期字符串
     */
    public static String getDayOfMonth(String dateStr, String fromat, String backFromat, int day)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        SimpleDateFormat backSdf = new SimpleDateFormat(backFromat);
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        String dayOfMonth = "";
        try
        {
            date = sdf.parse(dateStr);
            calendar.setTime(date);
            // 设置日历中月份的第n天
            calendar.set(Calendar.DAY_OF_MONTH, day);
            // 格式化日期
            dayOfMonth = backSdf.format(calendar.getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return dayOfMonth;
    }

    /**
     *
     * 获取传入的时间范围内，传入月份的天数
     *
     * @param startTime
     * @param endTime
     * @param timeFormat
     * @param month
     * @param monthFormat
     * @return
     * @throws ParseException
     */
    public static int getDaysRangeOfMonth(Object startTime, Object endTime, String timeFormat, Object month,
        String monthFormat)
    {
        try
        {
            Calendar rentStartDate = getCalendar(String.valueOf(startTime), timeFormat);
            Calendar rentEndDate = getCalendar(String.valueOf(endTime), timeFormat);
            Calendar monthFirstDay = getCalendar(String.valueOf(month), monthFormat);
            monthFirstDay.set(Calendar.DAY_OF_MONTH, 1);
            Calendar monthLastDay = getCalendar(String.valueOf(month), monthFormat);
            monthLastDay.set(Calendar.DAY_OF_MONTH, monthLastDay.getActualMaximum(Calendar.DAY_OF_MONTH));

            Calendar firstDay = rentStartDate.after(monthFirstDay) ? rentStartDate : monthFirstDay;
            Calendar lastDay = rentEndDate.after(monthLastDay) ? monthLastDay : rentEndDate;
            if (firstDay.after(lastDay))
            {
                return 0;
            }
            return daysBetween(firstDay.getTime(), lastDay.getTime()) + 1;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * <判断是否是同一天>
     *
     * @param date1 第一个比较日期
     * @param date2 第二个比较日期
     * @return 是同一天返回true 不是同一天返回false
     */
    public static boolean isSameDate(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    /**
     *
     * <根据指定的间隔时间类型获取指定日期字符串间隔数值的指定格式的日期>
     *
     * @param dateStr 日期字符串
     * @param interval 间隔数值 可以为正负整数
     * @param intervalType 间隔类型
     * @param dateFormat 日期格式 指定
     * @return 返回指定[dateStr]日期字符串 指定[dateFormat]日期格式 指定[intervalType] 间隔类型 指定[interval]间隔数值的日期字符串
     */
    public static String getIntervalDate(String dateStr, int interval, String intervalType, String dateFormat)
    {
        Calendar calendar = null;
        try
        {
            calendar = getCalendar(String.valueOf(dateStr), dateFormat);

            switch (intervalType)
            {
                // 年
                case Constant.YEAR:
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + interval);
                    break;
                // 月
                case Constant.MONTH:
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + interval);
                    break;
                // 日
                case Constant.DAY:
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + interval);
                    break;
                // 时
                case Constant.HOURS:
                    calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + interval);
                    break;
                // 分
                case Constant.MINUTES:
                    calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + interval);
                    break;
                // 秒
                case Constant.SECONDS:
                    calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + interval);
                    break;
                // 毫秒
                case Constant.MILLISECOND:
                    calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND) + interval);
                    break;
            }
            return calendarToString(calendar, dateFormat);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * <判断一个日期字符串是否在指定的开始和结束日期字符串之内>
     *
     * @param dateStr 需要进行判断的日期字符串
     * @param startDateStr 指定的开始日期字符串
     * @param endDateStr 指定的结束日期字符串
     * @param dateFormat 指定的日期格式
     * @return 如果在指定的日期范围内返回true否则返回false
     */
    public static boolean isRange(String dateStr, String startDateStr, String endDateStr, String dateFormat)
    {
        Calendar calendar = null;
        Calendar startCalendar = null;
        Calendar endCalendar = null;
        try
        {
            calendar = getCalendar(String.valueOf(dateStr), dateFormat);
            startCalendar = getCalendar(String.valueOf(startDateStr), dateFormat);
            endCalendar = getCalendar(String.valueOf(endDateStr), dateFormat);
            return !calendar.before(startCalendar) && !calendar.after(endCalendar);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * <获取两个日期字符串之间的时间差按小时/分钟/秒计算>
     *
     * @param beginDate
     * @param endDate
     * @param format
     * @param calculationType 返回起止时间类型[H:小时m:分钟s:秒] ex:H:返回10.00格式 m返回120.00s返回360.00
     * @return 返回两个日期字符串之间的字符串表示的差值按小时/分钟/秒计算默认返回一个字符串?天?时?分?秒
     */
    public static String getDateDifference(String beginDate, String endDate, String format, String calculationType)
    {
        Calendar beginCalendar = null;
        Calendar endCalendar = null;
        try
        {
            endCalendar = getCalendar(endDate, format);
            beginCalendar = getCalendar(beginDate, format);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        long differenceTime = (endCalendar.getTimeInMillis() - beginCalendar.getTimeInMillis());
        int dayParam = 1000 * 60 * 60 * 24;// 一天等于毫秒数
        int hourParam = 1000 * 60 * 60;// 一小时等于毫秒数
        long day = Math.abs(differenceTime / (dayParam));//
        differenceTime = Math.abs(differenceTime - day * dayParam);// 减去天的毫秒数。再求小时个数
        long hour = Math.abs(differenceTime / (hourParam));
        differenceTime = Math.abs(differenceTime - hour * hourParam);// 减去小时的毫秒数。再求分钟个数
        long minute = Math.abs(differenceTime / (1000 * 60));
        // 换算为分钟数总和
        long totalMinute = day * 24 * 60 + hour * 60 + minute;
        // 换算为秒数总和
        long totalSecond = totalMinute * 60;
        // 一小时60分钟
        double minuteOfHour = 60;
        // ?分钟的double值
        double minutes = minute;
        // 换算为小时数总和
        double totalHour = day * 24 + hour + minutes / minuteOfHour;
        String returnResult = "";
        if (calculationType.equals("H"))
        {
            returnResult = String.valueOf(totalHour);
        }
        else if (calculationType.equals("m"))
        {
            returnResult = String.valueOf(totalMinute);

        }
        else if (calculationType.equals("s"))
        {
            returnResult = String.valueOf(totalSecond);
        }
        else
        {
            returnResult = day + "天" + hour + "小时" + minute + "分钟";
        }
        return returnResult;
    }

    // public static void main(String args[])
    // {
    // String startTime = "2016-09-05";
    // String endTime = "2016-09-08";
    // Set<String> st = getIntervalDateStr(startTime, endTime, "yyyy-MM-dd", "day", -1);
    // System.out.println(st);
    // }

    /**
     *
     * <验证一个时间是否在另一个时间之前>
     *
     * @param startTime 第一个时间
     * @param endTime 第二个时间
     * @param format 日期字符串格式
     * @return 第一个时间在第二个时间之前则返回true 否则返回false
     */
    public static boolean before(Object startTime, Object endTime, String format)
    {
        Calendar start = null;
        Calendar end = null;
        try
        {
            start = getCalendar(String.valueOf(startTime), format);
            end = getCalendar(String.valueOf(endTime), format);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return start.before(end);
    }

    /**
     *
     * <验证一个时间是否在另一个时间之后>
     *
     * @param startTime 第一个时间
     * @param endTime 第二个时间
     * @param format 日期字符串格式
     * @return 第一个时间在第二个时间之后则返回true 否则返回false
     */
    public static boolean after(Object startTime, Object endTime, String format)
    {
        Calendar start = null;
        Calendar end = null;
        try
        {
            start = getCalendar(String.valueOf(startTime), format);
            end = getCalendar(String.valueOf(endTime), format);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return start.after(end);
    }

    /**
     *
     * <根据起止日期获取所有指定格式的日期字符串>
     *
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @param format 日期格式
     * @param intervalType 间隔类型
     * @param interval 间隔值
     * @return 根据起止日期获取所有指定格式的日期字符串
     */
    public static Set<String> getIntervalDateStr(String startTime, String endTime, String dateFormat,
        String intervalType, int interval)
    {
        Set<String> dateStrList = new HashSet<String>();
        String dateStr = getIntervalDate(endTime, interval, intervalType, dateFormat);
        // 然后循环时间间隔去获取一个字符串
        // 如果日期字符串在开始结束时间
        String endDateStr = getIntervalDate(endTime, interval, intervalType, dateFormat);
        String startDateStr = getIntervalDate(startTime, -interval, intervalType, dateFormat);
        dateStr = getIntervalDate(endTime, interval, intervalType, dateFormat);
        // 如果结束时间间隔之后时间在开始结束时间之间那么就把结束时间放入
        if (isRange(endDateStr, startTime, endTime, dateFormat))
        {
            dateStrList.add(endTime);
        }
        // 如果开始时间间隔之后时间在开始结束时间之间那么就把开始时间放入
        if (isRange(startDateStr, startTime, endTime, dateFormat))
        {
            dateStrList.add(startTime);
        }
        // 每间隔一次如果在开始结束之间那么就放入
        while (isRange(dateStr, startTime, endTime, dateFormat))
        {
            dateStrList.add(dateStr);
            dateStr = getIntervalDate(dateStr, interval, intervalType, dateFormat);
        }
        // 至少返回一个开始日期
        if (dateStrList.size() == 0)
        {
            dateStrList.add(startTime);
        }
        return dateStrList;
    }

    /**
     *
     * 传入一个时间格式的数组，判断是否时间有冲突
     *
     * @param timeList 需要判断的时间列表,
     * @param format 数组里的时间的格式
     * @return 如果时间有冲突 返回true, 否则， 返回false
     */
    public static boolean isTimeConflict(List<List<String>> timeList, String format)
    {
        if (timeList != null && timeList.size() > 0)
        {
            // 是否存在跨天
            boolean isExistTwoDay = false;
            String earliestTime = null;
            String latestTime = null;
            int sectionSeconds = 0;
            String twoDayStartTime = null;
            for (int i = 0; i < timeList.size(); i++)
            {
                String startTime = timeList.get(i).get(0);
                String endTime = timeList.get(i).get(1);
                boolean isTwoDay = after(startTime, endTime, format);
                if (isTwoDay)
                {
                    // 如果是跨天，但之前已经有跨天了,则肯定有冲突
                    if (isExistTwoDay)
                        return true;
                    isExistTwoDay = true;
                    endTime = startTime;
                    twoDayStartTime = startTime;
                }
                else
                {
                    // 如果非跨天的结束时间在跨天的开始时间之后，则有冲突
                    if (twoDayStartTime != null && after(endTime, twoDayStartTime, format))
                    {
                        return true;
                    }
                    // 累加时间间隔
                    String differentSeconds = getDateDifference(startTime, endTime, format, "s");
                    sectionSeconds += Integer.parseInt(differentSeconds);
                }
                earliestTime = earliestTime == null ? startTime
                    : (before(earliestTime, startTime, format) ? earliestTime : startTime);
                latestTime = latestTime == null ? endTime : (after(latestTime, endTime, format) ? latestTime : endTime);
            }
            // 获取所有时间段的最晚日期与最早日期的时间间隔, 如果累加的时间间隔比时间总长度大，则有冲突
            String allSectionSeconds = getDateDifference(earliestTime, latestTime, format, "s");
            return sectionSeconds > Integer.parseInt(allSectionSeconds);
        }
        return false;
    }

    public static void getTimeInterval(Integer choice, Map<String, Object> parMap)
    {

        if (choice == null)
        {
            throw new RuntimeException("choice can not be null!");
        }
        switch (choice)
        {
            // 本周
            case 0:
                theDateOfThisWeek(parMap);
                break;
            // 本半月
            case 1:
                theDateOfHalfMonth(parMap);
                break;
            // 本月
            case 2:
                theDateOfMonth(parMap);
                break;
            // 本季度
            case 3:
                theDateOfSeason(parMap);
                break;
            // 本半年
            case 4:
                lastDateOfHalfYear(parMap);
                break;
            // 本年
            default:
                lastDateOfYear(parMap);
                break;
        }
    }

    // 计算本周当前日期到周末的时间
    public static void theDateOfThisWeek(Map<String, Object> parMap)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek)
        {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 星期几与一个星期第一天的差值
        String beginTime = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 7);
        String endTime = sdf.format(cal.getTime());
        parMap.put("beginTime", beginTime);
        parMap.put("endTime", endTime);
    }

    @SuppressWarnings("deprecation")
    public static void theDateOfHalfMonth(Map<String, Object> parMap)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.getActualMaximum(Calendar.DATE);
        Date today = new Date();
        cal.setTime(today);
        int halfday = day / 2;
        if (today.getDay() >= halfday)
        {
            parMap.put("beginTime", dateFormat(cal, year, month, 1));
            parMap.put("endTime", dateFormat(cal, year, month, halfday));

        }
        else
        {
            parMap.put("beginTime", dateFormat(cal, year, month, halfday+1));
            parMap.put("endTime", dateFormat(cal, year, month, day));
        }
    }

    // 计算出本月的最后一天日期
    public static void theDateOfMonth(Map<String, Object> parMap)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.getActualMaximum(Calendar.DATE);
        parMap.put("beginTime", dateFormat(cal, year, month, 1));
        parMap.put("endTime", dateFormat(cal, year, month, day));
    }

    // 计算本季度的最后一天
    public static void theDateOfSeason(Map<String, Object> parMap)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int lastday = 0;
        int perSeasonMonth = 4;
        int season = 0;
        if (month % 4 != 0)
        {
            season = month / perSeasonMonth + 1;
        }
        else
        {
            season = month / perSeasonMonth;
        }
        switch (season)
        {
            case 1:
                lastday = 30;
                month = 1;
                break;
            case 2:
                lastday = 31;
                month = 5;
                break;
            case 3:
                lastday = 31;
                month = 9;
                break;
            default:
                break;
        }

        parMap.put("beginTime", dateFormat(cal, year, month, 1));
        parMap.put("endTime", dateFormat(cal, year, perSeasonMonth * season, lastday));
    }

    // 计算出半年的最后一天
    public static void lastDateOfHalfYear(Map<String, Object> parMap)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month <= 6)
        {
            parMap.put("beginTime", dateFormat(cal, year, 1, 1));
            parMap.put("endTime", dateFormat(cal, year, 6, 30));
        }
        else
        {
            parMap.put("beginTime", dateFormat(cal, year, 7, 31));
            parMap.put("endTime", dateFormat(cal, year, 12, 31));
        }

    }

    // 计算出本年的最后一天
    public static void lastDateOfYear(Map<String, Object> parMap)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        parMap.put("beginTime", dateFormat(cal, year, 1, 1));
        parMap.put("endTime", dateFormat(cal, year+1, 1, 1));
    }

    public static String dateFormat(Calendar cal, int year, int month, int day)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return sdf.format(cal.getTime());

    }

    public static String[] calendarLoop(Map<String, Object> paramMap, int days)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = parse((String)paramMap.get("startTime"), "yyyy-MM-dd");
        Date end = parse((String)paramMap.get("endTime"), "yyyy-MM-dd");
        Calendar dd = Calendar.getInstance();// 定义日期实例
        dd.setTime(begin);
        String[] daysArray = new String[days];
        int i = 0;
        while (dd.getTime().before(end))
        {// 判断是否到结束日期
            daysArray[i] = sdf.format(dd.getTime()).replace("-", "/");
            dd.add(Calendar.DATE, 1);// 进行当前日期月份加1
            i++;
        }
        dd.setTime(end);
        daysArray[i] = sdf.format(dd.getTime()).replace("-", "/");
        return daysArray;
    }

    public static int daysBetween(Map<String, Object> paramMap)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = sdf.parse((String)paramMap.get("startTime"));
        Date end = sdf.parse((String)paramMap.get("endTime"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        long time1 = cal.getTimeInMillis();
        cal.setTime(begin);
        long time2 = cal.getTimeInMillis();
        long between_days = (time1 - time2) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String[] sortDay(String[] dayList)
        throws ParseException
    {
        String temp = null;
        int size = dayList.length;
        for (int i = 0; i < size; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (!compared(dayList[i], dayList[j]))
                {
                    temp = dayList[i + 1];
                    dayList[i + 1] = dayList[i];
                    dayList[i] = temp;
                }
            }

        }
        return dayList;
    }

    private static boolean compared(String val1, String val2)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date valD1 = sdf.parse(val1);
        Date valD2 = sdf.parse(val2);
        cal.setTime(valD1);
        return cal.getTime().after(valD2);

    }

    /**
     *
     * <根据当前时间获取 当前时间在每半月的开始结束日期>
     *
     * @param parMap
     */
    public static void theDatesOfHalfMonth(Map<String, Object> parMap)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.getActualMaximum(Calendar.DATE);
        int halfday = day / 2;
        // 如果今天的天大于月半 开始时间从月半开始 结束时间是月末
        int todayDay = cal.get(Calendar.DATE);// 获取日
        if (todayDay >= halfday)
        {
            parMap.put("beginTime", dateFormat(cal, year, month, halfday));
            parMap.put("endTime", dateFormat(cal, year, month, day));

        }
        // 如果今天的天小于月半那么开始时间是1号 结束时间是月半
        else
        {
            parMap.put("beginTime", dateFormat(cal, year, month, 1));
            parMap.put("endTime", dateFormat(cal, year, month, halfday));
        }
    }

    /**
     *
     * <获取一个日期所属部分日期>
     *
     * @param oDate oDate 日期参数 可以为Object
     * @param sFormat 对应日期的格式化字符串
     * @param partMark 将要获取的部分日期的标示
     * @return 返回对应日期的 partMark 对应的部分日期 如果传入空参数返回今天的日期对应的partMark的部分日期
     * @throws ParseException 解析日期参数失败返回异常
     */
    public static int getDatePart(Object oDate, String sFormat, String partMark)
    {
        int field = 0;
        switch (partMark)
        {
            case "y":
                field = Calendar.DAY_OF_YEAR;
                break;
            case "M":
                field = Calendar.MONTH;
                break;
            case "d":
                field = Calendar.DAY_OF_MONTH;
                break;
            case "h":
                field = Calendar.HOUR_OF_DAY;
                break;
            case "m":
                field = Calendar.MINUTE;
                break;
            case "s":
                field = Calendar.SECOND;
                break;
            case "w":
                field = Calendar.DAY_OF_WEEK;
                break;
            default:
                field = Calendar.DAY_OF_YEAR;
                break;
        }
        if (null == oDate || "" == oDate)
        {
            return Calendar.getInstance().get(field);
        }
        Calendar calendar = null;
        try
        {
            calendar = CalendarUtil.getCalendar(String.valueOf(oDate), sFormat);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return calendar.get(field);
    }
}