import java.io.IOException;

/**
 * Created by AndriiKovalchuk on 11.01.2016.
 */
public class Main {

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        PageMonitor pm = new PageMonitor();
        PJObserver pjObserver = new PJObserver("http://www.pja.edu.pl/", pm);
        pm.attach(pjObserver);
        GazetaPLObserver gazetaPLObserver = new GazetaPLObserver("http://www.gazeta.pl/", pm);
        pm.attach(gazetaPLObserver);
        int i = 0;
        try {
            while (true) {
                pm.checkForModification();
                if(i == 1) {
                    originator.setState(pm.getObserversMap());
                    careTaker.add(originator.saveStateToMemento());
                }
                Thread.sleep(60 * 100);
                i++;
                if(i == 3) {
                    pm.setObserversMap(careTaker.get().getState());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
