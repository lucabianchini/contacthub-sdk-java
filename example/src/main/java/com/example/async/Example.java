package com.example.async;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.async.ContactHub;
import it.contactlab.hub.sdk.java.models.Customer;

/**
 * Example use of the Async SDK.
 */
public class Example {

  /**
   * The main method.
   */
  public static void main(String[] args) {
    Auth auth = new Auth("97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d","40b6195f-e4f7-4f95-b10e-75268d850988","854f0791-c120-4e4a-9264-6dd197cb922c");

    ContactHub ch = new ContactHub(auth);

    System.out.println("-----------------------------------");
    System.out.println("Retrieving customers' phone numbers");
    System.out.println("-----------------------------------");
    ch.getCustomers().thenAccept(customers ->
      customers.forEach(customer -> System.out.println(customer.getBase().getContacts().getPhone()))
    ).toCompletableFuture().join();

    System.out.println();
    System.out.println("---------------------------");
    System.out.println("Retrieving customer's email");
    System.out.println("---------------------------");
    ch.getCustomer("f5d3932d-6cd3-4969-ace2-9fd9c87acd13").thenAccept(customer ->
      System.out.println(customer.getBase().getContacts().getEmail())
    ).toCompletableFuture().join();
  }

}
