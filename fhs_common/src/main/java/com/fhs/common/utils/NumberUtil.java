package com.fhs.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 *
 * @author wangpengfei
 * @version [版本号, 2016年7月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NumberUtil
{
    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 转换为保留2位小数 四舍五入
     *
     * @param number 需要转换的值
     * @return 结果
     */
    public static double twoDecimalPlaces(double number)
    {
        return Math.round(number * 100) / 100.0;
    }

    /**
     * 我才是真正的四舍五入后保留两位小数.包括(1.0 也能变成 1.00)
     *
     * @param number
     * @return　但是我返回的类型是String
     */
    public static String keepTwoDecimalPlaces(double number)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(Math.round(number * 100) / 100.0);
    }

    /**
     * 四舍五入保留两位小数 (只转 :为double类型的数字)
     *
     * @param list
     * @return list
     */
    public static List<Map<String, Object>> keepTwoDecimalPlaces(List<Map<String, Object>> list)
    {
        for (Map<String, Object> tempMap : list)
        {
            for (String key : tempMap.keySet())
            {
                if (tempMap.get(key) instanceof Double)
                {
                    tempMap.put(key, keepTwoDecimalPlaces((ConverterUtils.toDouble(tempMap.get(key)))));
                }
            }
        }
        return list;
    }

    /***
     * 生成带位数的验证码
     *
     * @param several 生成几位参数
     *
     */
    public static String createRandomVcode(Integer several)
    {
        // 验证码
        String vcode = "";
        for (int i = 0; i < several; i++)
        {
            vcode = vcode + (int)(Math.random() * 10);
        }
        return vcode;
    }

    /***
     * 替换字符串中间的字符
     *
     * @param str 替换的字符串
     */
    public static String hideStr(String str)
    {
        String resultStr = str;
        if (str != null && str.length() > 7)
        {
            resultStr = str.substring(0, 3) + "****" + str.substring(7, str.length());
        }
        return resultStr;
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的累加加法运算。
     *
     * @return 多个参数的和
     */
    public static double add(double... numbers)
    {
        double resultDouble = 0.0;
        int length = numbers.length;
        if (length == 1)
        {
            resultDouble = numbers[0];
        }
        else if (length > 1)
        {
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(numbers[0]));
            for (int i = 0; i < length - 1; i++)
            {
                BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(numbers[i + 1]));
                bigDecimal1 = bigDecimal1.add(bigDecimal2);
            }
            resultDouble = bigDecimal1.doubleValue();
        }
        return resultDouble;
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的累减减法运算。
     *
     * @return 参数顺序不能填错
     */
    public static double sub(double... numbers)
    {
        double resultDouble = 0.0;
        int length = numbers.length;
        if (length == 1)
        {
            resultDouble = numbers[0];
        }
        else if (length > 1)
        {
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(numbers[0]));
            for (int i = 0; i < length - 1; i++)
            {
                BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(numbers[i + 1]));
                bigDecimal1 = bigDecimal1.subtract(bigDecimal2);
            }
            resultDouble = bigDecimal1.doubleValue();
        }
        return resultDouble;
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五不入处理。
     *
     * @param v 需要四舍五不入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五不入后的结果
     */
    public static double numberDown(double v, int scale)
    {
        return new BigDecimal(StringUtil.toString(v)).setScale(scale,BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 提供精确的小数位比较
     * =返回0 小于返回-1 大于返回1
     */
    public static int numberCompareTo(double x,double y) {
    	BigDecimal data1 = new BigDecimal(x);
    	BigDecimal data2 = new BigDecimal(y);
		return data1.compareTo(data2);
    }

    /**
     * 获取区间信息 用的时候问王磊
     *
     * @param sectsionParamList 区间参数
     * @param sectsionParamName 区间参数名称
     * @param init 为Section 里面的paramMap初始化数据的代码
     * @param isMonth 是否为计算月份
     * @return 区间
     */
    @SuppressWarnings("unchecked")
    public static <T> List<EMap<String, Object>> getSectsion(List<T> sectsionParamList, final String sectsionParamName,
        InitSectionPararm init, boolean isMonth)
    {
        List<EMap<String, Object>> resultList = new ArrayList<>();
        // 最小的数字
        int min = 0;
        // 最大的数字
        int max = 0;
        // 上一个数字
        int pre = -10000000;
        // 计数
        int num = 0;
        EMap<String, Object> appendParamMap = null;
        boolean isMap = false;

        if (sectsionParamList == null || sectsionParamList.size() == 0)
        {
            return resultList;
        }
        if (sectsionParamList.get(0).getClass().toString().contains("Map"))
        {
            isMap = true;
        }
        Collections.sort(sectsionParamList, new Comparator<T>()
        {

            @Override
            public int compare(T o1, T o2)
            {
                int num1 = 0;
                int num2 = 0;
                if (o1 instanceof Map)
                {
                    num1 = ConverterUtils.toInt(((Map<String, Object>)o1).get(sectsionParamName));
                    num2 = ConverterUtils.toInt(((Map<String, Object>)o2).get(sectsionParamName));
                }
                else
                {
                    num1 = ConverterUtils.toInt(o1);
                    num2 = ConverterUtils.toInt(o2);
                }
                if (num1 > num2)
                {
                    return 1;
                }
                if (num1 < num2)
                {
                    return -1;
                }
                return 0;
            }
        });
        Map<String, Object> tempMap = null;
        for (T param : sectsionParamList)
        {

            int number = 0;

            if (isMap)
            {
                tempMap = (Map<String, Object>)param;
                number = ConverterUtils.toInt(tempMap.get(sectsionParamName));
            }
            else
            {
                number = ConverterUtils.toInt(param);
            }
            int cha = number - pre;
            // 如果传过来的是月份 那么需要判断是否差是89 比如 201701-201612= 89
            if (cha == 1 || (isMonth && cha == 89))
            {
                num++;
                max = number;
                pre = number;
                if (init != null && isMap)
                {
                    init.init(tempMap, appendParamMap);
                }

            }
            else
            {
                if (appendParamMap != null)
                {
                    appendParamMap.put("start", min);
                    appendParamMap.put("end", max);
                    appendParamMap.put("numbers", num);
                }
                appendParamMap = new EMap<>();
                resultList.add(appendParamMap);
                min = number;
                pre = number;
                max = number;
                num = 1;
                if (init != null && isMap)
                {
                    init.init(tempMap, appendParamMap);
                }
            }
            if (appendParamMap != null)
            {
                appendParamMap.put("start", min);
                appendParamMap.put("end", max);
                appendParamMap.put("numbers", num);
            }
        }
        return resultList;
    }

    /**
     * 在getSectsion的时候传入一个InitSectionPararm
     *
     * @author wanglei
     * @version [版本号, 2016年12月20日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public static abstract class InitSectionPararm
    {
        /**
         * 初始化参数
         */
        public abstract void init(Map<String, Object> sourceParam, EMap<String, Object> resultMap);
    }

    /**
     * 获取两个int类型的百分比 精确到小数点后2位
     * @return
     * @author yaoyang
     * @version [版本号, 2018年4月24日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public static String getPercentage(Integer num1,Integer num2) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getPercentInstance();

        float totle = (float) num1 / (float) num2;

        String result = numberFormat.format(totle);

        if(result.contains("∞") || result.contains("�")){
            result = "0%";
        }
        return  result;
    }


    /**
     * 占比计算保留小数的位数方法
     * 转成百分数
     * 当前数除以总数
     * @param  num1 ,num2  num1/num2
     * @return  rate  保留2位小数的
     */
    public static String  division(int num1,int num2){
        String rate="0.00%";
        //定义格式化起始位数
        String format="0.00";
        if(num2 != 0 && num1 != 0){
            DecimalFormat dec = new DecimalFormat(format);
            rate =  dec.format((double) num1 / num2*100)+"%";
            while(true){
                if(rate.equals(format+"%")){
                    format=format+"0";
                    DecimalFormat dec1 = new DecimalFormat(format);
                    rate =  dec1.format((double) num1 / num2*100)+"%";
                }else {
                    break;
                }
            }
        }else if(num1 != 0 && num2 == 0){
            rate = "100%";
        }
        return rate;
    }

    /**
     *
     * 判断是不是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


    /**
     * 计算两个数据变化趋势
     * 若当前大于或者等于被对比则上升[(当前-对比)/对比 * 100%]
     * 若当前小于被对比则下降[(对比-当前)/当前 * 100%]
     * @param currentNumber: 原因数据1:现在数据
     * @param compareNumber: 原因数据2:对比数据
     * @return
     */
    public static Map calculateTrendOfChange(int currentNumber, int compareNumber){
        Map<String,Object> changePercentageInfo = new HashMap<String,Object> ();
        if (currentNumber >= compareNumber){//上升
            changePercentageInfo.put ( "percentage",  getPercentage (currentNumber - compareNumber, compareNumber));
            changePercentageInfo.put ( "change",  "rise");
        }else{//下降
            changePercentageInfo.put ( "percentage",  getPercentage (compareNumber - currentNumber, currentNumber));
            changePercentageInfo.put ( "change",  "decline");
        }
        return changePercentageInfo;
    }
}
