package com.java.task11.application;

import com.java.task11.controller.service.EmployeeService;
import com.java.task11.model.Employee;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class Application {

    static {
        PropertyConfigurator.configure("src/log4j.properties");
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setFirstName("Christoforo");
        employee.setLastName("Columb");
        employee.setEmail("columb@gmail.com");
        employee.setEncryptedPassword("columb");
        employee.setRoleId(1);

        EmployeeService employeeService = new EmployeeService();
        employeeService.save(employee);
        

        Employee empl = employeeService.getByEmail("columb@gmail.com");
        System.out.printf("Info: %s %s%n", empl.getFirstName(), empl.getLastName());
       // employeeService.delete(empl);
        System.out.println("\nTHE END");
    }
}
