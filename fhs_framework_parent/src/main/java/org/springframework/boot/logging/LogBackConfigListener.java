//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * by jackwang
 * 解决logback 从配置中心读取不起做用的问题
 */
public class LogBackConfigListener implements GenericApplicationListener {
    public static final int DEFAULT_ORDER = -2147483628;
    public static final String CONFIG_PROPERTY = "logging.config";
    public static final String REGISTER_SHUTDOWN_HOOK_PROPERTY = "logging.register-shutdown-hook";
    /** @deprecated */
    @Deprecated
    public static final String PATH_PROPERTY = "logging.path";
    /** @deprecated */
    @Deprecated
    public static final String FILE_PROPERTY = "logging.file";
    public static final String PID_KEY = "PID";
    public static final String EXCEPTION_CONVERSION_WORD = "LOG_EXCEPTION_CONVERSION_WORD";
    public static final String LOG_FILE = "LOG_FILE";
    public static final String LOG_PATH = "LOG_PATH";
    public static final String CONSOLE_LOG_PATTERN = "CONSOLE_LOG_PATTERN";
    public static final String FILE_LOG_PATTERN = "FILE_LOG_PATTERN";
    public static final String LOG_LEVEL_PATTERN = "LOG_LEVEL_PATTERN";
    public static final String LOGGING_SYSTEM_BEAN_NAME = "springBootLoggingSystem";
    private static MultiValueMap<LogLevel, String> LOG_LEVEL_LOGGERS = new LinkedMultiValueMap();
    private static AtomicBoolean shutdownHookRegistered = new AtomicBoolean(false);
    private static Class<?>[] EVENT_TYPES;
    private static Class<?>[] SOURCE_TYPES;
    private final Log logger = LogFactory.getLog(this.getClass());
    private LoggingSystem loggingSystem;
    private int order = -2147483628;
    private boolean parseArgs = true;
    private LogLevel springBootLogging = null;

    public LogBackConfigListener() {
    }

