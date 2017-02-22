package it.contactlab.hub.sdk.java.internal.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.gson.ContactHubGson;
import it.contactlab.hub.sdk.java.http.Request;
import it.contactlab.hub.sdk.java.models.Job;

import com.google.gson.Gson;
import org.json.JSONObject;

public class JobApi {

  private static Gson gson = ContactHubGson.getInstance();

  /**
   * Add a new Job to a Customer.
   */
  public static Job add(Auth auth, String customerId, Job job)
      throws HttpException {

    String endpoint = "/customers/" + customerId + "/jobs";
    String payload = gson.toJson(job);
    JSONObject response = Request.doPost(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Job.class);
  }

  /**
   * Update an existing Job.
   */
  public static Job update(Auth auth, String customerId, Job job)
      throws HttpException {

    String endpoint = "/customers/" + customerId + "/jobs/" + job.id();
    String payload = gson.toJson(job);
    JSONObject response = Request.doPut(auth, endpoint, payload);

    return gson.fromJson(response.toString(), Job.class);
  }

  /**
   * Remove a tag from a Customer.
   */
  public static boolean remove(Auth auth, String customerId, String jobId)
      throws HttpException {

    String endpoint = "/customers/" + customerId + "/jobs/" + jobId;
    JSONObject response = Request.doDelete(auth, endpoint);

    return true;
  }

}
