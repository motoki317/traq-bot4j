# traq-bot4j

[ ![Download](https://api.bintray.com/packages/motoki317/traq4j/traq-bot4j/images/download.svg) ](https://bintray.com/motoki317/traq4j/traq-bot4j/_latestVersion)

[traQ](https://github.com/traPtitech/traQ) bot library for Java
Requires Java 8+

## Example usage

### pom.xml

Make sure to use the latest version

traq-bot4j [ ![Download](https://api.bintray.com/packages/motoki317/traq4j/traq-bot4j/images/download.svg) ](https://bintray.com/motoki317/traq4j/traq-bot4j/_latestVersion)
traq4j [ ![Download](https://api.bintray.com/packages/motoki317/traq4j/traq4j/images/download.svg) ](https://bintray.com/motoki317/traq4j/traq4j/_latestVersion)

```xml
<project>
    <repositories>
        <repository>
            <id>jcenter</id>
            <name>jcenter-bintray</name>
            <url>https://jcenter.bintray.com</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.github.motoki317</groupId>
            <artifactId>traq4j</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.github.motoki317</groupId>
            <artifactId>traq-bot4j</artifactId>
            <version>3.0.0</version>
        </dependency>
    </dependencies>
</project>
```

### Code

```java
public class Main {
    public static void main(String[] args){
          EventHandlers handlers = new EventHandlers();
          handlers.setDirectMessageCreatedHandler((e, r) -> {
              if (e.getMessage().getPlainText().equals("ping")) {
                  try {
                      r.respond("pong!", false);
                  } catch (ApiException ex) {
                      ex.printStackTrace();
                  }
              }
          });
          BotServer server = new BotServer(
                  System.getenv("VERIFICATION_TOKEN"),
                  System.getenv("ACCESS_TOKEN"),
                  25565,
                  handlers
          );
          server.start();
    }
}
```
