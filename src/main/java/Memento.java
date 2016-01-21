import java.io.Serializable;
import java.util.Map;

/**
 * Created by AndriiKovalchuk on 21.01.2016.
 */
public class Memento implements Serializable {
    private Map<ObserverMapKeyWrapper, Observer> state;

    public Memento(Map<ObserverMapKeyWrapper, Observer> state) {
        this.state = state;
    }

    public Map<ObserverMapKeyWrapper, Observer> getState() {
        return state;
    }
}
