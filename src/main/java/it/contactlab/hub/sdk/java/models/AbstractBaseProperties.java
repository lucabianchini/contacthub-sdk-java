package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Base Properties for a Customer.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractBaseProperties {

  /**
   * The picture url of customer.
   */
  public abstract Optional<URI> pictureUrl();

  /**
   * The title.
   */
  public abstract Optional<String> title();

  /**
   * The prefix.
   */
  public abstract Optional<String> prefix();

  /**
   * The first name.
   */
  public abstract Optional<String> firstName();

  /**
   * The last name.
   */
  public abstract Optional<String> lastName();

  /**
   * The middle name.
   */
  public abstract Optional<String> middleName();

  /**
   * Gender.
   */
  public abstract Optional<String> gender();

  /**
   * Date of birth.
   */
  public abstract Optional<LocalDate> dob();

  /**
   * The locale.
   */
  public abstract Optional<String> locale();

  /**
   * The time zone.
   */
  public abstract Optional<ZoneId> timezone();

  /**
   * The contact information.
   */
  public abstract Optional<Contacts> contacts();

  /**
   * The address information.
   */
  public abstract Optional<Address> address();

  /**
   * Credential.
   */
  public abstract Optional<Credential> credential();

  /**
   * Educations.
   *
   * <p>Marked as @Nullable so that you can create a `patchCustomer` object with
   * this field set to null. It is never `null` when it is persisted.</p>
   */
  @Nullable public abstract List<Education> educations();

  /**
   * Likes.
   *
   * <p>Marked as @Nullable so that you can create a `patchCustomer` object with
   * this field set to null. It is never `null` when it is persisted.</p>
   */
  @Nullable public abstract List<Like> likes();

  /**
   * Social profile.
   */
  public abstract Optional<SocialProfile> socialProfile();

  /**
   * Jobs.
   *
   * <p>Marked as @Nullable so that you can create a `patchCustomer` object with
   * this field set to null. It is never `null` when it is persisted.</p>
   */
  @Nullable public abstract List<Job> jobs();

  /**
   * Subscriptions.
   *
   * <p>Marked as @Nullable so that you can create a `patchCustomer` object with
   * this field set to null. It is never `null` when it is persisted.</p>
   */
  @Nullable public abstract List<Subscription> subscriptions();

}
