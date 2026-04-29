package com.example.demo.config;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(EventRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Event e1 = new Event();
                e1.setEventName("2026 World Cup");
                e1.setEventDate("2026-06-11");
                e1.setTicketPrice(45000.00);
                e1.setCategory("Sports");
                e1.setDescription("View All 2026 World Cup Dates");
                e1.setImageUrl("https://images.unsplash.com/photo-1518605368461-1ee12523b185?w=500&q=80");

                Event e2 = new Event();
                e2.setEventName("Teddy Swims Tickets");
                e2.setEventDate("2026-08-14");
                e2.setTicketPrice(12000.00);
                e2.setCategory("Concerts");
                e2.setDescription("View All Teddy Swims Tour Dates");
                e2.setImageUrl("https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?w=500&q=80");

                Event e3 = new Event();
                e3.setEventName("Zach Williams Tickets");
                e3.setEventDate("2026-09-02");
                e3.setTicketPrice(8500.00);
                e3.setCategory("Concerts");
                e3.setDescription("View All Zach Williams Tour Dates");
                e3.setImageUrl("https://images.unsplash.com/photo-1470229722913-7c092bcebf4b?w=500&q=80");

                Event e4 = new Event();
                e4.setEventName("Melanie Martinez Tickets");
                e4.setEventDate("2026-07-15");
                e4.setTicketPrice(15000.00);
                e4.setCategory("Concerts");
                e4.setDescription("View All Melanie Martinez Tour Dates");
                e4.setImageUrl("https://images.unsplash.com/photo-1540039155732-684735035727?w=500&q=80");

                repository.saveAll(List.of(e1, e2, e3, e4));
                System.out.println("Premium Marketplace Data Seeded!");
            }
        };
    }
}