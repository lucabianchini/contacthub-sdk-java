package it.contactlab.hub.sdk.java.sync.test.integration;

import it.contactlab.hub.sdk.java.sync.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import com.google.gson.JsonObject

import java.time._

import com.google.gson.JsonObject;

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

import scala.collection.JavaConversions._

class EventSpec extends FeatureSpec with GivenWhenThen {

  val auth = new Auth(
    "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
    "40b6195f-e4f7-4f95-b10e-75268d850988",
    "854f0791-c120-4e4a-9264-6dd197cb922c"
  )

  val ch = new ContactHub(auth)
  val customerId = "2fc11014-7610-4db5-8f73-57ea9ea3be4e"
  val existingId = "539e6653-39cd-4315-905f-0b7cb1e71cd8"

  feature("adding a new event") {
    scenario("adding a simple event", Integration) {
      Given("a new event object")
      val props = new JsonObject;
      props.addProperty("url", "https://example.com/")
      props.addProperty("title", "Page Title")
      val event = Event.builder
        .customerId(customerId)
        .`type`("viewedPage")
        .context("WEB")
        .properties(props)
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
        .`type`("viewedPage")
        .context("WEB")
        .properties(new JsonObject)
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
        .`type`("viewedPage")
        .context("WEB")
        .properties(new JsonObject)
        .build

      When("the user adds the event")
      val success = ch.addEvent(event)

      Then("the event is created successfully")
      success should be (true)
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
      event.`type` should be ("viewedPage")
      event.context should be ("WEB")
      event.date should be (OffsetDateTime.parse("2016-12-29T14:36:49.339Z"))
      event.properties.getAsJsonPrimitive("title")
        .getAsString should be("The Title")
      event.contextInfo.get.getAsJsonObject("client").get("userAgent")
        .getAsString should be ("testUserAgent")
      event.registeredAt.get shouldBe (OffsetDateTime.parse("2016-12-29T14:36:49.355Z"))
    }
  }

}
