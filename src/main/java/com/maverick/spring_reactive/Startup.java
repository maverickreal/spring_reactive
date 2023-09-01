package com.maverick.spring_reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.test.StepVerifier;

@Configuration
public class Startup {
    @Bean
    CommandLineRunner initDatabase(R2dbcEntityTemplate template) {
        return (args) -> {
            template.getDatabaseClient()
                    .sql("create table employee (id int primary key auto_increment, name text not null, dept text not null)")
                    .fetch()
                    .rowsUpdated()
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

            template.insert(Employee.class)
                    .using(new Employee("John", "Sales"))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

            template.insert(Employee.class)
                    .using(new Employee("joseph", "tech"))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

            template.insert(Employee.class)
                    .using(new Employee("alice", "management"))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

            template.insert(Employee.class)
                    .using(new Employee("stephanie", "security"))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
        };
    }
}