package com.mdc.web.configuration;

import com.mdc.configuration.JavaConf;
import com.mdc.configuration.JdbcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{JavaConf.class, JdbcConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class, SecurityConfig.class, ThymeleafConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
