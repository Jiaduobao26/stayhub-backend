package com.lee.stayhub.listing;

public class DeleteListingNotAllowedException extends RuntimeException {
    public DeleteListingNotAllowedException(String message) {
        super(message);
    }
}
