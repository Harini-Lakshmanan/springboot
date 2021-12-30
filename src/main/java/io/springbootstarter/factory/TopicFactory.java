package io.springbootstarter.factory;

import io.springbootstarter.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicFactory {

    public static List<Topic> getListOfTopics() {
        List<Topic> topicList = new ArrayList<>();
        topicList.add(new Topic("spring", "Spring framework", "Spring description"));
        topicList.add(new Topic("Java", "Java framework", "Java description"));
        topicList.add(new Topic("Javascript", "Javascript framework", "Javascript description"));
        return topicList;
    }
}
