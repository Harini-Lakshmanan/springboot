package io.springbootstarter.service;

import io.springbootstarter.model.Course;
import io.springbootstarter.model.Topic;
import io.springbootstarter.repository.CourseRepository;
import io.springbootstarter.repository.TopicRepository;
import io.springbootstarter.request.CourseRequest;
import io.springbootstarter.request.TopicRequest;
import io.springbootstarter.response.CourseResponse;
import io.springbootstarter.response.TopicResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;

    private List<Topic> topicList = new ArrayList<>(Arrays.asList(
        new Topic("spring", "Spring framework", "Spring description"),
        new Topic("Java", "Java framework", "Java description"),
        new Topic("Javascript", "Javascript framework", "Javascript description")));

    public List<CourseResponse> getAllCourses(String id){
        List<TopicResponse> topicResponses = new ArrayList<>();
        List<CourseResponse> courseResponses = new ArrayList<>();

        Optional<Topic> byId = topicRepository.findById(id);
        TopicResponse topicResponse = new TopicResponse();
        topicResponse.setName(byId.get().getName());
        topicResponse.setDescription(byId.get().getDescription());
        topicResponse.setId(byId.get().getId());
        topicResponses.add(topicResponse);

        courseRepository.findByTopicId(id).forEach(course -> {
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setId(course.getId());
            courseResponse.setName(course.getName());
            courseResponse.setDescription(course.getDescription());
            courseResponse.setTopicResponse(topicResponses);
            courseResponses.add(courseResponse);
            return;
        });

        return courseResponses;
    }

    public CourseResponse getCourseById(String id){
        Course byId = courseRepository.getById(id);
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setDescription(byId.getDescription());
        courseResponse.setId(byId.getId());
        courseResponse.setName(byId.getName());
        return courseResponse;
    }

    public void addTopic(CourseRequest courseRequest,String topicId) {
        Optional<Topic> byId = topicRepository.findById(topicId);
        Course course = new Course();
        course.setTopic(byId.get());
        course.setDescription(courseRequest.getDescription());
        course.setName(courseRequest.getName());
        course.setId(courseRequest.getId());
        courseRepository.save(course);
    }

    public void updateCourse(CourseRequest courseRequest,String topicId, String id) {
        Topic topic = topicRepository.getById(topicId);
        Course course = courseRepository.getById(id);
        if (course != null && course.getId().equalsIgnoreCase(courseRequest.getId())){
            course.setDescription(courseRequest.getDescription());
            course.setName(courseRequest.getName());
            course.setId(courseRequest.getId());
            course.setTopic(topic);
            courseRepository.save(course);
        }
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
