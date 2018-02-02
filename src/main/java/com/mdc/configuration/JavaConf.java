package com.mdc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.mdc.authentication",
        "com.mdc.services"
})
public class JavaConf {
}
