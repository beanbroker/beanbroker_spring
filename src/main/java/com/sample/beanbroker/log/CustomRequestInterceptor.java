package com.sample.beanbroker.log;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        long startTime = Instant.now().toEpochMilli();
        log.info("Request URL::" + request.getRequestURL().toString() +" Method::" +request.getMethod() +
                ":: Start Time=" + Instant.now());

        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        requestCacheWrapperObject.getParameterMap();


//        String a = requestCacheWrapperObject.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        log.debug("sdgfsdgdsfg "  + a);
//        Map<String, String[]> paramMap = request.getParameterMap();
//
//        for (String key : paramMap.keySet()) {
//            StringBuilder paramValue = new StringBuilder();
//            String[] paramValues = paramMap.get(key);
//            if (paramValues.length > 0) {
//                for (String param : paramValues) {
//                    paramValue.append(param).append(",");
//                }
//                paramValue.deleteCharAt(paramValue.length() - 1);
//            }
//            log.debug("|  {}: {}", key, paramValue);
//        }





        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        long startTime = (Long) request.getAttribute("startTime");


        HttpStatus status = HttpStatus.valueOf(response.getStatus());

        log.info("Request URL::" + request.getRequestURL().toString() +
                " HttpStatus::" + status.getReasonPhrase() +
                " HttpStatusCode::" + status.value() +
                ":: Time Taken=" + (Instant.now().toEpochMilli() - startTime));
    }


    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setMaxPayloadLength(10000);
        return loggingFilter;
    }

}
