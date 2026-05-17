package com.example.demo.config;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(EventRepository repository) {
        return args -> {
            if (repository.count() == 0) {

                Event e1 = new Event();
                e1.setName("2026 World Cup");
                e1.setEventDate(LocalDate.parse("2026-06-11"));
                e1.setEventTime(LocalTime.of(18, 0));
                e1.setVenue("Main Stadium");
                e1.setTotalCapacity(50000);
                e1.setAvailableCapacity(50000);
                e1.setTicketPrice(45000.00);
                e1.setCategory("Sports");
                e1.setDescription("View All 2026 World Cup Dates");
                e1.setImageUrl("/images/pic1.jpg");

                Event e2 = new Event();
                e2.setName("Teddy Swims Tickets");
                e2.setEventDate(LocalDate.parse("2026-08-14"));
                e2.setEventTime(LocalTime.of(20, 0));
                e2.setVenue("City Arena");
                e2.setTotalCapacity(15000);
                e2.setAvailableCapacity(15000);
                e2.setTicketPrice(12000.00);
                e2.setCategory("Concerts");
                e2.setDescription("View All Teddy Swims Tour Dates");
                e2.setImageUrl("/images/pic2.jpg");

                Event e3 = new Event();
                e3.setName("Zach Williams Tickets");
                e3.setEventDate(LocalDate.parse("2026-09-02"));
                e3.setEventTime(LocalTime.of(19, 30));
                e3.setVenue("Grand Theater");
                e3.setTotalCapacity(5000);
                e3.setAvailableCapacity(5000);
                e3.setTicketPrice(8500.00);
                e3.setCategory("Concerts");
                e3.setDescription("View All Zach Williams Tour Dates");
                e3.setImageUrl("/images/pic3.jpg");

                Event e4 = new Event();
                e4.setName("Melanie Martinez Tickets");
                e4.setEventDate(LocalDate.parse("2026-07-15"));
                e4.setEventTime(LocalTime.of(21, 0));
                e4.setVenue("Outdoor Amphitheater");
                e4.setTotalCapacity(25000);
                e4.setAvailableCapacity(25000);
                e4.setTicketPrice(15000.00);
                e4.setCategory("Concerts");
                e4.setDescription("View All Melanie Martinez Tour Dates");
                e4.setImageUrl("/images/pic4.jpg");

                repository.saveAll(List.of(e1, e2, e3, e4));
                System.out.println("Premium Marketplace Data Seeded!");
            }
        };
    }
}