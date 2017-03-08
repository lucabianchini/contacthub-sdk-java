package it.contactlab.hub.sdk.java.exceptions;

import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpException extends ContactHubException {

  /**
   * HttpException is thrown when a request to the ContactHub API failed with an
   * exception and no response is available.
   */
  public HttpException(UnirestException exception) {
    super("The HTTP call failed with error: " + exception.getMessage(), exception);
  }

}
