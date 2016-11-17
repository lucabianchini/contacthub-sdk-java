package it.contactlab.hub.sdk.java.sync.test.integration;

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import scala.compat.java8.FutureConverters._

import it.contactlab.hub.sdk.java.sync.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import org.scalacheck.Gen

class CustomersSpec extends FeatureSpec with GivenWhenThen {

  implicit val genCustomer: Gen[Customer] = for {
    firstName <- Gen.alphaStr
    lastName  <- Gen.alphaStr
    email     <- Gen.alphaStr
  } yield {
    val customer = new Customer
    val contacts = new Contacts
    val base = new BaseProperties
    base.setFirstName(firstName)
    base.setLastName(lastName)
    contacts.setEmail(s"$email@example.com")
    base.setContacts(contacts)
    customer.setBase(base);
    customer
  }

  implicit def toFuture[A](c: java.util.concurrent.CompletionStage[A]) = c.toScala

  val auth = new Auth(
    "97841617075b4b5f8ea88c30a8d2aec7647b7181df2c483fa78138c8d58aed4d",
    "40b6195f-e4f7-4f95-b10e-75268d850988",
    "854f0791-c120-4e4a-9264-6dd197cb922c"
  )

  val ch = new ContactHub(auth)
  val customerId = "f5d3932d-6cd3-4969-ace2-9fd9c87acd13"
  val externalId = "db55ec278cd6ca385c6d6a1ae49987c2"

  feature("retrieving customers") {
    scenario("retrieving the first page of customers of a node", Integration) {

      Given("a node")

      When("the user asks for the first page of customers")
      val customers = ch.getCustomers

      Then("all 10 users should be retrieved")
      customers shouldNot be (null)
      customers should have length 10
    }

    scenario("retrieving a single existing customer of a node by id", Integration) {
      Given("a node")

      When("the user asks for a customer by id")
      val customer = ch.getCustomer(customerId)

      Then("the user should be retrieved")
      customer.getId shouldBe customerId
    }

    scenario("retrieving a single non-existing customer of a node by id", Integration) {
      Given("a node")
      When("the user asks for a user that doesn't exist")
      Then("the user should not be retrieved")
      Then("an error should be thrown")

      an [HttpException] should be thrownBy ch.getCustomer("not-existing")
    }

    scenario("retrieving a single customer of a node by external id", Integration) {
      Given("a node")

      When("the user asks for a customer by external id")
      val customer = ch.getCustomerByExternalId(externalId)

      Then("the user should be retrieved")
      customer.getExternalId shouldBe externalId
    }

    scenario("retrieving a single non-existing customer of a node by external id", Integration) {
      Given("a node")
      When("the user asks for a user that doesn't exist")
      Then("the user should not be retrieved")
      Then("an error should be thrown")

      an [HttpException] should be thrownBy ch.getCustomerByExternalId("not-existing")
    }
  }

  feature("adding and deleting customers") {
    scenario("adding a customer to a node and deleting it", Integration) {
      Given("a new customer")

      When("the user adds a customer")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer);

      Then("a new customer is created")
      val id = newCustomer.getId
      id shouldNot be (null)

      When("the user deletes the customer")
      val success = ch.deleteCustomer(id)

      Then("the customer should be deleted")
      success should be (true)
      an [HttpException] should be thrownBy ch.getCustomer(id)
    }

    scenario("deleting a non-exiting customer", Integration) {
      Given("a customer id")
      val customerId = "not-existing"
      Given("a node that doesn't contain a customer with such id")

      When("the user tries to delete that customer")

      Then("no customer deleted")
      an [HttpException] should be thrownBy ch.deleteCustomer(customerId)
    }
  }

  feature("updating and patching customers") {
    scenario("updating a customer's email address") {
      Given("a customer previously added")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)

      When("the user updates the customer")
      val base = newCustomer.getBase()
      val contacts = base.getContacts()
      val newEmail = Gen.alphaStr.sample.get + "@example.com"
      contacts.setEmail(newEmail)
      base.setContacts(contacts)
      newCustomer.setBase(base)
      println(newCustomer)
      val updatedCustomer = ch.updateCustomer(newCustomer)

      Then("the customer should be updated")
      Then("the customer's id shoud not have changed")
      updatedCustomer.getId shouldBe newCustomer.getId

      Then("the customer's email should be updated")
      updatedCustomer.getBase.getContacts.getEmail shouldBe newEmail
      Then("the customer's updatedAt date should be updated")
      updatedCustomer.getUpdatedAt should be > newCustomer.getUpdatedAt
    }

    scenario("updating a non-existing customer") {
      Given("a node")
      When("the user tries to update a user that does not exist")
      val customer = genCustomer.sample.get
      customer.setId("not-existing")

      Then("the update should fail")
      an [HttpException] should be thrownBy ch.updateCustomer(customer)
    }

    scenario("patching a customer's email address") {
      Given("a customer previously added")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)

      When("the user patches the customer")
      val patchCustomer = new PatchCustomer
      val contacts = new Contacts
      val base = new BaseProperties
      val newEmail = Gen.alphaStr.sample.get + "@example.com"
      contacts.setEmail(newEmail)
      base.setContacts(contacts)
      patchCustomer.setBase(base);
      val updatedCustomer = ch.patchCustomer(newCustomer.getId, patchCustomer)

      Then("the customer should be updated")
      Then("the customer's id shoud not have changed")
      updatedCustomer.getId shouldBe newCustomer.getId
      Then("the customer's first and last name shoud not have changed")
      updatedCustomer.getBase.getFirstName shouldBe newCustomer.getBase.getFirstName
      updatedCustomer.getBase.getLastName shouldBe newCustomer.getBase.getLastName

      Then("the customer's email should be updated")
      updatedCustomer.getBase.getContacts.getEmail shouldBe newEmail
      Then("the customer's updatedAt date should be updated")
      updatedCustomer.getUpdatedAt should be > newCustomer.getUpdatedAt
    }

    scenario("patching a non-existing customer") {
      Given("a node")
      When("the user tries to update a user that does not exist")
      Then("the patch should fail")
      an [HttpException] should be thrownBy ch.patchCustomer("non-existing", new PatchCustomer)
    }
  }

}
