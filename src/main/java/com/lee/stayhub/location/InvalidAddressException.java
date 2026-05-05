package com.lee.stayhub.location;

public class InvalidAddressException extends RuntimeException {
  public InvalidAddressException() {
    super("Invalid address");
  }
}
