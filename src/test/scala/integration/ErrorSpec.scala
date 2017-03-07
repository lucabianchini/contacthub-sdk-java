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

      Then("A ContactHubException is thrown")
      val e = the [ContactHubException] thrownBy request
      And("The exception statusCode is 401")
      e.getStatusCode shouldBe 401
    }
  }

}
