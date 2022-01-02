package io.springbootstarter.response;//package mx.com.walmart.cam.batch.annotation;


import io.springbootstarter.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
                content = {@Content(schema = @Schema(implementation = Response.class))}),
        @ApiResponse(responseCode = "400", description = SwaggerConstants.DESCRIPTION_400,
                content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "401", description = SwaggerConstants.DESCRIPTION_401,
                content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "403", description = SwaggerConstants.DESCRIPTION_403,
                content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "404", description = SwaggerConstants.DESCRIPTION_404,
                content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "500", description = SwaggerConstants.DESCRIPTION_500,
                content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
        @ApiResponse(responseCode = "503", description = SwaggerConstants.DESCRIPTION_503,
                content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})}
)
public @interface GlobalApiResponses {
}
