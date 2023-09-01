package com.maverick.spring_reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepo extends ReactiveCrudRepository<Employee, Long> {
}