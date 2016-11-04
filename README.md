# ContactHUB Java SDK

## Install

#### Maven
```xml
<dependency>
  <groupId>it.contactlab.hub</groupId>
  <artifactId>java-sdk</artifactId>
  <version>LATEST</version>
</dependency>
```

#### sbt
```scala
libraryDependencies += "it.contactlab.hub" % "java-sdk" % "<LATEST>"
```

## Quickstart

### Sync SDK

```java
Auth auth = new Auth(token, workspaceId, nodeId);

ContactHub ch = new ContactHub(auth);

List<Customer> cc = ch.getCustomers();
System.out.println(cc);
```

### Async SDK

```java
Auth auth = new Auth(token, workspaceId, nodeId);

ContactHub ch = new ContactHub(auth);

await(ch.getCustomers().whenComplete((cc, ex) -> System.out.println(cc)));
```

Check the [example](example/) folder for complete implementations.
