package ru.savosin.databasewebapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest request, Exception ex, HttpServletResponse response) throws IOException {
        logger.error("Request: " + request.getRequestURL() + " raised " + ex);
        response.sendRedirect(request.getRequestURL().toString());
    }
}
