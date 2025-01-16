package com.bytedance.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI swaggerOpenApi() {
        return new OpenAPI()
                .info(new Info().title("抖音商城 API")
                        .description("抖音商城(后端复刻版)")
                        .version("v1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("项目地址")
                        .url("https://github.com/CooperationPracticeProject/cantera"));
    }
}

