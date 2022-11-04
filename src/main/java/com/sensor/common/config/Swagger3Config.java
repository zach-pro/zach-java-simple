package com.sensor.common.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author apple
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Swagger3",
                version = "1.0",
                description = "Swagger接口文档",
                contact = @Contact(name = "zach")
        ),
   //     security = @SecurityRequirement(name = "JWT"),
        externalDocs = @ExternalDocumentation(description = "参考文档",
                url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"
        )
)
// @SecurityScheme(type = SecuritySchemeType.HTTP, name = "JWT", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class Swagger3Config {}