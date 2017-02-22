package com.example.async;

import it.contactlab.hub.sdk.java.AsyncContactHub;
import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.models.Customer;

/**
 * Example use of the Async SDK.
 */
public class Example {

  private static String cid = "f5d3932d-6cd3-4969-ace2-9fd9c87acd13";

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

    System.out.println();
    System.out.println("---------------------------");
    System.out.println("Retrieving customer's email");
    System.out.println("---------------------------");
    ch.getCustomer("f5d3932d-6cd3-4969-ace2-9fd9c87acd13").thenAccept(customer ->
      System.out.println(customer.base().get().contacts().get().email().get())
    ).toCompletableFuture().join();
  }

}
