package com.shop.soap_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_XML)
                .favorParameter(true)
                .parameterName("format")
                .ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(true)
                .mediaType("xml", MediaType.APPLICATION_XML);


    }
}

