# ContactHUB Java SDK

## Private repository for beta

Until this library reaches 1.0, it's only available from a private Maven
repository at https://buildo-private-maven.appspot.com/

If you have a valid account for this repository, add it to your build tool
configuration.

### sbt

Add the following lines to your `build.sbt`:

```scala
resolvers += "buildo private maven" at "https://buildo-private-maven.appspot.com/"
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
```

Then create a file '~/.ivy2/.credentials' with the following content:

```
realm=
host=buildo-private-maven.appspot.com
user=user
password=pass
```


### Gradle

Add this to your project's `build.gradle`:

```
repositories {
  mavenCentral()
  maven {
    credentials {
      username "user"
      password "pass"
    }
    url "https://buildo-private-maven.appspot.com"
  }
}
```

Then add your credentials to `~/.gradle/gradle.properties`:

```
buildoMavenUser=user
buildoMavenPassword=pass
```

### Maven

Add this to your project's `pom.xml`:

```xml
  <repositories>
    <repository>
      <id>buildo-private-maven.appspot.com</id>
      <url>https://buildo-private-maven.appspot.com</url>
    </repository>
  </repositories>
```

Then add your credentials to `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>buildo-private-maven.appspot.com</id>
      <configuration>
        <httpHeaders>
          <property>
            <name>Authorization</name>
            <!-- Encode the string "user:pass" using https://www.base64encode.org/ -->
            <value>Basic dXNlcjpwYXNz</value>
          </property>
        </httpHeaders>
      </configuration>
    </server>
  </servers>
</settings>
```


## Adding this library to your project's dependencies

### sbt

In your `build.sbt`:

```scala
libraryDependencies += "it.contactlab.hub" % "sdk-java" % "0.3.0"
```

### Gradle

In your `build.gradle`:

```
dependencies {
  compile 'it.contactlab.hub:sdk-java:0.3.0
}
```

### Maven

In your `pom.xml`:

```xml
<dependency>
  <groupId>it.contactlab.hub</groupId>
  <artifactId>sdk-java</artifactId>
  <version>0.3.0</version>
</dependency>
```


## Importing this library in your project

```java
import it.contactlab.hub.sdk.java.sync.ContactHub;
```

We also provide a separate `async` implementation where all the methods return a
Java8 [CompletionStage&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletionStage.html).

If you wish the use the Async api, import from the `async` package instead:

```java
import it.contactlab.hub.sdk.java.async.ContactHub;
```

All the methods documented below are available in both packages.


## Authenticating

Find your token, workspaceId and nodeId in the ContactHub dashboard. Then
instantiate a new ContactHub object like this:

```java
Auth auth = new Auth(token, workspaceId, nodeId);

ContactHub ch = new ContactHub(auth);
```

## Session API

### createSessionId

Generate a new sessionId.

```java
String sessionId = ch.createSessionId();
```

### addCustomerSession

Reconcile a sessionId with an existing Customer. Use this if you want to
associate anonymous events (containing a sessionId) with an existing customerId.

```java
Boolean success = ch.addCustomerSession(customerId, sessionId);
```


## Customer API

### getCustomer

Retrieve a customer by its id.

```java
Customer customer = ch.getCustomer("a-valid-customer-id");
```

### getCustomers

Retrieve all the Customers in a Node.

```java
List<Customer> customers = ch.getCustomers();
```

### getCustomerByExternalId

Retrieve all the Customers matching the given external id.

```java
List<Customer> customers = ch.getCustomerByExternalId("an-external-id");
```

Please note you can have more than one customer with the same external id,
unless you include "external id" in the "matching policies" for your workspace.

### addCustomer

Add a new Customer. This method returns a new Customer object including the id that was
assigned to the new customer by the API.

```java
Customer newCustomer = addCustomer(Customer customer)
```

If the "Customer uniqueness" configuration for your workspace is set to "Merge"
and the new customer data matches an existing customer according to the
"matching policies" for your workspace, this method will return the merged
Customer data.

To create a `Customer` instance that this method requires as its argument, use the
builder provided by the `Customer` object.

```java
Customer.builder()
        .base(...)
        .extended(...)
        // ...
        .build();
```

### deleteCustomer

Delete the Customer with the specified id.

```java
deleteCustomer("an-existing-id");
```

### updateCustomer

Update an existing Customer by replacing all of its data with the data provided.

```java
Customer updatedCustomer = updateCustomer(Customer newCustomer);
```

This method will fail if the id of `newCustomer` doesn't match an existing
customer.

