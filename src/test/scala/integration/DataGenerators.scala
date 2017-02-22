package it.contactlab.hub.sdk.java.test.integration

import it.contactlab.hub.sdk.java.models._

import org.scalacheck.Gen

trait DataGenerators {

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

}
