package com.java.task11.application;

import org.apache.log4j.PropertyConfigurator;

import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;


public class Application {

    static {
        PropertyConfigurator.configure("src/log4j.properties");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Christoforo");
        user.setLastName("Columb");
        user.setEmail("columb@gmail.com");
        user.setEncryptedPassword("columb");
        user.setRoleId(1);

        UserService employeeService = new UserService();
        employeeService.save(user);
        System.out.println(user.toString());
        

        User empl = employeeService.getByEmail("columb@gmail.com");
        System.out.printf("Info: %s %s%n", empl.getFirstName(), empl.getLastName());
        employeeService.delete(empl);
        System.out.println("\nTHE END");
    }
}
