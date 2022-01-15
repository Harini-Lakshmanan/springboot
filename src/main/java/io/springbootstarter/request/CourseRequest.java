package io.springbootstarter.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CourseRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private  String description;
}
