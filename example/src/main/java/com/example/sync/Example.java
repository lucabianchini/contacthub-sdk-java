package com.example.sync;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.CustomerTags;
import it.contactlab.hub.sdk.java.models.base.BaseProperties;
import it.contactlab.hub.sdk.java.models.base.Contacts;
import it.contactlab.hub.sdk.java.sync.ContactHub;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Example use of the Sync SDK.
 */
public class Example {

  private static final String cid = "f5d3932d-6cd3-4969-ace2-9fd9c87acd13";
  private static final String eid = "db55ec278cd6ca385c6d6a1ae49987c2";

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
        "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
        "40b6195f-e4f7-4f95-b10e-75268d850988",
        "854f0791-c120-4e4a-9264-6dd197cb922c"
        );

    final ContactHub ch = new ContactHub(auth);

    System.out.println("-----------------------------------");
    System.out.println("Retrieving customers' phone numbers");
    System.out.println("-----------------------------------");
    List<Customer> customers = ch.getCustomers();
    customers.forEach(customer -> System.out.println(
        customer.base().get().contacts().get().phone().get()));
    System.out.println();

    System.out.println("---------------------------");
    System.out.println("Retrieving customer's email");
    System.out.println("---------------------------");
    Customer customer = ch.getCustomer("f5d3932d-6cd3-4969-ace2-9fd9c87acd13");
    System.out.println(customer.base().get().contacts().get().email().get());
    System.out.println();

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
            .manual(Arrays.asList("example-tag"))
            .build())
        .build();

    String newId = ch.addCustomer(mario).id().get();
    System.out.println(newId);
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
