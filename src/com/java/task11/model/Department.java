package com.java.task11.model;

import javax.persistence.*;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
/*@Entity
@Table(name = "departments")*/
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "department_name", length = 30)
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
