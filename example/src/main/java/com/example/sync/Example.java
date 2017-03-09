package com.example.sync;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.ContactHub;
import it.contactlab.hub.sdk.java.models.BaseProperties;
import it.contactlab.hub.sdk.java.models.Contacts;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.CustomerTags;
import it.contactlab.hub.sdk.java.models.Event;
import it.contactlab.hub.sdk.java.models.EventContext;
import it.contactlab.hub.sdk.java.models.EventType;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Example use of the Sync SDK.
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
  public static void main(String[] args) throws Exception {
    final Auth auth = new Auth(
      System.getenv("CONTACTHUB_TEST_TOKEN"),
      System.getenv("CONTACTHUB_TEST_WORKSPACE_ID"),
      System.getenv("CONTACTHUB_TEST_NODE_ID")
    );

    final ContactHub ch = new ContactHub(auth);

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

    String newId = ch.addCustomer(mario).id().get();
    System.out.println(newId);
    System.out.println();

    System.out.println("-------------------------------");
    System.out.println("Retrieving the customer's email");
    System.out.println("-------------------------------");
    Customer customer = ch.getCustomer(newId);
    System.out.println(customer.base().get().contacts().get().email().get());
    System.out.println();

    System.out.println("---------------");
    System.out.println("Adding an event");
    System.out.println("---------------");

    HashMap<String, Object> eventProperties = new HashMap<String, Object>();
    eventProperties.put("url", "https://example.com/");
    eventProperties.put("title", "Page Title");
    Event event = Event.builder()
                       .customerId(newId)
                       .context(EventContext.WEB)
                       .type(EventType.viewedPage)
                       .build();
    ch.addEvent(event);
    System.out.println("Event queued successfully.");
    System.out.println();

    System.out.println("-------------------");
    System.out.println("Deleting a customer");
    System.out.println("-------------------");
    boolean success = ch.deleteCustomer(newId);
    if (success) {
      System.out.println("Successfully deleted customer " + newId);
    } else {
      System.out.println("OMGZ FAILED!!1!");
    }
    System.out.println();
  }

}
