package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * The contact information.
 */
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
  @Nullable public abstract List<OtherContact> otherContacts();

  /**
   * Mobile devices.
   */
  @Nullable public abstract List<MobileDevice> mobileDevices();

}
