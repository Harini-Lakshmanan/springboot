package io.springbootstarter.controller;

import io.springbootstarter.factory.TopicFactory;
import io.springbootstarter.model.Topic;
import io.springbootstarter.request.TopicRequest;
import io.springbootstarter.response.GlobalApiResponses;
import io.springbootstarter.response.Response;
import io.springbootstarter.response.TopicResponse;
import io.springbootstarter.service.TopicService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/topics")
@Tag(name="Topics")
@GlobalApiResponses
public class TopicController {

    private final TopicService topicService;

    @GetMapping(value = "/getTopics",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Get All Topics",
    content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = TopicResponse.class))))
    public Response<List<TopicResponse>> getAllTopics(){
        List<TopicResponse> allTopics = topicService.getAllTopics();
        Response response = new Response();
        response.setData(allTopics);
        return response;
    }

    @GetMapping(value = "/getTopics/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Get topic by Id",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TopicResponse.class))))
    public Response<TopicResponse> getTopicById(@PathVariable String id){
        TopicResponse topicById = topicService.getTopicById(id);
        Response response = new Response();
        response.setData(topicById);
        return response;
    }

    @PostMapping(value = "/addTopic",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Add a new topic",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = void.class))))
    public void insertTopic(@RequestBody TopicRequest topicRequest){
        topicService.addTopic(topicRequest);
    }

    @PutMapping(value = "/updateTopic/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Update a topic",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = void.class))))
    public void updateTopic(@RequestBody TopicRequest topicRequest,@PathVariable String id){
        topicService.updateTopic(topicRequest,id);
    }

    @DeleteMapping(value = "/deleteTopic/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Delete a topic",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = void.class))))
    public void deleteTopic(@PathVariable String id){
        topicService.deleteTopic(id);
    }

}
