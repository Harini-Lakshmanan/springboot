package cam.walmart.payment.controllers.configuration;

import cam.walmart.payment.annotation.GlobalApiResponses;
import cam.walmart.payment.constants.SwaggerConstants;
import cam.walmart.payment.exception.CamException;
import cam.walmart.payment.model.request.*;
import cam.walmart.payment.model.response.CountryResponse;
import cam.walmart.payment.model.response.Error;
import cam.walmart.payment.model.response.Response;
import cam.walmart.payment.model.response.SAPCountryResponse;
import cam.walmart.payment.service.configuration.SAPCountryService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/configuration/sap")
@Tag(name = "SAP Country Configuration")
@Slf4j
@GlobalApiResponses
@PreAuthorize("hasPermission()")
public class SAPCountryController {

    private final SAPCountryService sapCountryService;

    @GetMapping(value = "/countries")
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<List<CountryResponse>> countries() {
        Response<List<CountryResponse>> response = new Response<>();
        response.setData(sapCountryService.getAllCountries());
        return response;
    }

    @GetMapping(value = "/country/details/{countryCode}")
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<SAPCountryResponse> countryDetails(@PathVariable("countryCode") String countryCode) {
        Response<SAPCountryResponse> response = new Response<>();
        response.setData(sapCountryService.getCountryDetails(countryCode));
        return response;
    }

    @GetMapping(value = "/country/tax/{countryCode}")
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<Double> countryTax(@PathVariable("countryCode") String countryCode) {
        Response<Double> response = new Response<>();
        response.setData(sapCountryService.getCountryTax(countryCode));
        return response;
    }

    @PutMapping(value = "/updateSAPCountryTax", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<Void> updateSAPCountryTaxDetails(@RequestBody @Valid SAPCountryTaxRequest sapCountryTaxRequest) {
        Response<Void> response = new Response<>();
        try {
            sapCountryService.updateSAPCountryTax(sapCountryTaxRequest);
            log.info("Successfully updated SAP Country Tax details");
        } catch (CamException camException) {
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(new Error(camException.getCode(), camException.getMessage())));
        }
        return response;
    }

    @PutMapping(value = "/updateSAPCountryTaxExemptionDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<Void> updateSAPCountryTaxExemptionDetails(@RequestBody @Valid UpdateSAPCountryExemptionRequest updateSapCountryExemptionRequest) {
        Response<Void> response = new Response<>();
        try {
            sapCountryService.updateSAPCountryTaxExemptionDetails(updateSapCountryExemptionRequest);
            log.info("Successfully updated SAP Country Tax Exemption Details");
        } catch (CamException camException) {
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(new Error(camException.getCode(), camException.getMessage())));
        }
        return response;
    }

    @PutMapping(value = "/updateSAPCountryTaxCodeDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<Void> updateSAPCountryTaxCodeDetails(@RequestBody @Valid UpdateSAPCountryTaxCodeRequest updateSAPCountryTaxCodeRequest) {
        Response<Void> response = new Response<>();
        try {
            sapCountryService.updateSAPCountryTaxCodeDetails(updateSAPCountryTaxCodeRequest);
            log.info("Successfully updated SAP Country Tax Code Details");
        } catch (CamException camException) {
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(new Error(camException.getCode(), camException.getMessage())));
        }
        return response;
    }


    @PostMapping(value = "/saveParameter", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<Void> saveSAPCountryParameter(@RequestBody @Valid SAPCountryParameterRequest sapCountryParameterRequest) {
        log.info("Request reached saveSAPCountryParameter endpoint");
        Response<Void> response = new Response<>();
        try {
            sapCountryService.saveSAPCountryParameter(sapCountryParameterRequest);
            log.info("Successfully saved SAP Country paramter  details ");
        } catch (CamException camException) {
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(new cam.walmart.payment.model.response.Error(camException.getCode(), camException.getMessage())));
        }
        return response;


    }

    @PutMapping(value = "/updateParameter", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200,
            content = @Content(schema = @Schema()))
    public Response<Void> updateSAPCountryParameter(@RequestBody @Valid UpdateSAPCountryParameterRequest updateSAPCountryParameterRequest) {
        log.info("Request reached updateSAPCountryParameter endpoint");
        Response<Void> response = new Response<>();
        try {
            sapCountryService.updateSAPCountryParameter(updateSAPCountryParameterRequest);
            log.info("Successfully updated SAP Country paramter  details ");
        } catch (CamException camException) {
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(new cam.walmart.payment.model.response.Error(camException.getCode(), camException.getMessage())));
        }
        return response;
    }

    @DeleteMapping(value = "/deleteParameter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = SwaggerConstants.DESCRIPTION_200, content = @Content(schema = @Schema()))

    public Response<Void> deleteSAPCountryParameter(@RequestParam("paramterName") String parameterName, @RequestParam("countryCode") String countryCode) {

        log.info("Request reached deleteSAPCountryParameter endpoint");
        Response<Void> response = new Response<>();
        try {
            sapCountryService.deleteSAPCountryParameter(parameterName, countryCode);
            log.info("Successfully deleted Data package details ");
        } catch (CamException camException) {
            response.setSuccess(false);
            response.setErrors(Collections.singletonList(new cam.walmart.payment.model.response.Error(camException.getCode(), camException.getMessage())));
        }
        return response;

    }
}
