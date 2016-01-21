import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by AndriiKovalchuk on 21.01.2016.
 */
public class OriginatorTest {

    @Test
    public void saveStateToMementoTest() {
        Originator or = new Originator();
        Map<ObserverMapKeyWrapper, Observer> state = new HashMap<ObserverMapKeyWrapper, Observer>();
        or.setState(state);
        Memento mem = or.saveStateToMemento();
        assertNotNull(mem);
        assertSame(state,mem.getState());
    }

    @Test
    public void getStateFromMementoTest() {
        Originator or = new Originator();
        Map<ObserverMapKeyWrapper, Observer> state = new HashMap<ObserverMapKeyWrapper, Observer>();
        Memento memento = new Memento(state);
        or.getStateFromMemento(memento);
        assertSame(state, or.getState());

    }
}
