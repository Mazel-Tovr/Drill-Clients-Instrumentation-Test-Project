package com.epam.util;

import org.springframework.web.context.request.*;

import javax.servlet.http.*;
import java.util.*;

public class RequestHelper {

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    public static Map<String, String> getHeaderFromCurrentRequest() {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = getCurrentRequest().getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, getCurrentRequest().getHeader(headerName));
        }
        return headers;
    }

}
