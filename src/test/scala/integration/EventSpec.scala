package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.exceptions._

import java.time._
import java.time.temporal.ChronoUnit._

import com.google.gson.JsonObject

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

import scala.collection.JavaConversions._
import scala.collection.mutable._

class EventSpec extends FeatureSpec with GivenWhenThen {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  )

  val ch = new ContactHub(auth)
  val customerId = "2fc11014-7610-4db5-8f73-57ea9ea3be4e"
  val existingId = "539e6653-39cd-4315-905f-0b7cb1e71cd8"

  feature("adding a new event") {
    scenario("adding a simple event", Integration) {
      Given("a new event object")
      val event = Event.builder
        .customerId(customerId)
        .`type`(EventType.viewedPage)
        .context(EventContext.WEB)
        .properties(HashMap(
          "url" -> "https://example.com",
          "title" -> "Page Title"
        ))
        .build

      When("the user adds the event")
      val success = ch.addEvent(event)

      Then("the event is created successfully")
      success should be (true)
    }

    scenario("adding an event with a sessionId", Integration) {
      Given("a new event object with a sessionId")
      val event = Event.builder
        .sessionId("ses123")
        .`type`(EventType.viewedPage)
        .context(EventContext.WEB)
        .build

      When("the user adds the event")
      val success = ch.addEvent(event)

      Then("the event is created successfully")
      success should be (true)
    }

    scenario("adding an event with an externalId", Integration) {
      Given("a new event object with a externalId")
      val event = Event.builder
        .externalId("ext123")
        .`type`(EventType.viewedPage)
        .context(EventContext.WEB)
        .build

      When("the user adds the event")
      val success = ch.addEvent(event)

      Then("the event is created successfully")
      success should be (true)
    }

    scenario("adding an event with sessionId and externalId", Integration) {
      Given("a new event object with both a sessionId and a externalId")
      val event = Event.builder
        .sessionId("ses123")
        .externalId("ext123")
        .`type`(EventType.viewedPage)
        .context(EventContext.WEB)
        .build

      When("the user adds the event")
      val success = ch.addEvent(event)

      Then("the event is created successfully")
      success should be (true)

      And("the sessionId is ignored (externalId has precedence)")
      // FIXME: it's not trivial to test this condition
    }
  }

  feature("retrieving events") {
    scenario("retrieve an event by id", Integration) {
      Given("an existing event id")
      val id = existingId

      When("the user asks for that event")
      val event = ch.getEvent(id)

      Then("the returned object contains the expected fields")
      event.id.get should be (id)
      event.customerId.get should be (customerId)
      event.`type` should be (EventType.viewedPage)
      event.context should be (EventContext.WEB)
      event.date should be (OffsetDateTime.parse("2016-12-29T14:36:49.339Z"))
      event.properties.get("title") should be("The Title")
      event.contextInfo.get("client").asInstanceOf[java.util.Map[String,Object]]
        .get("userAgent") should be ("testUserAgent")
      event.registeredAt.get shouldBe (OffsetDateTime.parse("2016-12-29T14:36:49.355Z"))
    }

    scenario("retrieve all events for a customer", Integration) {
      Given("a customer with some events")
      val cid = customerId

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid)

      Then("a List of Events with that customerId should be returned")
      events.length should be > 0
      forAll(events)(_.customerId.get should be (cid))
    }

    scenario("retrieve events filtering by type", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("an event type filter")
      val eventType = EventType.addedProduct
      val filters = EventFilters.builder.`type`(eventType).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters)

      Then("a List of Events with that type should be returned")
      events.length should be > 0
      forAll(events)(_.`type` should be (eventType))
    }

    scenario("retrieve events filtering by context", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("an event context filter")
      val context = EventContext.ECOMMERCE
      val filters = EventFilters.builder.context(context).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters)

      Then("a List of Events with that customerId should be returned")
      events.length should be > 0
      forAll(events)(_.context should be (context))
    }

    scenario("retrieve events filtering by mode", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("an event mode filter")
      val mode = EventMode.PASSIVE
      val filters = EventFilters.builder.mode(mode).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters)

      Then("no events should be returned (no passive events in the test db)")
      events.length should be (0)
    }

    scenario("retrieve events filtering by date", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("a date range filter")
      val dateFrom = OffsetDateTime.now.minus(1, DAYS)
      val dateTo = OffsetDateTime.now.minus(1, HOURS)
      val filters = EventFilters.builder.dateFrom(dateFrom).dateTo(dateTo).build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters)

      Then("a List of Events in that date range should be returned")
      events.length should be > 0
      forAll(events)(_.date.isAfter(dateFrom) should be (true))
      forAll(events)(_.date.isBefore(dateTo) should be (true))
    }

    scenario("retrieve events with multiple filters", Integration) {
      Given("a customer with some events")
      val cid = customerId

      And("multiple event filters")
      val eventType = EventType.addedProduct
      val context = EventContext.ECOMMERCE
      val dateTo = OffsetDateTime.now.minus(1, HOURS)
      val filters = EventFilters.builder
                                .`type`(eventType)
                                .context(context)
                                .dateTo(dateTo)
                                .build

      When("the user retrieves the events for that customer")
      val events = ch.getEvents(cid, filters)

      Then("a List of Events with that customerId should be returned")
      events.length should be > 0
      forAll(events)(_.customerId.get should be (cid))
      forAll(events)(_.`type` should be (eventType))
      forAll(events)(_.context should be (context))
      forAll(events)(_.date.isBefore(dateTo) should be (true))
    }
  }

}
