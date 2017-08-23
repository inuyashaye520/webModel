package com.cn.idataitech.inuyasha.model.error;

import org.springframework.context.ApplicationEvent;

/**
 * SetLoginPageEvent
 */
public class SetLoginPageEvent extends ApplicationEvent {

    public SetLoginPageEvent(LoginPage loginPage) {
        super(loginPage);
    }

    public LoginPage getSource() {
        return (LoginPage) super.getSource();
    }

}
