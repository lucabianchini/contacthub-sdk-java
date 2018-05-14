package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 *  Details about the user in an event context info.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractUser {

  /**
   * The identifier of the user.
   */
  public abstract String id();

  /**
   * The external identifier of the user.
   */
  public abstract Optional<String> externalId();

  /**
   * The username of the user.
   */
  public abstract Optional<String> username();

  /**
   * The first name of the user.
   */
  public abstract Optional<String> firstName();

  /**
   * The last name of the user.
   */
  public abstract Optional<String> lastName();

  /**
   * The contact information for the user.
   */
  public abstract Optional<UserContacts> contacts();
}
