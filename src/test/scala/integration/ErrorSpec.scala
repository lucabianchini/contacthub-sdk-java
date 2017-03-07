package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

class ErrorSpec extends FeatureSpec with GivenWhenThen with DataGenerators {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  )

  val ch = new ContactHub(auth)

  feature("Authentication errors") {
    scenario("Empty auth token") {
      Given("I try to use an empty string as a token")
      val emptyToken = ""

      When("I create the Auth instance")
      def auth = new Auth(
        emptyToken,
        sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
        sys.env("CONTACTHUB_TEST_NODE_ID")
      )

      Then("IllegalArgumentException is thrown immediately")
      an [IllegalArgumentException] should be thrownBy auth
    }

    scenario("Invalid token") {
      Given("I authenticate with an invalid token")
      val auth = new Auth(
        "f4k3-t0k3n",
        sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
        sys.env("CONTACTHUB_TEST_NODE_ID")
      )
      val ch = new ContactHub(auth)

      When("I request a resource")
      def request = ch.getCustomers()

      Then("a ContactHubException is thrown")
      val e = the [ContactHubException] thrownBy request
      And("the exception statusCode is 401")
      e.getStatusCode shouldBe 401
      And("the exception errorMessage should be the one sent by the API")
      e.getErrorMessage shouldBe "The client is not authorized to access the API"
    }
  }

  feature("Not found errors") {
    scenario("Customer not found") {
      Given("a non-existent customer id")
      val customerId = "123"

      When("I try to retrieve the Customer by id")
      def getCustomer = ch.getCustomer(customerId)

      Then("a ContactHubException is thrown")
      val e = the [ContactHubException] thrownBy getCustomer
      And("The exception statusCode is 404")
      e.getStatusCode shouldBe 404
    }
  }

  feature("Invalid data") {
    scenario("Submitting invalid data") {
      Given("a customer without required data")
      val customer = Customer.builder.build

      When("I try to add the Customer")
      def addCustomer = ch.addCustomer(customer)

      Then("a ContactHubException is thrown")
      val e = the [ContactHubException] thrownBy addCustomer
      And("The exception statusCode is 400")
      e.getStatusCode shouldBe 400
    }
  }

  feature("Unexpected server errors") {
    scenario("HTTP failure") {
      Given("I simulate an HTTP failure")
      val auth = new Auth(
        "a",
        "b",
        "c",
        "https://ioadhuiqohwdiubceiub.com"
      )
      val ch = new ContactHub(auth)

      When("I request a resource")
      def request = ch.getCustomers()

      Then("an HttpException is thrown")
      val e = the [HttpException] thrownBy request
      println(e.getMessage)
    }

    scenario("50x error") {
      Given("I simulate a 500 error")
      val auth = new Auth(
        "a",
        "b",
        "c",
        "https://httpbin.org/status/500?"
      )
      val ch = new ContactHub(auth)

      When("I request a resource")
      def request = ch.getCustomers()

      Then("a ContactHubException is thrown")
      val e = the [ServerException] thrownBy request
      And("the exception statusCode is 500")
      e.getStatusCode shouldBe 500
    }

    scenario("40x error without a JSON body") {
      Given("I simulate a 404 error with a non-JSON body")
      val auth = new Auth(
        "a",
        "b",
        "c",
        "https://httpbin.org"
      )
      val ch = new ContactHub(auth)

      When("I request a resource")
      def request = ch.getCustomers()

      Then("a ContactHubException is thrown")
      val e = the [ServerException] thrownBy request
      And("the exception statusCode is 404")
      e.getStatusCode shouldBe 404
    }
  }

}
