package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 * Context info about events made in digital message context, like email marketing, sms, push, etc..
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractDigitalCampaignContextInfo extends AbstractContextInfo{
}
