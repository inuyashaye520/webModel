package com.cn.idataitech.inuyasha.model.error;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login page
 */
public interface LoginPage {

    String getPattern();

    Object login(HttpServletRequest request, HttpServletResponse response, Model model);

}
