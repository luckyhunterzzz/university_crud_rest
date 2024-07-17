package luckyhunter.university.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Фильтр для логирования HTTP запросов и ответов.
 */
@Slf4j
@WebFilter("/*")
public class LoggingFilter implements Filter {

    /**
     * Инициализация фильтра. Вызывается при старте фильтра.
     *
     * @param filterConfig Конфигурация фильтра.
     * @throws ServletException Возникает при ошибке инициализации фильтра.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("LoggingFilter initialized...");
    }

    /**
     * Метод для обработки HTTP запросов и ответов.
     *
     * @param request  HTTP запрос.
     * @param response HTTP ответ.
     * @param chain    Цепочка фильтров, через которую передается запрос.
     * @throws IOException      Возникает при ошибке ввода/вывода.
     * @throws ServletException Возникает при ошибке сервлета.
     */
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

    /**
     * Метод для завершения работы фильтра. Вызывается при завершении работы фильтра.
     */
    @Override
    public void destroy() {
    }
}