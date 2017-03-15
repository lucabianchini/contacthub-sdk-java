package it.contactlab.hub.sdk.java.queries;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractConditionContainer {

  public abstract Condition condition();

}
