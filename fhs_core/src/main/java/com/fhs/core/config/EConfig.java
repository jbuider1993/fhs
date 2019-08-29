package com.fhs.core.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 配置文件工具，自定义配置文件使用此工具类读取
 *
 * @author wanglei
 * @version [版本号, 2015年8月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class EConfig implements InitializingBean, CommandLineRunner {

    private static final Logger LOG = Logger.getLogger(EConfig.class);

    public static final String PROJECT_BASE_URL = EConfig.class.getResource("/").getFile();

    /**
     * 不允许外部直接访问静态常量
     */
    public static final Properties PATH = new Properties();

    /**
     * 零散的配置信息
     */
    public static final Properties OTHER_CONFIG = new Properties();

    /**
     * 是否为调试模式
     */
    public static boolean IS_DEBUG = false;

    public static String projectName;

    @ApolloConfig("path")
    private Config pathConfig;

    @ApolloConfig("other")
    private Config otherConfig;

    @Value("${fhs.disable-apollo:false}")
    private boolean isDisableApollo;

    /**
     * 配置文件改变的监听集合
     */
    private List<ConfigChangeListener> configValueChangeListenerList = new ArrayList();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (isDisableApollo) {
            initConfigFromProperties();
        } else {
            initConfigFromApollo();
        }
    }

    /**
     * 在本地properties文件初始化apollo配置
     */
    private void initConfigFromProperties() {
        InputStream pathInputStream = null;
        InputStream otherInputStream = null;
        String jarPath = getPath();
        LOG.info("jar 路径:" + jarPath);
        File pathFile = new File(jarPath + "/config/path.properties");
        if (pathFile.exists()) {
            try {
                pathInputStream = new FileInputStream(pathFile);
                LOG.info("从" + pathFile.getPath() + "读取path.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        File otherFile = new File(jarPath + "/config/other.properties");
        if (otherFile.exists()) {
            try {
                otherInputStream = new FileInputStream(otherFile);
                LOG.info("从" + otherFile.getPath() + "读取other.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (pathInputStream == null) {
            pathInputStream = EConfig.class.getClassLoader().getResourceAsStream("path.properties");
            LOG.info("从jar读取path.properties");
        }
        if (otherInputStream == null) {
            otherInputStream = EConfig.class.getClassLoader().getResourceAsStream("other.properties");
            LOG.info("从jar读取other.properties");
        }

        // 使用properties对象加载输入流
        try {
            PATH.load(pathInputStream);
            OTHER_CONFIG.load(otherInputStream);
        } catch (IOException e) {
            LOG.error("加载配置文件错误", e);
        }
    }

    /**
     * 获取jar包所在路径
     *
     * @return jar包所在路径
     */
    public static String getPath() {
        ApplicationHome home = new ApplicationHome(EConfig.class);
        String path = home.getDir().getAbsolutePath();
        return path;
    }

    /**
     * 从apollo初始化配置文件
     */
    private void initConfigFromApollo() {
        new Thread() {
            public void run() {
                try {
                    Properties properties = new Properties();
                    // 使用ClassLoader加载properties配置文件生成对应的输入流
                    InputStream in = EConfig.class.getClassLoader().getResourceAsStream("config.properties");
                    // 使用properties对象加载输入流
                    properties.load(in);
                    projectName = properties.getProperty("fhs.projectName");
                    // 在阿波罗中获取 path和 other的配置项
                    Set<String> propertyNames = pathConfig.getPropertyNames();
                    for (String key : propertyNames) {
                        PATH.put(key, pathConfig.getProperty(key, ""));
                    }
                    PATH.put("basePath", pathConfig.getProperty(projectName + "_basePath", ""));
                    propertyNames = otherConfig.getPropertyNames();
                    for (String key : propertyNames) {
                        OTHER_CONFIG.put(key, otherConfig.getProperty(key, ""));
                    }
                    LOG.info("成功加载path properties:" + JsonUtils.map2json(PATH));
                    LOG.info("成功加载other properties:" + JsonUtils.map2json(OTHER_CONFIG));
                } catch (FileNotFoundException e) {
                    LOG.error(new Object(), e);
                } catch (IOException e) {
                    LOG.error(new Object(), e);
                }
            }
        }.start();
    }

    /**
     * 获取properties value
     *
     * @param properties 参数为:java.util.Properties 对象
     * @param key        传入一个java.util.Properties 对象所包含的键
     * @return 对应的value 如果不包含此key返回null
     */
    private static String getValue(Properties properties, String key) {
        return properties.containsKey(key) ? properties.getProperty(key) : null;
    }

    /**
     * 获取路径配置
     *
     * @param key 路径类型
     * @return 路径
     */
    public static String getPathPropertiesValue(String key) {
        return getValue(PATH, key);
    }


    /**
     * 获取其他杂项配置信息
     *
     * @param key 配置的key
     * @return 配置的值
     */
    public static String getOtherConfigPropertiesValue(String key) {
        return getValue(OTHER_CONFIG, key);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (this.isDisableApollo) {
            return;
        }
        LOG.infoMsg("项目启动,初始化apollo的配置文件>>>>>>>>>......");
        //加载指定path的配置
        Set<String> configs = pathConfig.getPropertyNames();
        if (!CheckUtils.isNullOrEmpty(configs)) {
            configs.forEach(key -> {
                PATH.setProperty(key, pathConfig.getProperty(key, null));
            });
            //监听path中的key发生变化后就改变其值
            monitorApolloConfigurationChange(PATH, pathConfig);
        }

        //加载指定otherpath的配置
        Set<String> otherConfigs = otherConfig.getPropertyNames();
        if (!CheckUtils.isNullOrEmpty(otherConfigs)) {
            otherConfigs.forEach(key -> {
                OTHER_CONFIG.setProperty(key, otherConfig.getProperty(key, null));
            });
            //监听otherpath中的key发生变化后就改变其值
            monitorApolloConfigurationChange(OTHER_CONFIG, otherConfig);
        }
        configValueChangeListenerList.forEach(listener -> {
            listener.refresh(PATH, OTHER_CONFIG);
        });
    }

    /**
     * @param properties apollo的namespace
     * @param config     apollo的配置
     * @desc 监听apollo配置中发生变化的值
     */
    public void monitorApolloConfigurationChange(Properties properties, Config config) {
        config.addChangeListener(configChangeEvent -> {
            Set<String> keys = configChangeEvent.changedKeys();
            for (String key : keys) {
                ConfigChange configChange = configChangeEvent.getChange(key);
                //打印日志
                LOG.infoMsg(configChange.getPropertyName() + "的值改变了，原值:[" +
                        configChange.getOldValue() + "],新值:[" + configChange.getNewValue() + "]");
                //将获取apollo中新值覆盖原来的值
                properties.put(configChange.getPropertyName(), configChange.getNewValue());
            }
        });
    }


    public void registerConfigChangeListener(ConfigChangeListener listener) {
        this.configValueChangeListenerList.add(listener);
    }
}
