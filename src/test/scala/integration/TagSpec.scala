package it.contactlab.hub.sdk.java.sync.test.integration

import it.contactlab.hub.sdk.java.sync.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

import scala.collection.JavaConversions._

class TagSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter {

  implicit val genCustomer: Gen[Customer] = for {
    firstName <- Gen.nonEmptyListOf(Gen.alphaChar).map(_.mkString)
    lastName  <- Gen.nonEmptyListOf(Gen.alphaChar).map(_.mkString)
    email     <- Gen.nonEmptyListOf(Gen.alphaChar).map(_.mkString)
  } yield {
    val contacts = Contacts.builder.email(s"$email@example.com").build

    Customer.builder
      .base(BaseProperties.builder
        .firstName(firstName)
        .lastName(lastName)
        .contacts(contacts)
        .build)
      .build
  }

  val auth = new Auth(
    "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
    "40b6195f-e4f7-4f95-b10e-75268d850988",
    "854f0791-c120-4e4a-9264-6dd197cb922c"
  )

  val ch = new ContactHub(auth)
  val customerId = "4012e67d-72fd-4f84-9039-71cbc5b80078"

  before {
    ch.patchCustomer(customerId, Customer.builder
      .tags(CustomerTags.builder
        .manual(Set("existing-tag", "another-tag"))
        .auto(Set("existing-auto-tag"))
        .build)
      .build)
  }

  feature("adding a tag") {
    scenario("when the tag is new", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I add a new tag")
      val updated = ch.addTag(cid, "new-tag")

      Then("The new tag is present")
      updated.tags.get.manual.get should contain ("new-tag")

      And("The old ones are still there")
      updated.tags.get.manual.get should contain ("existing-tag")
      updated.tags.get.auto.get should contain ("existing-auto-tag")
    }

    scenario("when the tag is already present", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I try to add an existing tag")
      val updated = ch.addTag(cid, "existing-tag")

      Then("The existing tag is not removed")
      updated.tags.get.manual.get should contain ("existing-tag")

      And("The old ones are still there")
      updated.tags.get.auto.get should contain ("existing-auto-tag")
    }

    scenario("when the Customer has no other tags", Integration) {
      Given("a new Customer with no tags")
      val customer = genCustomer.sample.get
      val cid = ch.addCustomer(customer).id.get

      When("I add a new tag")
      val updated = ch.addTag(cid, "new-tag")

      Then("The new tag is present")
      updated.tags.get.manual.get should contain ("new-tag")
    }
  }

  feature("removing a tag") {
    scenario("when the tag is present", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I remove an existing tag")
      val updated = ch.removeTag(cid, "existing-tag")

      Then("The existing tag is no longer present")
      updated.tags.get.manual.get should not contain ("existing-tag")

      And("The other ones are still there")
      updated.tags.get.manual.get should contain ("another-tag")
      updated.tags.get.auto.get should contain ("existing-auto-tag")
    }

    scenario("when the tag is not present", Integration) {
      Given("a known customer")
      val cid = customerId

      When("I try to remove a tag that is not present")
      val updated = ch.removeTag(cid, "non-existing-tag")

      Then("The existing ones are still there")
      updated.tags.get.auto.get should contain ("existing-auto-tag")
      updated.tags.get.manual.get should contain ("existing-tag")
      updated.tags.get.manual.get should contain ("another-tag")
    }

    scenario("when the Customer has no tags", Integration) {
      Given("a new Customer with no tags")
      val customer = genCustomer.sample.get

      val cid = ch.addCustomer(customer).id.get

      When("I try to remove a tag that is not present")
      val updated = ch.removeTag(cid, "non-existing-tag")

      Then("the Customer tags have not changed")
      updated.tags.isPresent should be (false)
    }
  }
}
