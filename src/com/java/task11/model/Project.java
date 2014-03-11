package com.java.task11.model;

import javax.persistence.*;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "project_name", length = 30)
    private String projectName;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "notes", length = 60)
    private String notes;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String note) {
        this.notes = note;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
