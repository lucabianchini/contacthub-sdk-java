package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.ApiException;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.exceptions.ServerException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.Job;

import com.google.gson.Gson;

public class JobApi {

  private static Gson gson = ContactHubGson.getInstance();

  /**
   * Add a new Job to a Customer.
   */
  public static Job add(Auth auth, String customerId, Job job)
      throws ApiException, ServerException, HttpException {

    String endpoint = "/customers/" + customerId + "/jobs";
    String payload = gson.toJson(job);
    String response = Request.doPost(auth, endpoint, payload);

    return gson.fromJson(response, Job.class);
  }

  /**
   * Update an existing Job.
   */
  public static Job update(Auth auth, String customerId, Job job)
      throws ApiException, ServerException, HttpException {

    String endpoint = "/customers/" + customerId + "/jobs/" + job.id();
    String payload = gson.toJson(job);
    String response = Request.doPut(auth, endpoint, payload);

    return gson.fromJson(response, Job.class);
  }

  /**
   * Remove a tag from a Customer.
   */
  public static boolean remove(Auth auth, String customerId, String jobId)
      throws ApiException, ServerException, HttpException {

    String endpoint = "/customers/" + customerId + "/jobs/" + jobId;
    String response = Request.doDelete(auth, endpoint);

    return true;
  }

}
