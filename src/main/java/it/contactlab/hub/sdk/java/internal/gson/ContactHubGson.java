package it.contactlab.hub.sdk.java.internal.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import it.contactlab.hub.sdk.java.models.ContactCenterEvent;
import it.contactlab.hub.sdk.java.models.DigitalCampaignEvent;
import it.contactlab.hub.sdk.java.models.EcommerceEvent;
import it.contactlab.hub.sdk.java.models.Event;
import it.contactlab.hub.sdk.java.models.EventContext;
import it.contactlab.hub.sdk.java.models.MobileEvent;
import it.contactlab.hub.sdk.java.models.OtherEvent;
import it.contactlab.hub.sdk.java.models.RetailEvent;
import it.contactlab.hub.sdk.java.models.SocialEvent;
import it.contactlab.hub.sdk.java.models.WebEvent;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ContactHubGson {

  /**
   * Handling java.time.OffsetDateTime
   */
  private static final DateTimeFormatter dateTimeFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private static final JsonSerializer<OffsetDateTime> dateTimeJsonSerializer =
      (src, typeOfSrc, context) -> new JsonPrimitive(src.format(dateTimeFormatter));
  private static final JsonDeserializer<OffsetDateTime> dateTimeJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : OffsetDateTime.parse(json.getAsString(), dateTimeFormatter);

  /**
   * Handling java.time.LocalDate
   */
  private static final DateTimeFormatter dateFormatter =
      DateTimeFormatter.ISO_LOCAL_DATE; // "yyyy-MM-dd"

  private static final JsonSerializer<LocalDate> dateJsonSerializer =
      (src, typeOfSrc, context) -> new JsonPrimitive(src.format(dateFormatter));
  private static final JsonDeserializer<LocalDate> dateJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : LocalDate.parse(json.getAsString(), dateFormatter);

  /**
   * Handling java.time.ZoneId
   */
  private static final JsonSerializer<ZoneId> zoneIdJsonSerializer =
      (src, typeofSrc, context) -> new JsonPrimitive(src.getId());
  private static final JsonDeserializer<ZoneId> zoneIdJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : ZoneId.of(json.getAsString());

  /**
   * Registering all (de)serializers.
   */
  public static final Gson getInstance() {

    RuntimeTypeAdapterFactory<Event> eventAdapter =
        RuntimeTypeAdapterFactory
        .of(Event.class, "context")
        .registerSubtype(ContactCenterEvent.class, EventContext.CONTACT_CENTER.toString())
        .registerSubtype(DigitalCampaignEvent.class, EventContext.DIGITAL_CAMPAIGN.toString())
        .registerSubtype(EcommerceEvent.class, EventContext.ECOMMERCE.toString())
        .registerSubtype(MobileEvent.class, EventContext.MOBILE.toString())
        .registerSubtype(OtherEvent.class, EventContext.OTHER.toString())
        .registerSubtype(RetailEvent.class, EventContext.RETAIL.toString())
        .registerSubtype(SocialEvent.class, EventContext.SOCIAL.toString())
        .registerSubtype(WebEvent.class, EventContext.WEB.toString());

    GsonBuilder gsonBuilder = new GsonBuilder()
        .registerTypeAdapter(OffsetDateTime.class, dateTimeJsonDeserializer)
        .registerTypeAdapter(OffsetDateTime.class, dateTimeJsonSerializer)
        .registerTypeAdapter(LocalDate.class, dateJsonSerializer)
        .registerTypeAdapter(LocalDate.class, dateJsonDeserializer)
        .registerTypeAdapter(ZoneId.class, zoneIdJsonSerializer)
        .registerTypeAdapter(ZoneId.class, zoneIdJsonDeserializer)
        .registerTypeAdapterFactory(eventAdapter);

    return gsonBuilder.create();
  }

  /**
   * Public method to use the same date formatting in other contexts.
   */
  public static final String formatDate(OffsetDateTime date) {
    return date.format(dateTimeFormatter);
  }
}
