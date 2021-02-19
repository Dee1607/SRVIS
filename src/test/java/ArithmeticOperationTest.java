import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



    public class ArithmeticOperationTest {

        @Test
        public void addTest() {
            ArithmeticOperation a=new ArithmeticOperation();
            assertEquals(2, a.add(1,1));
        }
    }

