package hola.models;


import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@ToString
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )

    private Long id;
    @Column(name = "course_name")
    private String courseName;
    private String duration;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(mappedBy = "courses", cascade = {REMOVE})
    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course",
            cascade = {REMOVE},
            orphanRemoval = true)
    private Teacher teacher;


    public Course() {
    }

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public Course(Company company) {
        this.company = company;
    }

    public Course(List<Group> groups) {
        this.groups = groups;
    }

    public Course(Teacher teacher) {
        this.teacher = teacher;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}


