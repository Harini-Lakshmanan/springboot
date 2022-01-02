package io.springbootstarter.response;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class Error implements Serializable {

    private String errorCode;
    private String errorMessage;

    public Error(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}

