package it.contactlab.hub.sdk.java.models;

import it.contactlab.hub.sdk.java.models.base.BaseProperties;

import org.immutables.value.Value;
import org.immutables.gson.Gson;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Customer.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCustomer {
  public abstract Optional<String> id();
  public abstract Optional<OffsetDateTime> registeredAt();
  public abstract Optional<OffsetDateTime> updatedAt();
  public abstract Optional<String> externalId();
  public abstract Optional<BaseProperties> base();
  public abstract Optional<String> nodeId();
  public abstract Optional<Object> extended();
  public abstract Optional<String> extra();
  public abstract Optional<CustomerTags> tags();
}
