package com.cn.idataitech.inuyasha.model.config;

import com.cn.idataitech.inuyasha.model.session.shiroTag.ShiroTags;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;

@Configuration
public class FreemarkerConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration implements ServletContextAware {

    private String contextPath;

    @Autowired
    private freemarker.template.Configuration configuration;
    private FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        configurer.setPostTemplateLoaders(new ClassTemplateLoader(FreemarkerConfig.class, "/templates/"));
        applyProperties(configurer);
        return configurer;
    }

    public void setServletContext(ServletContext context) {
        contextPath = context.getContextPath();

        configuration.setSharedVariable("shiro", new ShiroTags());
        try {
            configuration.setSharedVariable("ContextPath", contextPath);
            configuration.setSharedVariable("AssetsPath", contextPath + "/assets");
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }
}
