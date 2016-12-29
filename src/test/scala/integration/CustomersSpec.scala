package it.contactlab.hub.sdk.java.sync.test.integration;

import it.contactlab.hub.sdk.java.sync.ContactHub
import it.contactlab.hub.sdk.java.Auth
import it.contactlab.hub.sdk.java.models._, base._
import it.contactlab.hub.sdk.java.exceptions._

import java.time._

import org.scalacheck.Gen

import org.scalatest.FeatureSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen

import scala.collection.JavaConversions._

class CustomersSpec extends FeatureSpec with GivenWhenThen {

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
  val customerId = "c841ab14-b8a2-45d3-88b8-02210e2a9ebe"
  val extIdSingle = "db55ec278cd6ca385c6d6a1ae49987c2"
  val extIdMultiple = "multipleExternalIdTest"

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
      customer.id.get shouldBe customerId
    }

    scenario("retrieving a single non-existing customer of a node by id", Integration) {
      Given("a node")
      When("the user asks for a user that doesn't exist")
      Then("the user should not be retrieved")
      Then("an error should be thrown")
      an [HttpException] should be thrownBy ch.getCustomer("not-existing")
    }

    scenario("retrieving a single customer by external id", Integration) {
      Given("a node")

      When("the user asks for a customer matching an external id")
      val customers = ch.getCustomerByExternalId(extIdSingle)

      Then("the expected user should be retrieved")
      customers should have length 1
      customers.head.externalId.get shouldBe extIdSingle
    }

    scenario("retrieving multiple customers by external id", Integration) {
      Given("a node")

      When("the user asks for multiple customers matching an external id")
      val customers = ch.getCustomerByExternalId(extIdMultiple)

      Then("the expected users should be retrieved")
      customers should have length 2
      customers(0).externalId.get shouldBe extIdMultiple
      customers(1).externalId.get shouldBe extIdMultiple
    }

    scenario("retrieving a non-existing customer by external id", Integration) {
      Given("a node")
      When("the user asks for a user that doesn't exist")
      val customers = ch.getCustomerByExternalId("not-existing")

      Then("an empty list should be returned")
      customers should have length 0
    }

    scenario("reading top-level properties", Integration) {
      Given("a node")

      When("the user asks for a customer by id")
      val customer = ch.getCustomer(customerId)

      Then("the customer should have the expected id")
      customer.id.get shouldBe customerId

      And("the customer should have the expected tags")
      customer.tags.get.manual.get.toArray shouldBe Array("example-tag")
    }
  }

  feature("adding and deleting customers") {
    scenario("adding a customer to a node and deleting it", Integration) {
      Given("a new customer")
      val customer = genCustomer.sample.get

      When("the user adds a customer")
      val newCustomer = ch.addCustomer(customer);

      Then("a new customer is created")
      val id = newCustomer.id.get
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
      val base = newCustomer.base.get
      val contacts = base.contacts.get
      val newEmail = Gen.alphaStr.sample.get + "@example.com"
      val newBase = base.withContacts(contacts.withEmail(newEmail))

      val updatedCustomer = ch.updateCustomer(
        Customer.builder
          .id(newCustomer.id)
          .base(newBase)
          .build
      )

      Then("the customer should be updated")
      ch.getCustomer(newCustomer.id.get) shouldBe updatedCustomer

      Then("the customer's id should not have changed")
      updatedCustomer.id.get shouldBe newCustomer.id.get

      Then("the customer's email should be updated")
      updatedCustomer.base.get.contacts.get.email.get shouldBe newEmail

      Then("the customer's updatedAt date should be updated")
      updatedCustomer.updatedAt.get should be > newCustomer.updatedAt.get
    }

    scenario("updating a non-existing customer") {
      Given("a node")
      When("the user tries to update a user that does not exist")
      val customer = Customer.builder.id("not-existing").build

      Then("the update should fail")
      an [HttpException] should be thrownBy ch.updateCustomer(customer)
    }

    scenario("patching a customer's email address") {
      Given("a customer previously added")
      val customer = genCustomer.sample.get
      val newCustomer = ch.addCustomer(customer)

      When("the user patches the customer")
      val newEmail = Gen.alphaStr.sample.get + "@example.com"

      val patchCustomer = Customer.builder
        .base(BaseProperties.builder
          .contacts(Contacts.builder.email(newEmail).build)
          .build)
        .build;

      val updatedCustomer = ch.patchCustomer(newCustomer.id.get, patchCustomer)

      Then("the customer should be updated")
      ch.getCustomer(newCustomer.id.get) shouldBe updatedCustomer

      Then("the customer's id should not have changed")
      updatedCustomer.id shouldBe newCustomer.id

      Then("the customer's first and last name should not have changed")
      updatedCustomer.base.get.firstName shouldBe newCustomer.base.get.firstName
      updatedCustomer.base.get.lastName shouldBe newCustomer.base.get.lastName

      Then("the customer's email should be updated")
      updatedCustomer.base.get.contacts.get.email.get shouldBe newEmail
      Then("the customer's updatedAt date should be updated")
      updatedCustomer.updatedAt.get should be > newCustomer.updatedAt.get
    }

    scenario("patching a non-existing customer") {
      Given("some customer data")
      val newCustomer = genCustomer.sample.get
      When("the user tries to update a user that does not exist")
      def patch = ch.patchCustomer("non-existing", newCustomer)
      Then("the patch should fail")
      an [HttpException] should be thrownBy patch
    }
  }

  feature("creating and reading base properties") {
    scenario("creating a new customer with many base properties") {
      Given("a customer with many props")

      val base = BaseProperties.builder
        .pictureUrl(new java.net.URI("http://example.com/img.png"))
        .title("Mr")
        .prefix("Dr")
        .firstName("Mario")
        .lastName("Rossi")
        .middleName("Giacomo")
        .gender("male")
        .dob(LocalDate.parse("1990-12-12"))
        .locale("it_IT")
        .timezone(ZoneId.of("Europe/Rome"))
        .contacts(Contacts.builder
          .email(Gen.alphaStr.sample.get + "@example.com")
          .fax("123456")
          .mobilePhone("+393331234567")
          .phone("0212345678")
          .otherContacts(Seq(
            OtherContact.builder
              .`type`(OtherContactType.MOBILE).name("work").value("3337654321")
              .build))
          .mobileDevices(Seq(
            MobileDevice.builder
              .`type`(MobileDeviceType.IOS).name("iPhone").identifier("1234")
              .build))
          .build)
        .address(Address.builder
          .street("Via Malaga")
          .city("Milano")
          .country("Italy")
          .province("MI")
          .zip("20143")
          .geo(Geo.builder.lat(45.4654).lon(9.1859).build)
          .build)
        .credential(Credential.builder.username("user").password("pass").build)
        .educations(Seq(
          Education.builder
            .id("edu")
            .schoolType(SchoolType.OTHER)
            .schoolName("Politecnico di Milano")
            .schoolConcentration("Software Engineering")
            .startYear(2000)
            .endYear(2005)
            .isCurrent(false)
            .build))
        .likes(Seq(
          Like.builder
            .id("like1")
            .category("cat1")
            .name("foobar")
            .createdTime(OffsetDateTime.now())
            .build))
        .socialProfile(SocialProfile.builder
          .facebook("https://www.facebook.com/ContactLab")
          .twitter("https://twitter.com/ContactLab")
          .build)
        .jobs(Seq(
          Job.builder
            .id("contactlab")
            .companyIndustry("Marketing")
            .companyName("ContactLab")
            .jobTitle("Software Engineer")
            .startDate(LocalDate.parse("2016-09-01"))
            .isCurrent(true)
            .build))
        .subscriptions(Seq(
          Subscription.builder
            .id("sub")
            .name("ContactLab News")
            .`type`("Newsletter")
            .kind(SubscriptionKind.DIGITAL_MESSAGE)
            .subscribed(true)
            .startDate(OffsetDateTime.parse("2016-01-01T00:00:00Z"))
            .endDate(OffsetDateTime.parse("2018-01-01T00:00:00Z"))
            .subscriberId("ASD123")
            .registeredAt(OffsetDateTime.parse("2016-05-10T00:00:00Z"))
            .updatedAt(OffsetDateTime.parse("2016-05-10T00:00:00Z"))
            .preferences(Seq(
              Preference.builder.key("key1").value("value1").build,
              Preference.builder.key("key2").value("value2").build
            ))
            .build))
        .build

      val customer = Customer.builder
        .base(base)
        .build

      When("the user creates and retrieves the customer")
      val newCustomer = ch.addCustomer(customer)
      val retrievedCustomer = ch.getCustomer(newCustomer.id.get)

      Then("all the properties match")
      retrievedCustomer.base.get shouldBe base
      ch.deleteCustomer(retrievedCustomer.id.get)
    }
  }

}