### patchCustomer

Update an existing Customer (identified by `id`) by merging its existing data
with the data provided.

```java
Customer updatedCustomer = patchCustomer(String id, Customer newCustomerData);
```

Customer properties that were already set and are included in the new data will
be overwritten.

Customer properties that were already set and are not specified will be left
untouched.

The merge is performed API-side. If you want more control on how the data is
merged, retrieve the customer with `getCustomer()`, update the properties
locally and overwrite the whole customer with `updateCustomer()`.


## Education API

Convenience methods to add/update/remove the Education objects of a Customer.
You can perform the same operations also using `patchCustomer`.

### addEducation

```java
Customer updatedCustomer = ch.addEducation(customerId, Education education);
```

### updateEducation

```java
Customer updatedCustomer = ch.updateEducation(customerId, Education education);
```

### removeEducation

```java
Customer updatedCustomer = ch.removeEducation(customerId, String educationId);
```


## Like API

Convenience methods to add/update/remove the Like objects of a Customer.
You can perform the same operations also using `patchCustomer`.

### addLike

```java
Customer updatedCustomer = ch.addLike(customerId, Like like);
```

### updateLike

```java
Customer updatedCustomer = ch.updateLike(customerId, Like like);
```

### removeLike

```java
Customer updatedCustomer = ch.removeLike(customerId, String likeId);
```


## Job API

Convenience methods to add/update/remove the Job objects of a Customer.
You can perform the same operations also using `patchCustomer`.

### addJob

```java
Customer updatedCustomer = ch.addJob(customerId, Job job);
```

### updateJob

```java
Customer updatedCustomer = ch.updateJob(customerId, Job job);
```

### removeJob

```java
Customer updatedCustomer = ch.removeJob(customerId, String jobId);
```


## Tag API

### addTag

Add a tag to a Customer. If the tag is already present, nothing will be done.

This is just a convenience method, as the same result could be achieved using
`getCustomer` and `patchCustomer`.

```java
Customer updatedCustomer = ch.addTag(customerId, "a-new-tag");
```

### removeTag

Remove a tag from a Customer. If the tag is already present, nothing will be
done.

This is just a convenience method, as the same result could be achieved using
`getCustomer` and `patchCustomer`.

```java
Customer updatedCustomer = ch.addTag(customerId, "a-tag-to-remove");
```


## Event API

### getEvent

Retrieve an event by its id.

```java
Event event = ch.getEvent("a-valid-event-id");
```

### getEvents

Retrieve all the events for a customer.

```java
List<Event> events = ch.getEvents(customerId);
```

### addEvent

Add a new Event. This method returns `true` if the API has successfully queued
the event for insertion. The API will then process the queue asynchronously, it
can take a few seconds for an event to be actually stored.

```java
Boolean queued = addEvent(Event event)
```

To create a `Event` instance, use the builder provided by the `Event` object.

```java
Event.builder()
  .customerId(customerId)
  .context("WEB")
  .type("viewedPage")
  .properties(eventProperties)
  .build();
```

`eventProperties` must be an instance of `com.google.gson.JsonObject`, the
structure of this object varies depending on the event type and always contains
a number of optional fields, so you should only include the properties you need.

For example, this would generate a valid `eventProperties` object for a
`viewedPage` event:

```java
val eventProperties = new JsonObject;
eventProperties.addProperty("url", "https://example.com/");
eventProperties.addProperty("title", "Page Title");
```


## Examples

Check the [example](example/) folder for working examples you can download and
try out.

### Running the example with sbt

```sh
sbt example/run
```

### Running the example with Gradle

```sh
cd example
gradle run
```

### Running the example with Maven

```sh
cd example
mvn compile
mvn exec:java
```


## Contributing to this library

Despite this being a Java library, we use [sbt](http://www.scala-sbt.org/) as
the build tool.

`sbt compile` will compile all the Java sources to `target/classes`

`sbt package` will package the compiled files in a JAR file under `target`

`sbt doc` will generate HTML JavaDoc in `target/api`

`sbt packageDoc` will package the javadoc files in a JAR file under `target`


### Immutables

This library uses [Immutables](http://immutables.github.io/) to generate
high-quality, immutable implementations of the domain models.

This dramatically reduces Java boilerplate code. For example, only an
`AbstractCustomer` model is present in the `src` folder. When you compile it,
the `Customer` implementation will be automatically generated by Immutables and
saved as `target/classes/.../Customer.java`.
