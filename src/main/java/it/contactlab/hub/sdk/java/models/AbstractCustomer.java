package it.contactlab.hub.sdk.java.models;

import it.contactlab.hub.sdk.java.models.base.BaseProperties;

import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * A Customer.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCustomer {
  /**
   * The customer id.
   */
  public abstract Optional<String> id();

  /**
   * The registration timestamp.
   */
  public abstract Optional<OffsetDateTime> registeredAt();

  /**
   * The customer update timestamp.
   */
  public abstract Optional<OffsetDateTime> updatedAt();

  /**
   * The external id of the customer.
   */
  public abstract Optional<String> externalId();

  /**
   * The {@link BaseProperties} of the customer.
   */
  public abstract Optional<BaseProperties> base();

  /**
   * The id of the ContactHub node the customer belongs to.
   */
  public abstract Optional<String> nodeId();

  /**
   * Custom data defined by workspace based on a declarated schema.
   */
  public abstract Optional<Object> extended();

  /**
   * Custom data defined by workspace not based on a declarated schema.
   */
  public abstract Optional<String> extra();

  /**
   * The {@link CustomerTags} associated to the customer.
   */
  public abstract Optional<CustomerTags> tags();
}
