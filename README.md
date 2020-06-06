# traq-bot4j

[traQ](https://github.com/traPtitech/traQ) bot library for Java

## Example usage

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
