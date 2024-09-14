package com.jekirdek.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerMessageUtil {

    private LoggerMessageUtil() {
    }

    private static final Logger logger = LoggerFactory.getLogger(LoggerMessageUtil.class);

    public static void logInfo(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    public static void logError(String message, Throwable throwable) {
        if (logger.isErrorEnabled()) {
            logger.error(message, throwable);
        }
    }

    public static String buildMessage(String... parts) {
        StringBuilder message = new StringBuilder();
        for (String part : parts) {
            message.append(part);
        }
        return message.toString();
    }

    public static String buildMessageWithArgs(String message, Object... args) {
        StringBuilder result = new StringBuilder();
        int argIndex = 0;

        for (int i = 0; i < message.length(); i++) {
            if (i < message.length() - 1 && message.charAt(i) == '{' && message.charAt(i + 1) == '}') {
                if (argIndex < args.length) {
                    result.append(args[argIndex++]);
                }
                i++;
            } else {
                result.append(message.charAt(i));
            }
        }
        return result.toString();
    }
}
