package it.contactlab.hub.sdk.java.queries;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractSimpleQuery implements Query {

  public final String type = "simple";

  public abstract ConditionContainer are();

}
