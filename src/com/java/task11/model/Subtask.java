package com.java.task11.model;

import javax.persistence.*;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
@Entity
@Table(name = "subtasks")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 30)
    private String subtaskName;

    @Column(name = "description", length = 60)
    private String subtaskDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }

    public String getSubtaskDescription() {
        return subtaskDescription;
    }

    public void setSubtaskDescription(String subtaskDescription) {
        this.subtaskDescription = subtaskDescription;
    }
}
