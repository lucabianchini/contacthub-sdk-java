package it.contactlab.hub.sdk.java.queries;

import it.contactlab.hub.sdk.java.models.EventType;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEventCondition {

  public abstract EventType event();

  public abstract Condition condition();

  public abstract Condition timeframe();
}
