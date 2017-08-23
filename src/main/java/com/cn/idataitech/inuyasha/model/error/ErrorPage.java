package com.cn.idataitech.inuyasha.model.error;

import com.cn.idataitech.inuyasha.model.bean.JsonResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Error page
 */
@Controller("errorPage")
@ControllerAdvice
public class ErrorPage implements ErrorController {

    private List<LoginPage> loginPages = new ArrayList<>();

    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response, Model model) {
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String uri = ((String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)).substring(request.getContextPath().length());
        Integer errorCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (errorCode != null && errorMessage == null) {
            errorMessage = HttpStatus.valueOf(errorCode).name();
        }

        return handle(request, response, exception, model, uri, errorCode, errorMessage);
    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, HttpServletResponse response, Model model) {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        if (uri.startsWith("/api/")) {
            return new ResponseEntity<>(JSON.toJSONString(JsonResult.error(HttpStatus.UNAUTHORIZED.value())).getBytes(), HttpStatus.OK);
        }

        for (LoginPage loginPage : loginPages) {
            if (uri.matches(loginPage.getPattern())) {
                return loginPage.login(request, response, model);
            }
        }
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, HttpServletResponse response, Exception exception, Model model) {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        Integer errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String errorMessage = exception.getMessage();

        return handle(request, response, exception, model, uri, errorCode, errorMessage);
    }


    private Object handle(HttpServletRequest request, HttpServletResponse response, Exception exception, Model model, String uri, Integer errorCode, String errorMessage) {
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        if (exception != null) {
            model.addAttribute("stackTrace", exception.getStackTrace());
            exception.printStackTrace();
        }


        if (uri.startsWith("/api/")) {
            return new ResponseEntity<>(JSON.toJSONString(JsonResult.error(errorCode, errorMessage)).getBytes(), HttpStatus.OK);
        }

        if (uri.startsWith("/tmpl/")) {
            return "/error_part";
        }
        return "/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @EventListener
    public void setLoginPage(SetLoginPageEvent event) {
        loginPages.add(event.getSource());
    }
}
