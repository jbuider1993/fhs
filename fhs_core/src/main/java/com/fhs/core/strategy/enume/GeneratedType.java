package com.fhs.core.strategy.enume;

/**
 * 主键生成类型
 * 
 * @author jianbo.qin
 *
 */
public class GeneratedType
{
    public static final String AUTO = "AUTO"; // 数据库int自动生成
    
    public static final String UUID = "UUID"; // UUID 32位长度

    private GeneratedType()
    {
    }
}
