package java_jdk.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by wb-zj373670 on 2018/8/8.
 */
public class LogTest {
    public static void main(String[] args) {
        String loggerName = "java_jdk.log.LogTest";
        String pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}][%level][%thread] %logger{20} - %m%n";
        Logger logger1 = LoggerFactory.getLogger(loggerName + ".main");
        Logger logger = LoggerFactory.getLogger(loggerName);
        if (logger instanceof ch.qos.logback.classic.Logger) {
            String logFile = "D:\\logFile";

            RollingFileAppender appender = new RollingFileAppender();

            final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            File file = new File(logFile);
            appender.setFile(file.getAbsolutePath());

            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(context);
            encoder.setCharset(Charset.forName("GBK"));
            encoder.setPattern(pattern);
            encoder.start();

            TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
            policy.setFileNamePattern(logFile + ".%d{yyyy-MM-dd}");
            policy.setCleanHistoryOnStart(true);
            policy.setContext(context);
            policy.setMaxHistory(7);
            policy.setParent(appender);
            policy.start();

            appender.setRollingPolicy(policy);
            appender.setContext(context);
            appender.setEncoder(encoder);
            appender.start();

            ((ch.qos.logback.classic.Logger) logger).setAdditive(false);
            ((ch.qos.logback.classic.Logger) logger).setLevel(Level.toLevel("DEBUG"));

            ((ch.qos.logback.classic.Logger) logger).detachAndStopAllAppenders();
            ((ch.qos.logback.classic.Logger) logger).addAppender(appender);

            logger.warn("Example");
            logger1.warn("hh");
            System.out.println("z");
        }
    }

}
