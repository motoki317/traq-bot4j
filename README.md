# traq-bot4j

[![GitHub release](https://img.shields.io/github/release/motoki317/traq-bot4j.svg)](https://GitHub.com/motoki317/traq-bot4j/releases/)

[traQ](https://github.com/traPtitech/traQ) bot library for Java

Requires Java 8+

## Example usage

### pom.xml

Make sure to use the latest version shown below.

- traq4j
[![GitHub release](https://img.shields.io/github/release/motoki317/traq4j.svg)](https://GitHub.com/motoki317/traq4j/releases/)
- traq-bot4j
[![GitHub release](https://img.shields.io/github/release/motoki317/traq-bot4j.svg)](https://GitHub.com/motoki317/traq-bot4j/releases/)

```xml
<project>
    <repositories>
        <repository>
            <id>GitHub</id>
            <url>https://raw.github.com/motoki317/traq4j/mvn-repo/</url>
        </repository>
        <repository>
            <id>GitHub</id>
            <url>https://raw.github.com/motoki317/traq-bot4j/mvn-repo/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.github.motoki317</groupId>
            <artifactId>traq4j</artifactId>
            <version>${traq4j.version}</version>
        </dependency>
        <dependency>
            <groupId>com.github.motoki317</groupId>
            <artifactId>traq-bot4j</artifactId>
            <version>${traq-bot4j.version}</version>
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
                  8080,
                  handlers
          );
          server.start();
    }
}
```
