package io.springbootstarter.controller;

import io.springbootstarter.request.CourseRequest;
import io.springbootstarter.request.TopicRequest;
import io.springbootstarter.response.CourseResponse;
import io.springbootstarter.response.GlobalApiResponses;
import io.springbootstarter.response.Response;
import io.springbootstarter.response.TopicResponse;
import io.springbootstarter.service.CourseService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
@Tag(name="Courses")
@GlobalApiResponses
public class CourseController {

    private final CourseService courseService;

    @GetMapping(value = "/topics/{id}/courses",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Get All Courses",
    content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = CourseResponse.class))))
    public Response<List<CourseResponse>> getAllCourses(@PathVariable String id){
        List<CourseResponse> courseResponses = courseService.getAllCourses(id);
        Response response = new Response();
        response.setData(courseResponses);
        return response;
    }

    @GetMapping(value = "topic/{topicId}/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Get Course by Id",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CourseResponse.class))))
    public Response<CourseResponse> getCourseById(@PathVariable String id){
        CourseResponse courseResponse = courseService.getCourseById(id);
        Response response = new Response();
        response.setData(courseResponse);
        return response;
    }

    @PostMapping(value = "/topics/{topicId}/courses",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Add a new topic",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = void.class))))
    public void addCourse(@RequestBody CourseRequest courseRequest,@PathVariable String topicId){
        courseService.addTopic(courseRequest,topicId);
    }

    @PutMapping(value = "/topics/{topicId}/courses/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Update a topic",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = void.class))))
    public void updateTopic(@RequestBody CourseRequest courseRequest,@PathVariable String topicId, @PathVariable String id){
        courseService.updateCourse(courseRequest,topicId,id);
    }

    @DeleteMapping(value = "/topics/{topicId}/courses/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Delete a topic",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = void.class))))
    public void deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
    }

}
