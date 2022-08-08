package com.hzh.app.config.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class HzhLineOfCallerConverter extends ClassicConverter {

    public String convert(ILoggingEvent le) {
        StackTraceElement[] cda = le.getCallerData();

        String line = cda != null && cda.length > 0 ? Integer.toString(cda[0].getLineNumber()) : CallerData.NA;
        //System.out.println("line=" + line);

        return line;
    }

}
