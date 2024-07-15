package luckyhunter.university.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("LoggingFilter initialized...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.debug("Request received: {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());

        try {
            chain.doFilter(request, response);
        } finally {
            log.debug("Response sent to: {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());
        }
    }

    @Override
    public void destroy() {
    }
}

//        log.debug("Request received: {} {}", request.getRemoteAddr(), request.getRemotePort());
//        chain.doFilter(request, response);
//        log.debug("Response sent to: {} {}", request.getRemoteAddr(), request.getRemotePort());