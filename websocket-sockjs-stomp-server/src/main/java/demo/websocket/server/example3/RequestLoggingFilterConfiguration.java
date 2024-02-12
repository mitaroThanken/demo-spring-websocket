package demo.websocket.server.example3;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfiguration {
    @Bean
    FilterRegistrationBean<?> loggingRequestFilter() {
        final var filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        final var bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE + 1);
        return bean;
    }

    @Bean
    FilterRegistrationBean<?> loggingResponseFilter() {
        final var filter = new ResponseLoggingFilter();
        final var bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        return bean;
    }
}