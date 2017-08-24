package com.cn.idataitech.inuyasha.model.page;

import com.cn.idataitech.inuyasha.model.bean.Constant;
import com.cn.idataitech.inuyasha.model.error.LoginPage;
import com.cn.idataitech.inuyasha.model.error.SetLoginPageEvent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminHtml implements LoginPage, ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    @Qualifier("eventPublisher")
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping("/admin/login.html")
    public String login() {
        if (SecurityUtils.getSubject().hasRole(Constant.ROLE_ADMIN)) {
            return "redirect:/admin/index.html";
        }
        return "/admin/login";
    }

    @RequestMapping({"/admin", "/admin/", "/admin/**/*.html"})
    @RequiresRoles(Constant.ROLE_ADMIN)
    public String admin() {
        return "/admin/index";
    }

    @Override
    public String getPattern() {
        return "\\/admin(\\/.*)?";
    }

    @Override
    public Object login(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/admin/login.html";
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        eventPublisher.publishEvent(new SetLoginPageEvent(this));
    }


}
