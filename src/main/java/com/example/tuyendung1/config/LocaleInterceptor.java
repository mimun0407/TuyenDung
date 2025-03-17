package com.example.tuyendung1.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String lang = request.getParameter("lang");
        if (lang != null && !lang.isEmpty()) {
            Locale locale = Locale.forLanguageTag(lang);
            LocaleContextHolder.setLocale(locale);
            response.setHeader("Content-Language", locale.toLanguageTag()); // Gửi lại header để kiểm tra
        }
        return true;
    }
}