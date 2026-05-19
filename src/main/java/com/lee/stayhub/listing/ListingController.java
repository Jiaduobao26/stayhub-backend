package com.lee.stayhub.listing;

import com.lee.stayhub.booking.BookingService;
import com.lee.stayhub.model.BookingDto;
import com.lee.stayhub.model.ListingDto;
import com.lee.stayhub.model.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/listings")
public class ListingController {

    private final BookingService bookingService;
    private final ListingService listingService;


    public ListingController(BookingService bookingService, ListingService listingService) {
        this.bookingService = bookingService;
        this.listingService = listingService;
    }


    @GetMapping
    public List<ListingDto> getListings(@AuthenticationPrincipal UserEntity user) {
        return listingService.getListings(user.getId());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createListing(
            @AuthenticationPrincipal UserEntity user,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("description") String description,
            @RequestParam("guest_number") int guestNumber,
            @RequestParam("images") List<MultipartFile> images
    ) {
        listingService.createListing(user.getId(), name, address, description, guestNumber, images);
    }


    @DeleteMapping("/{listingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteListing(@AuthenticationPrincipal UserEntity user, @PathVariable Long listingId) {
        listingService.deleteListing(user.getId(), listingId);
    }


    @GetMapping("/search")
    public List<ListingDto> search(
            @AuthenticationPrincipal UserEntity user,
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("checkin_date") LocalDate checkIn,
            @RequestParam("checkout_date") LocalDate checkOut,
            @RequestParam("guest_number") int guestNumber,
            @RequestParam(name = "distance", required = false, defaultValue = "500000") Integer distance
    ) {
        return listingService.search(lat, lon, distance, checkIn, checkOut, guestNumber);
    }


    @GetMapping("/{listingId}/bookings")
    public List<BookingDto> getListingBookings(@AuthenticationPrincipal UserEntity user, @PathVariable Long listingId) {
        return bookingService.findBookingsByListingId(user.getId(), listingId);
    }
}
