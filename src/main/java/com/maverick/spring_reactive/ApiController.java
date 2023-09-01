package com.maverick.spring_reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("api")
public class ApiController {
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("all")
    public Flux<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @PostMapping
    public Mono addEmployee(@RequestBody Mono<Employee> employee){
        employee.flatMap(employeeRepo::save);
        return Mono.empty();
    }
}