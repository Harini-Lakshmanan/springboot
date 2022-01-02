package io.springbootstarter.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String generalError;
    private boolean isValidationError = false;
    private Map<String, String> validationErrors;

    public ErrorResponse(String generalError) {
        this.generalError = generalError;
        this.validationErrors = null;
    }
    
    public ErrorResponse(Map<String, String> validationErrors) {
        this.isValidationError = true;
        this.validationErrors = validationErrors;
    }

    public String getGeneralError() {
        return generalError;
    }

    public void setGeneralError(String generalError) {
        this.generalError = generalError;
    }

    public boolean isValidationError() {
        return isValidationError;
    }

    public void setValidationError(boolean validationError) {
        isValidationError = validationError;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}

