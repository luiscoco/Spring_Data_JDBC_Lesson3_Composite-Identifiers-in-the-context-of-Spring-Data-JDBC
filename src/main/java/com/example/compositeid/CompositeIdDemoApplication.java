package com.example.compositeid;

import com.example.compositeid.domain.Order;
import com.example.compositeid.domain.OrderId;
import com.example.compositeid.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class CompositeIdDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompositeIdDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(OrderRepository orderRepository) {
        return args -> {
            OrderId id = new OrderId("CUST-001", 1L);
            Order order = new Order(id, LocalDate.now(), "First demo order");
            orderRepository.save(order);

            orderRepository.findAll()
                    .forEach(o -> System.out.println("Found order: " + o));
        };
    }
}
