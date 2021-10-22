import com.epam.kafka.*;
import org.junit.jupiter.api.*;


public class Tests {

    private static BasicProducer basicProducer;

    @BeforeAll
    public static void init() {
        basicProducer = BasicProducer.getInstance();
    }


    @Test
    public void SendToFirstClassFirstMethod() {
        basicProducer.sendToKafka(1, 1);
    }

    @Test
    public void SendToFirstClassSecondMethod() {
        basicProducer.sendToKafka(1, 2);
    }

    @Test
    public void SendToFirstClassThirdMethod() {
        basicProducer.sendToKafka(1, 3);
    }

}
