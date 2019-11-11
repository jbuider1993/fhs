package com.fhs.workflow.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class AotuCreateTable {
    public static void main(String[] args) {
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        pec.setJdbcDriver("com.mysql.jdbc.Driver");
        pec.setJdbcUrl("jdbc:mysql://192.168.0.107:3306/jack?useUnicode=true&characterEncoding=utf8");
        pec.setJdbcUsername("root");
        pec.setJdbcPassword("123qwe");

        /**
         * DB_SCHEMA_UPDATE_FALSE 不能自动创建表，需要表存在 create-drop 先删除表再创建表
         * DB_SCHEMA_UPDATE_TRUE 如何表不存在，自动创建和更新表
         */
        //pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎对象
        ProcessEngine processEngine = pec.buildProcessEngine();
        
        
    }
}
