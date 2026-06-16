package com.railway.controller;

import com.railway.entity.Booking;
import com.railway.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PostMapping
    public Booking bookTicket(@RequestBody Booking booking) {

        booking.setPnr(UUID.randomUUID()
                .toString()
                .substring(0, 8));

        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    @GetMapping("/user/{email}")
    public List<Booking> getUserBookings(
            @PathVariable String email) {

        return bookingRepository.findByUserEmail(email);
    }
    @GetMapping("/pnr/{pnr}")
    public Booking getBookingByPnr(@PathVariable String pnr) {

        return bookingRepository
                .findByPnr(pnr)
                .orElse(null);
    }
    @PutMapping("/cancel/{pnr}")
    public Booking cancelTicket(@PathVariable String pnr) {

        Booking booking = bookingRepository
                .findByPnr(pnr)
                .orElse(null);

        if (booking != null) {
            booking.setStatus("CANCELLED");
            return bookingRepository.save(booking);
        }

        return null;
    }

}