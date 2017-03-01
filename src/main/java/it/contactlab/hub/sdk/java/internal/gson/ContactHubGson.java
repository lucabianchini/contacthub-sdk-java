package it.contactlab.hub.sdk.java.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ContactHubGson {

  /**
   * Handling java.time.OffsetDateTime
   */
  public static DateTimeFormatter dateTimeFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private static JsonSerializer<OffsetDateTime> dateTimeJsonSerializer =
      (src, typeOfSrc, context) -> new JsonPrimitive(src.format(dateTimeFormatter));
  private static JsonDeserializer<OffsetDateTime> dateTimeJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : OffsetDateTime.parse(json.getAsString(), dateTimeFormatter);

  /**
   * Handling java.time.LocalDate
   */
  private static DateTimeFormatter dateFormatter =
      DateTimeFormatter.ISO_LOCAL_DATE; // "yyyy-MM-dd"

  private static JsonSerializer<LocalDate> dateJsonSerializer =
      (src, typeOfSrc, context) -> new JsonPrimitive(src.format(dateFormatter));
  private static JsonDeserializer<LocalDate> dateJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : LocalDate.parse(json.getAsString(), dateFormatter);

  /**
   * Handling java.time.ZoneId
   */
  private static JsonSerializer<ZoneId> zoneIdJsonSerializer =
      (src, typeofSrc, context) -> new JsonPrimitive(src.getId());
  private static JsonDeserializer<ZoneId> zoneIdJsonDeserializer =
      (json, typeOfT, context) ->
      json == null ? null : ZoneId.of(json.getAsString());

  /**
   * Registering all (de)serializers.
   */
  public static Gson getInstance() {
    GsonBuilder gsonBuilder = new GsonBuilder()
        .registerTypeAdapter(OffsetDateTime.class, dateTimeJsonDeserializer)
        .registerTypeAdapter(OffsetDateTime.class, dateTimeJsonSerializer)
        .registerTypeAdapter(LocalDate.class, dateJsonSerializer)
        .registerTypeAdapter(LocalDate.class, dateJsonDeserializer)
        .registerTypeAdapter(ZoneId.class, zoneIdJsonSerializer)
        .registerTypeAdapter(ZoneId.class, zoneIdJsonDeserializer);

    return gsonBuilder.create();
  }

}
