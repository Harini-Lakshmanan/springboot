package io.springbootstarter.factory;

import io.springbootstarter.model.Topic;
import io.springbootstarter.request.TopicRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicFactory {

    public static List<Topic> getListOfTopics() {
        List<Topic> topicList = new ArrayList<>();
        topicList.add(new Topic("spring", "Spring framework", "Spring description"));
        topicList.add(new Topic("Java", "Java framework", "Java description"));
        topicList.add(new Topic("Javascript", "Javascript framework", "Javascript description"));
        return topicList;
    }

    public static Topic getTopicById(String id) {
        List<Topic> listOfTopics = getListOfTopics();
        Topic topic = listOfTopics.stream().filter(topic1 -> topic1.getId().equals(id)).findFirst().get();
        return topic;
    }
}
