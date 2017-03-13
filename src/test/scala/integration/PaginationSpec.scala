package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models.GetCustomersOptions
import it.contactlab.hub.sdk.java.models.EventFilters

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

class PaginationSpec extends FeatureSpec with GivenWhenThen {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  )

  val ch = new ContactHub(auth)

  // FIXME: Given it takes about 30 seconds for new events to be indexed, we
  // rely on some existing events that were added manually to the test workspace
  val customerId = "b765329a-84b2-4380-bfa5-fa4ec33d3b82"

  feature("Paginating Customers") {
    scenario("Reading pagination attributes") {
      Given("I fetch a list of customers")
      val page0 = ch.getCustomers

      When("I inspect the 'page' property")
      val page = page0.page

      Then("I find some interesting pagination attributes")
      page.number shouldBe 0
      page.size shouldBe 10
      page.totalElements.toInt should be > 0
      page.totalPages.toInt should be > 0
    }

    scenario("Fetching a specific page") {
      Given("I request a specific page")
      val page3 = ch.getCustomers(GetCustomersOptions.builder.page(3).build)

      When("I inspect the 'page' property")
      val page = page3.page

      Then("I find the expected page number")
      page.number shouldBe 3
    }

    scenario("Fetching the next page") {
      Given("I fetch a list of customers")
      val page0 = ch.getCustomers

      When("I request the next page")
      val page1 = page0.nextPage

      Then("I get the second page")
      page1.get.page.number shouldBe 1
    }

    scenario("Fetching the previous page") {
      Given("I fetch a specific page")
      val page3 = ch.getCustomers(GetCustomersOptions.builder.page(3).build)

      When("I request the previous page")
      val page2 = page3.previousPage

      Then("I get the previous page")
      page2.get.page.number shouldBe 2
    }

    scenario("Navigating back and forwards") {
      Given("I fetch a list of customers")
      val page3 = ch.getCustomers(GetCustomersOptions.builder.page(3).build)

      When("I navigate back and forward")
      val page = page3.previousPage.get.nextPage.get.page

      Then("I end back on the same page")
      page.number shouldBe 3
    }

    scenario("Calling nextPage from the last page") {
      Given("I fetch the last page")
      val page0 = ch.getCustomers
      val last = page0.page.totalPages
      val lastPage = ch.getCustomers(GetCustomersOptions.builder.page(last).build)

      When("I try to fetch the next page")
      val next = lastPage.nextPage

      Then("I get an empty Optional")
      next.isPresent shouldBe false
    }

    scenario("Calling previousPage from the first page") {
      Given("I fetch the first page")
      val page0 = ch.getCustomers

      When("I try to fetch the previous page")
      val prev = page0.previousPage

      Then("I get an empty Optional")
      prev.isPresent shouldBe false
    }
  }

  /*
   * We only test one scenario for Events, as the code handling pagination is
   * the same and we don't expect Events to behave differently.
   */
  feature("Paginating Events") {
    scenario("Navigating back and forwards") {
      Given("I fetch a list of events")
      val page3 = ch.getEvents(customerId, EventFilters.builder.page(3).build)

      When("I navigate back and forward")
      val page = page3.previousPage.get.nextPage.get.page

      Then("I end back on the same page")
      page.number shouldBe 3
    }
  }

}
