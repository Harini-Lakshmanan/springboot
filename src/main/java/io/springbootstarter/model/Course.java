package io.springbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Course")
public class Course {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private  String description;
    @ManyToOne
    private Topic topic;
}
