package io.springbootstarter.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.springbootstarter.model.Topic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private  String description;

    private List<TopicResponse> topicResponse;
}
