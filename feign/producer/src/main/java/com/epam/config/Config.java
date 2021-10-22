package com.epam.config;

import com.epam.utli.*;
import feign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.*;

//@Configuration //Header transit
public class Config {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new ProxyInterceptor();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setThreadContextInheritable(true);
        return dispatcherServlet;
    }

    static class ProxyInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {
            RequestHelper.getHeaderFromCurrentRequest().forEach(template::header);
        }
    }

}
