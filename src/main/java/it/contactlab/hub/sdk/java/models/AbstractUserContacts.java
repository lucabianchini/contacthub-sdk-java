package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 *  The contact information for the user.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractUserContacts {
  /**
   * The email address of the user.
   */
  public abstract Optional<String> email();

  /**
   * The mobile phone number of the user.
   */
  public abstract Optional<String> mobilePhone();

  /**
   * The phone number of the user.
   */
  public abstract Optional<String> phone();
}
