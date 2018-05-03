package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 *  Details about the sales assistant in an event context info.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractSalesAssistant {

  /**
   * The identifier of the user.
   */
  public abstract String id();

  /**
   * The first name of the user.
   */
  public abstract Optional<String> firstName();

  /**
   * The last name of the user.
   */
  public abstract Optional<String> lastName();

  /**
   * The contact information for the sales assistant.
   */
  public abstract Optional<UserContacts> contacts();
}
