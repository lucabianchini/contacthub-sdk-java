package it.contactlab.hub.sdk.java.queries;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractAtomicCondition implements Condition {

  public final String type = "atomic";

  public abstract String attribute();

  public abstract Operator operator();

  public abstract Optional<Object> value();

}
