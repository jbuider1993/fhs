package com.fhs.core.config;

import java.util.Properties;

/**
 * 当配置文件改变的时候触发(阿波罗模式适用)
 */
@FunctionalInterface
public  interface  ConfigChangeListener{
     void refresh(Properties pathConfig, Properties otherConfig);
}