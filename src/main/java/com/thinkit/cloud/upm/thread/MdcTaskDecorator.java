package com.thinkit.cloud.upm.thread;

import java.util.Map;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

public class MdcTaskDecorator implements TaskDecorator {
	private final static String LOG_TRACE_ID = "logTraceId";
	
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> map = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(map);
                String traceId = MDC.get(LOG_TRACE_ID);
                if (traceId  == null) {
                    traceId = UUID.randomUUID().toString();
                    MDC.put(LOG_TRACE_ID, traceId);
                }
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }	
}