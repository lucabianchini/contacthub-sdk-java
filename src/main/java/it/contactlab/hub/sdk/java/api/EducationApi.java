package it.contactlab.hub.sdk.java.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.base.BaseProperties;
import it.contactlab.hub.sdk.java.models.base.Education;

import java.util.List;
import java.util.stream.Collectors;

public class EducationApi {

  /**
   * Add a new Education to a Customer.
   */
  public static Customer add(Auth auth, String customerId, Education education)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Education> educations = customer.base().get().educations().get();

    boolean changed = educations.add(education);

    if (changed) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().educations(educations).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Update an existing Education.
   */
  public static Customer update(Auth auth, String customerId, Education education)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Education> educations = customer.base().get().educations().get();

    List<Education> updatedEducations = educations.stream()
        .map(e -> {
          return e.id().equals(education.id()) ? education : e;
        })
        .collect(Collectors.toList());

    if (updatedEducations != educations) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().educations(updatedEducations).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Remove a tag from a Customer.
   */
  public static Customer remove(Auth auth, String customerId, String educationId)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Education> educations = customer.base().get().educations().get();

    List<Education> updatedEducations = educations.stream()
        .filter(e -> {
          return !e.id().get().equals(educationId);
        })
        .collect(Collectors.toList());

    if (updatedEducations != educations) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().educations(updatedEducations).build())
          .build());
    } else {
      return customer;
    }
  }

}
