package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * The contact information.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractContacts {

  /**
   * The e-mail.
   */
  public abstract Optional<String> email();

  /**
   * The fax number.
   */
  public abstract Optional<String> fax();

  /**
   * The mobile phone number.
   */
  public abstract Optional<String> mobilePhone();

  /**
   * The phone.
   */
  public abstract Optional<String> phone();

  /**
   * Other contacts.
   */
  public abstract Optional<List<OtherContact>> otherContacts();

  /**
   * Mobile devices.
   */
  public abstract Optional<List<MobileDevice>> mobileDevices();

}
