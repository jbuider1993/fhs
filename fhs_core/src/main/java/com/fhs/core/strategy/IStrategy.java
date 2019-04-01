package com.fhs.core.strategy;

/**
 * 自定义主键生成器
 * by jackwong
 */
public interface IStrategy {



    /**
     * 生成一个主键
     * @param className 类名字
     * @return 主键内容
     */
    Object getPkey(String className);
}
