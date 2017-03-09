package com.example.async;

import it.contactlab.hub.sdk.java.AsyncContactHub;
import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.models.BaseProperties;
import it.contactlab.hub.sdk.java.models.Contacts;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.CustomerTags;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Example use of the Async SDK.
 */
public class Example {

  private static final String chars = "abcdefghijklmnopqrstuvwxyz";
  private static final SecureRandom rnd = new SecureRandom();

  private static String randomString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    return sb.toString();
  }

  /**
   * The main method.
   */
  public static void main(String[] args) {
    final Auth auth = new Auth(
      System.getenv("CONTACTHUB_TEST_TOKEN"),
      System.getenv("CONTACTHUB_TEST_WORKSPACE_ID"),
      System.getenv("CONTACTHUB_TEST_NODE_ID")
    );

    final AsyncContactHub ch = new AsyncContactHub(auth);

    System.out.println("-----------------------");
    System.out.println("Creating a new customer");
    System.out.println("-----------------------");

    Customer mario = Customer.builder()
        .base(BaseProperties.builder()
            .firstName("Mario")
            .lastName("Rossi")
            .contacts(Contacts.builder()
              .email(randomString(8) + "@example.com")
              .build())
            .build())
        .tags(CustomerTags.builder()
            .manual(new HashSet<String>(Arrays.asList("a", "b")))
            .build())
        .build();

    String newId = ch.addCustomer(mario).thenApply(customer ->
        customer.id().get()
    ).toCompletableFuture().join();

    System.out.println(newId);
    System.out.println();

    System.out.println("-------------------------------");
    System.out.println("Retrieving the customer's email");
    System.out.println("-------------------------------");

    ch.getCustomer(newId).thenAccept(customer ->
        System.out.println(customer.base().get().contacts().get().email().get())
    ).toCompletableFuture().join();
    System.out.println();
  }

}
