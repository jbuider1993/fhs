package com.fhs.core.validation;

import java.util.regex.Matcher;

public class PatternTest
{
    public static void main(String[] args)
    {
        // 邮箱测试
        String str = "d@6e.gg";
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println("手机验证:" + rs);
        
        // 正整数测试
        
        String str1 = "1111";
        String regEx1 = "^[1-9]\\d*$";
        java.util.regex.Pattern pattern1 = java.util.regex.Pattern.compile(regEx1);
        Matcher matcher1 = pattern1.matcher(str1);
        boolean rs1 = matcher1.matches();
        System.out.println("正整数:" + rs1);
    }
}
