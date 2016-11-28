package it.contactlab.hub.sdk.java.models;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * Customer.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
abstract class AbstractCustomerTags {
  abstract Optional<List<String>> auto();

  abstract Optional<List<String>> manual();
}
