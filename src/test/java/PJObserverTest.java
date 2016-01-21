import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by AndriiKovalchuk on 21.01.2016.
 */
public class PJObserverTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void updateTest() {
        PJObserver ob = new PJObserver("www.test.pl", null);
        ob.update();
        assertEquals("page www.test.pl was updated\n",outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
