package it.contactlab.hub.sdk.java.exceptions;

public class ContactHubException extends Exception {

  public ContactHubException(String message) {
    super(message);
  }

  public ContactHubException(String message, Throwable cause) {
    super(message, cause);
  }
}
