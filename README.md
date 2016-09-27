# ContactHUB Java SDK

## Install

#### Maven
```xml
<dependency>
  <groupId>com.contactlab.hub</groupId>
  <artifactId>java-sdk</artifactId>
  <version>LATEST</version>
</dependency>
```

#### sbt
```scala
libraryDependencies += "com.contactlab.hub" % "sdk.java" % "<LATEST>"
```

## Quickstart

```java
import com.contactlab.hub.Node;

Node node = new Node("12123123-1233-1231-1231-examplenodeid", "12123123-1233-1231-1231-exampleworkspaceid");
node.getCustomers().whenComplete((customers, e) -> {
  if (e != null) {
    System.err.println(e);
  } else {
    System.out.println(customers);
  }
});
```
