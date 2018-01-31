package com.mdc.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;

@ComponentScan({
        "com.mdc.web.controller"
})
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    public void setThymeleafViewResolver(ThymeleafViewResolver thymeleafViewResolver) {
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("html", MediaType.TEXT_HTML);
        mediaTypes.put ("xml", MediaType.APPLICATION_XML);
        mediaTypes.put ("json", MediaType.APPLICATION_JSON);
        configurer
                .ignoreAcceptHeader(true)
                .favorParameter(true)
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaTypes(mediaTypes);
    }

    /**
     * Static resource handler where we store the css or js and images
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCES_HANDLER)
                .addResourceLocations(RESOURCES_LOCATION)
                .setCachePeriod(0);
    }

    /**
     * Register custom url to a view.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login/form")
                .setViewName("login");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver);
    }


}
