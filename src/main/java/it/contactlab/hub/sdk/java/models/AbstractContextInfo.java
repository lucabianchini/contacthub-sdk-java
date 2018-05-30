package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Context info about an Event.
 */
public abstract class AbstractContextInfo {

  /**
   * The client of this Event context info.
   */
  public abstract Optional<Client> client();

  /**
   * The user for this Event context info.
   */
  public abstract Optional<User> user();
}
