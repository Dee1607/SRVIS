import org.junit.jupiter.api.Test;
import sum.Hello;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @Test
    void addTest() {

        Hello h=new Hello();
        int c=h.add(1,2);
        assertEquals(3,c);
    }
}