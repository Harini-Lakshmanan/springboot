package io.springbootstarter.repository;

import io.springbootstarter.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    public List<Course> findByTopicId(String topic);
}
