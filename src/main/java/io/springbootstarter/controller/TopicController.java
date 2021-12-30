package io.springbootstarter.controller;

import io.springbootstarter.factory.TopicFactory;
import io.springbootstarter.model.Topic;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
@Tag(name="Topics")
public class TopicController {

    @GetMapping(value = "/getTopics",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> getAllTopics(){
        return TopicFactory.getListOfTopics();
    }
}
