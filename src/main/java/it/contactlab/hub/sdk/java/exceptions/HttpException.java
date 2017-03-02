package it.contactlab.hub.sdk.java.exceptions;

import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpException extends BaseException {

  public HttpException(UnirestException exception) {
    super("The HTTP call failed with error: " + exception.getMessage(), exception);
  }

}
