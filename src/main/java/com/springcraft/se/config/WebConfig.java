package com.springcraft.se.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer
                .useRequestHeader("X-API-Version") // Header-based
                //.useQueryParam("version") // Query parameter-based
                //.useMediaTypeParameter(MediaType.APPLICATION_JSON, "version") // Media type
                //.usePathSegment(1) // Index of the path segment containing version
                .addSupportedVersions("1.0", "2.0", "3.0")
                .setDefaultVersion("1.0")
                .setVersionParser(new ApiVersionParser());
    }
}
