package com.maverick.spring_reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping
    public Mono<Rendering> getIndex() {
        return employeeRepo.findAll().collectList()
                .map(employees -> Rendering.view("index")
                        .modelAttribute("employees", employees)
                        .modelAttribute("employee", new Employee("", ""))
                        .build());
    }

    @PostMapping
    public Mono<String> addEmployee(@ModelAttribute("employee") Mono<Employee> employee) {
        return employee.flatMap(emp -> employeeRepo.save(emp))
                .thenReturn("redirect:/");
    }
    // @PostMapping
    // public Mono<String> addEmployee(@ModelAttribute Mono<Employee> employee) {
    // return employee.flatMap((emp) -> employeeRepo.save(emp)).map(emp ->
    // "redirect:/");
    // }
}