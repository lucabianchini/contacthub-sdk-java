package it.contactlab.hub.sdk.java.test.integration;

import it.contactlab.hub.sdk.java.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import scala.collection.JavaConversions._

class EducationSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter {

  val auth = new Auth(
    sys.env("CONTACTHUB_TEST_TOKEN"),
    sys.env("CONTACTHUB_TEST_WORKSPACE_ID"),
    sys.env("CONTACTHUB_TEST_NODE_ID")
  );

  val ch = new ContactHub(auth)
  val customerId = "0533ef33-81b1-4993-9025-18edaec976d3"

  val education1 = Education.builder.id("education1").schoolName("foo").build
  val education2 = Education.builder.id("education2").schoolName("bar").build

  before {
    ch.patchCustomer(customerId, Customer.builder
      .base(BaseProperties.builder
        .educations(Seq(education1, education2))
        .build)
      .build)
  }

  feature("managing educations") {
    scenario("adding a new education", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I add a new education")
      val newEducation = Education.builder.id("education3").schoolName("baz").build

      ch.addEducation(cid, newEducation)

      val updated = ch.getCustomer(cid)

      Then("The new education is present")
      updated.base.get.educations should contain (newEducation)

      And("The old ones are still there")
      updated.base.get.educations should contain (education1)
    }

    scenario("updating a education", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I update an existing education")
      val newEducation = Education.builder.id("education2").schoolName("baz").build

      ch.updateEducation(cid, newEducation)

      val updated = ch.getCustomer(cid)

      Then("The matching education is updated")
      updated.base.get.educations should contain (newEducation)

      And("The other educations are still there")
      updated.base.get.educations should contain (education1)
    }

    scenario("removing a education", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I remove an existing education")
      ch.removeEducation(cid, "education1")

      val updated = ch.getCustomer(cid)

      Then("The matching education is removed")
      updated.base.get.educations should not contain (education1)
    }
  }
}
