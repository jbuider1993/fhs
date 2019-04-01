package com.fhs.common.utils;

import java.math.BigDecimal;

/**
 * 比较工具类
 *
 * @Filename: CompareUtils.java
 * @Description:
 * @Version: 1.0
 * @Author: yaoyang
 * @Email: 15947277970@163.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class CompareUtils
{

    /**
     * 高精度比较Double
     * @param x
     * @param y
     * @return
     */
    public static boolean compareDouble(Double x,Double y) {

        if (null == x || null == y)
        {
            return false;
        }
        BigDecimal data1 = new BigDecimal(x);
        BigDecimal data2 = new BigDecimal(y);
        if (data1.compareTo(data2) == 0)
        {
            return true;
        }
        return false;
    }
}
