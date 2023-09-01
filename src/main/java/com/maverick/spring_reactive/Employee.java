package com.maverick.spring_reactive;

import org.springframework.data.annotation.Id;

public class Employee{
    private @Id Long id;
    private String name, dept;
    public Employee(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }
}