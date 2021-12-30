package io.springbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private  String description;
}
