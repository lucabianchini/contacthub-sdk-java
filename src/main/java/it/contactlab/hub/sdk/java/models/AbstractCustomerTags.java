package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;
import org.immutables.gson.Gson;

import java.util.Optional;
import java.util.List;

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
