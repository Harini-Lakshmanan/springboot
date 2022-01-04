package io.springbootstarter.service;

import io.springbootstarter.model.Topic;
import io.springbootstarter.repository.TopicRepository;
import io.springbootstarter.request.TopicRequest;
import io.springbootstarter.response.TopicResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicService{

    private final TopicRepository topicRepository;

    private List<Topic> topicList = new ArrayList<>(Arrays.asList(
        new Topic("spring", "Spring framework", "Spring description"),
        new Topic("Java", "Java framework", "Java description"),
        new Topic("Javascript", "Javascript framework", "Javascript description")));

    public List<TopicResponse> getAllTopics(){
        List<Topic> topicList = new ArrayList<>();
        List<TopicResponse> topicResponses = new ArrayList<>();
        topicRepository.findAll().forEach(topic -> {
            TopicResponse topicResponse = new TopicResponse();
            topicResponse.setId(topic.getId());
            topicResponse.setName(topic.getName());
            topicResponse.setDescription(topic.getDescription());
            topicResponses.add(topicResponse);
            return;
        });
        return  topicResponses;
    }

    public TopicResponse getTopicById(String id){
        Topic byId = topicRepository.getById(id);
        TopicResponse topicResponse = new TopicResponse();
        topicResponse.setDescription(byId.getDescription());
        topicResponse.setId(byId.getId());
        topicResponse.setName(byId.getName());
        return topicResponse;
    }

    public void addTopic(TopicRequest topicRequest) {
        Topic topic = new Topic();
        topic.setId(topicRequest.getId());
        topic.setName(topicRequest.getName());
        topic.setDescription(topicRequest.getDescription());
        topicRepository.save(topic);
    }

    public void updateTopic(TopicRequest topicRequest, String id) {
        Topic topic = topicRepository.getById(id);
        if (topic != null && topic.getId().equalsIgnoreCase(topicRequest.getId())){
            topic.setDescription(topicRequest.getDescription());
            topic.setName(topicRequest.getName());
            topic.setId(topicRequest.getId());
            topicRepository.save(topic);
        }
    }

    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }
}
