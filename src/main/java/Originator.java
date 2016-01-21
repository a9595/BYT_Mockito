import java.util.Map;

/**
 * Created by AndriiKovalchuk on 21.01.2016.
 */
public class Originator {
    private Map<ObserverMapKeyWrapper, Observer> state;

    public Map<ObserverMapKeyWrapper, Observer> getState() {
        return state;
    }

    public void setState(Map<ObserverMapKeyWrapper, Observer> state) {
        this.state = state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
