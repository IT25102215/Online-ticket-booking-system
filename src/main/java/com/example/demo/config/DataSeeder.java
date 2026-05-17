package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            EventRepository eventRepo,
            ReviewRepository reviewRepo,
            UserRepository userRepo,
            OrganizerRepository organizerRepo,
            VenueRepository venueRepo) {

        return args -> {
            // Seed Events (Restored all 4 events!)
            if (eventRepo.count() == 0) {
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

                eventRepo.saveAll(List.of(e1, e2, e3, e4));
                System.out.println("Events Seeded!");
            }

            // Seed Reviews (Sasheni)
            if (reviewRepo.count() == 0) {
                Review r1 = new Review();
                r1.setEventName("2026 World Cup");
                r1.setUserName("Kamal Perera");
                r1.setRating(5);
                r1.setComment("Amazing seats and great atmosphere!");
                reviewRepo.save(r1);

                System.out.println("Reviews Seeded!");
            }

            // Seed Users (Winnath)
            if (userRepo.count() == 0) {
                User u1 = new User();
                u1.setFullName("Winnath Dev");
                u1.setEmail("winnath@demo.com");
                u1.setPhoneNumber("0771234567");
                u1.setRole("Premium");
                userRepo.save(u1);

                System.out.println("Users Seeded!");
            }

            // Seed Organizers (Kavindu)
            if (organizerRepo.count() == 0) {
                Organizer o1 = new Organizer();
                o1.setName("Live Nation LK");
                o1.setContactPerson("Nimal Silva");
                o1.setEmail("contact@livenation.lk");
                o1.setPhone("0112345678");
                organizerRepo.save(o1);

                System.out.println("Organizers Seeded!");
            }

            // Seed Venues (Kavindu)
            if (venueRepo.count() == 0) {
                Organizer linkedOrganizer = organizerRepo.findAll().get(0);

                Venue v1 = new Venue();
                v1.setName("Sugathadasa Stadium");
                v1.setAddress("Colombo");
                v1.setCapacity(25000);
                v1.setOrganizer(linkedOrganizer);
                venueRepo.save(v1);

                System.out.println("Venues Seeded!");
            }
        };
    }
}