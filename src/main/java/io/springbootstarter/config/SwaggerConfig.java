/*
package io.springbootstarter.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private final String moduleName;

    public SwaggerConfig(@Value("${spring.application.name}") String moduleName) {
        this.moduleName = moduleName;
    }

    private Info apiInfo() {
        return new Info().title(StringUtils.capitalize(moduleName))
                .description("CAM")
                .version("1.0")
                .termsOfService("Terms of service");
//                .contact(new Contact().name("Sarys team").url("https://mex-acciones-ui-stage.ngts-int-factory-mex-acciones.k8s.glb.us.walmart.net/en/index").email("NGTS-Int_Factory@walmart.onmicrosoft.com"))
//                .license(new License().name("License of API").url("Api license URL"));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Authorization";

        Server objServer = new Server();
        objServer.setUrl("http://localhost:8080/cam-payment-service");
        Server  objServerDev=new Server();
        objServerDev.setUrl("https://cam-payment-service-dev.cam-upay.k8s.glb.us.walmart.net/cam-payment-service");
//        Server objServerStage=new Server();
//        objServerStage.setUrl("https://mex-sarys-stage.ngts-int-factory-sarys-gmm.k8s.glb.us.walmart.net/mex-sarys");

        List<Server> lstServer = new ArrayList<>();
        lstServer.add(objServer);
       lstServer.add(objServerDev);
//        lstServer.add(objServerStage);
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .in(SecurityScheme.In.HEADER)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT"))
                )
                .servers(lstServer)
                .info(apiInfo());
    }

    @Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> {
            Parameter parameter = new Parameter();
            parameter.setIn("header");
            parameter.setName("language");
            parameter.setSchema(populateLanguageSchema());
            parameter.setRequired(false);
            operation.addParametersItem(parameter);
            return operation;
        };
    }

    private StringSchema populateLanguageSchema() {
        StringSchema stringSchema = new StringSchema();
        stringSchema.setEnum(Arrays.asList("en", "es"));
        return stringSchema;
    }

   */
/* private StringSchema populateRoleIdSchema() {
        StringSchema stringSchema = new StringSchema();
        stringSchema.setEnum(Arrays.asList("0", "1", "2", "3"));
        return stringSchema;
    }

    private StringSchema populateRoleSchema() {
        StringSchema schema = new StringSchema();
        schema.setEnum(Arrays.asList("None", "Administrador  Jr", "Ejecutivo", "Administrador Sr"));
        return schema;
    }

    private String populateActionSchema(String action) {
        return action;
    }*//*

}*/