    public boolean supportsEventType(ResolvableType resolvableType) {
        return this.isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    public boolean supportsSourceType(Class<?> sourceType) {
        return this.isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class... supportedTypes) {
        if (type != null) {
            Class[] var3 = supportedTypes;
            int var4 = supportedTypes.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<?> supportedType = var3[var5];
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            this.onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent)event);
        }
    }

    private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        this.loggingSystem = LoggingSystem.get(event.getSpringApplication().getClassLoader());
        this.loggingSystem.beforeInitialize();
    }

    private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        if (this.loggingSystem == null) {
            this.loggingSystem = LoggingSystem.get(event.getSpringApplication().getClassLoader());
        }

        this.initialize(event.getEnvironment(), event.getSpringApplication().getClassLoader());
    }

    private void onApplicationPreparedEvent(ApplicationPreparedEvent event) {
        ConfigurableListableBeanFactory beanFactory = event.getApplicationContext().getBeanFactory();
        if (!beanFactory.containsBean("springBootLoggingSystem")) {
            beanFactory.registerSingleton("springBootLoggingSystem", this.loggingSystem);
        }

    }

    private void onContextClosedEvent() {
        if (this.loggingSystem != null) {
            this.loggingSystem.cleanUp();
        }

    }

    private void onApplicationFailedEvent() {
        if (this.loggingSystem != null) {
            this.loggingSystem.cleanUp();
        }

    }

    protected void initialize(ConfigurableEnvironment environment, ClassLoader classLoader) {
        (new LoggingSystemProperties(environment)).apply();
        LogFile logFile = LogFile.get(environment);
        if (logFile != null) {
            logFile.applyToSystemProperties();
        }

        this.initializeEarlyLoggingLevel(environment);
        this.initializeSystem(environment, this.loggingSystem, logFile);
        this.initializeFinalLoggingLevels(environment, this.loggingSystem);
        this.registerShutdownHookIfNecessary(environment, this.loggingSystem);
    }

    private void initializeEarlyLoggingLevel(ConfigurableEnvironment environment) {
        if (this.parseArgs && this.springBootLogging == null) {
            if (this.isSet(environment, "debug")) {
                this.springBootLogging = LogLevel.DEBUG;
            }

            if (this.isSet(environment, "trace")) {
                this.springBootLogging = LogLevel.TRACE;
            }
        }

    }

    private boolean isSet(ConfigurableEnvironment environment, String property) {
        String value = environment.getProperty(property);
        return value != null && !value.equals("false");
    }

    private void initializeSystem(ConfigurableEnvironment environment, LoggingSystem system, LogFile logFile) {
        LoggingInitializationContext initializationContext = new LoggingInitializationContext(environment);
        String logConfig = environment.getProperty("logging.config");
        if (this.ignoreLogConfig(logConfig)) {
            system.initialize(initializationContext, (String)null, logFile);
        } else {
            try {
                system.cleanUp();
                ResourceUtils.getURL(logConfig).openStream().close();
                system.initialize(initializationContext, logConfig, logFile);
            } catch (Exception var7) {
                System.err.println("Logging system failed to initialize using configuration from '" + logConfig + "'");
                var7.printStackTrace(System.err);
                throw new IllegalStateException(var7);
            }
        }

    }

    private boolean ignoreLogConfig(String logConfig) {
        return !StringUtils.hasLength(logConfig) || logConfig.startsWith("-D");
    }

    private void initializeFinalLoggingLevels(ConfigurableEnvironment environment, LoggingSystem system) {
        if (this.springBootLogging != null) {
            this.initializeLogLevel(system, this.springBootLogging);
        }

        this.setLogLevels(system, environment);
    }

    protected void initializeLogLevel(LoggingSystem system, LogLevel level) {
        List<String> loggers = (List)LOG_LEVEL_LOGGERS.get(level);
        if (loggers != null) {
            Iterator var4 = loggers.iterator();

            while(var4.hasNext()) {
                String logger = (String)var4.next();
                system.setLogLevel(logger, level);
            }
        }

    }

    protected void setLogLevels(LoggingSystem system, Environment environment) {
        Map<String, Object> levels = (new RelaxedPropertyResolver(environment)).getSubProperties("logging.level.");
        boolean rootProcessed = false;
        Iterator var5 = levels.entrySet().iterator();

        while(true) {
            Entry entry;
            String name;
            while(true) {
                if (!var5.hasNext()) {
                    return;
                }

                entry = (Entry)var5.next();
                name = (String)entry.getKey();
                if (!name.equalsIgnoreCase("ROOT")) {
                    break;
                }

                if (!rootProcessed) {
                    name = null;
                    rootProcessed = true;
                    break;
                }
            }

            this.setLogLevel(system, environment, name, entry.getValue().toString());
        }
    }

    private void setLogLevel(LoggingSystem system, Environment environment, String name, String level) {
        try {
            level = environment.resolvePlaceholders(level);
            system.setLogLevel(name, this.coerceLogLevel(level));
        } catch (RuntimeException var6) {
            this.logger.error("Cannot set level: " + level + " for '" + name + "'");
        }

    }

    private LogLevel coerceLogLevel(String level) {
        return "false".equalsIgnoreCase(level) ? LogLevel.OFF : LogLevel.valueOf(level.toUpperCase(Locale.ENGLISH));
    }

    private void registerShutdownHookIfNecessary(Environment environment, LoggingSystem loggingSystem) {
        boolean registerShutdownHook = ((Boolean)(new RelaxedPropertyResolver(environment)).getProperty("logging.register-shutdown-hook", Boolean.class, false)).booleanValue();
        if (registerShutdownHook) {
            Runnable shutdownHandler = loggingSystem.getShutdownHandler();
            if (shutdownHandler != null && shutdownHookRegistered.compareAndSet(false, true)) {
                this.registerShutdownHook(new Thread(shutdownHandler));
            }
        }

    }

    void registerShutdownHook(Thread shutdownHook) {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

    public void setSpringBootLogging(LogLevel springBootLogging) {
        this.springBootLogging = springBootLogging;
    }

    public void setParseArgs(boolean parseArgs) {
        this.parseArgs = parseArgs;
    }

    static {
        LOG_LEVEL_LOGGERS.add(LogLevel.DEBUG, "org.springframework.boot");
        LOG_LEVEL_LOGGERS.add(LogLevel.TRACE, "org.springframework");
        LOG_LEVEL_LOGGERS.add(LogLevel.TRACE, "org.apache.tomcat");
        LOG_LEVEL_LOGGERS.add(LogLevel.TRACE, "org.apache.catalina");
        LOG_LEVEL_LOGGERS.add(LogLevel.TRACE, "org.eclipse.jetty");
        LOG_LEVEL_LOGGERS.add(LogLevel.TRACE, "org.hibernate.tool.hbm2ddl");
        LOG_LEVEL_LOGGERS.add(LogLevel.DEBUG, "org.hibernate.SQL");
        EVENT_TYPES = new Class[]{ApplicationStartingEvent.class, ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class, ContextClosedEvent.class, ApplicationFailedEvent.class};
        SOURCE_TYPES = new Class[]{SpringApplication.class, ApplicationContext.class};
    }
}
