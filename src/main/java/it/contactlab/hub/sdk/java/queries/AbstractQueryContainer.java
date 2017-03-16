package it.contactlab.hub.sdk.java.queries;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractQueryContainer {

  public abstract Optional<String> name();

  public abstract Query query();

}
