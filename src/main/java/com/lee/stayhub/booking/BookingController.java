package com.lee.stayhub.booking;

import com.lee.stayhub.model.BookingDto;
import com.lee.stayhub.model.BookingRequest;
import com.lee.stayhub.model.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping
    public List<BookingDto> getGuestBookings(@AuthenticationPrincipal UserEntity user) {
        return bookingService.findBookingsByGuestId(user.getId());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBooking(@AuthenticationPrincipal UserEntity user, @RequestBody BookingRequest body) {
        bookingService.createBooking(user.getId(), body.listingId(), body.checkInDate(), body.checkOutDate());
    }


    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@AuthenticationPrincipal UserEntity user, @PathVariable long bookingId) {
        bookingService.deleteBooking(user.getId(), bookingId);
    }
}

