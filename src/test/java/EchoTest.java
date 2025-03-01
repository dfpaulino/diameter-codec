import org.example.EchoClient;
import org.example.EchoServer;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
public class EchoTest {

    Process server;
    EchoClient client;

    @BeforeEach
    public void setup() throws IOException, InterruptedException {
        //server = EchoServer.start();
        Thread.sleep(500);
        client = EchoClient.start();
    }

    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrect() throws InterruptedException {
        String resp1 = client.sendMessage("hello");
        Thread.sleep(1000);
        String resp2 = client.sendMessage("world");
        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        Thread.sleep(1000);
    }

    @AfterEach
    public  void teardown() throws IOException {
        //server.destroy();
        EchoClient.stop();
    }
}