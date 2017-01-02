package it.contactlab.hub.sdk.java.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.base.BaseProperties;
import it.contactlab.hub.sdk.java.models.base.Job;

import java.util.List;
import java.util.stream.Collectors;

public class JobApi {

  /**
   * Add a new Job to a Customer.
   */
  public static Customer add(Auth auth, String customerId, Job job)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Job> jobs = customer.base().get().jobs().get();

    boolean changed = jobs.add(job);

    if (changed) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().jobs(jobs).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Update an existing Job.
   */
  public static Customer update(Auth auth, String customerId, Job job)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Job> jobs = customer.base().get().jobs().get();

    List<Job> updatedJobs = jobs.stream()
        .map(j -> {
          return j.id().equals(job.id()) ? job : j;
        })
        .collect(Collectors.toList());

    if (updatedJobs != jobs) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().jobs(updatedJobs).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Remove a tag from a Customer.
   */
  public static Customer remove(Auth auth, String customerId, String jobId)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    List<Job> jobs = customer.base().get().jobs().get();

    List<Job> updatedJobs = jobs.stream()
        .filter(j -> {
          return !j.id().get().equals(jobId);
        })
        .collect(Collectors.toList());

    if (updatedJobs != jobs) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .base(BaseProperties.builder().jobs(updatedJobs).build())
          .build());
    } else {
      return customer;
    }
  }

}
