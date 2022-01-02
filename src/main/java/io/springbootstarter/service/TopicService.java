package io.springbootstarter.service;

import io.springbootstarter.model.Topic;
import io.springbootstarter.request.TopicRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService{

    private List<Topic> topicList = new ArrayList<>(Arrays.asList(
        new Topic("spring", "Spring framework", "Spring description"),
        new Topic("Java", "Java framework", "Java description"),
        new Topic("Javascript", "Javascript framework", "Javascript description")));

    public List<Topic> getAllTopics(){
        return topicList;
    }

    public Topic getTopicById(String id){
        return topicList.stream().filter(topic -> topic.getId().equalsIgnoreCase(id)).findFirst().get();
    }

    public void addTopic(TopicRequest topicRequest) {
        Topic topic = new Topic();
        topic.setId(topicRequest.getId());
        topic.setName(topicRequest.getName());
        topic.setDescription(topicRequest.getDescription());
        topicList.add(topic);
    }

    public void updateTopic(TopicRequest topicRequest, String id) {
        List<Topic> collect = topicList.stream().filter(topic -> {
            if (topic.getId().equalsIgnoreCase(id)) {
                topicList.set(topicList.indexOf(0), topic);
            }
            return true;
        }).collect(Collectors.toList());

    }

    public void deleteTopic(String id) {
        topicList.removeIf(topic -> topic.getId().equalsIgnoreCase(id));
    }
}
