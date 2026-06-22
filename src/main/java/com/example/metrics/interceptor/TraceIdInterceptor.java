package com.example.metrics.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    private static final Logger log =
            LoggerFactory.getLogger(TraceIdInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String traceId = UUID.randomUUID().toString();

        request.setAttribute("traceId", traceId);
        request.setAttribute("startTime", System.currentTimeMillis()); // FIXED
        response.setHeader("X-Trace-Id", traceId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {

        Object startObj = request.getAttribute("startTime");

        long executionTime = 0;
        if (startObj instanceof Long startTime) {
            executionTime = System.currentTimeMillis() - startTime;
        }

        log.info(
                "Request Completed : {} | Status={} | Time={} ms | TraceId={}",
                request.getRequestURI(),
                response.getStatus(),
                executionTime,
                request.getAttribute("traceId")
        );

        if (ex != null) {
            log.error("Request Failed", ex);
        }
    }
}