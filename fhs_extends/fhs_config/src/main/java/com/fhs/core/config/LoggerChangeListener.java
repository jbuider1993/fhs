package com.fhs.core.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Set;

/**
 * apollo 日志级别更变监听
 */
@Component
public class LoggerChangeListener {

    private Logger logger = LoggerFactory.getLogger(LoggerChangeListener.class);

    private static final String LOGGER_TAG = "logging.level";

    @ApolloConfig("application")
    private Config config;

    @ApolloConfigChangeListener
    private void onChange(ConfigChangeEvent changeEvent) {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            ConfigChange change = changeEvent.getChange(key);
            if(change != null){
                logger.info(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                if (key.startsWith(LOGGER_TAG)) {
                    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                    // change log level for global
                    loggerContext.getLogger(key.replace(LOGGER_TAG + ".","")).setLevel(Level.valueOf(change.getNewValue()));
                }
            }
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
}
