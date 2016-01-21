import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndriiKovalchuk on 11.01.2016.
 */
public class PageMonitor implements Serializable{
    private Map<ObserverMapKeyWrapper, Observer> observersMap;

    public PageMonitor() {
        observersMap = new HashMap<ObserverMapKeyWrapper, Observer>();
    }

    public void checkForModification() throws InterruptedException, IOException {
        for (ObserverMapKeyWrapper ob : observersMap.keySet()) {
            checkUrlForModification(ob);
        }
    }

    public void checkUrlForModification(ObserverMapKeyWrapper ob) throws IOException {
        URLConnection conn = ob.getUrl().openConnection();
        long longtime = conn.getLastModified();
        Date modifiedDate = new Date(longtime);
        if (ob.getLastExportDate() == null || modifiedDate.after(ob.getLastExportDate())) {
            //observer
            ob.setLastExportDate(modifiedDate);
            observersMap.get(ob).update();
        }
//        System.out.println(ob.getUrl() + " " + modifiedDate.toString());
    }

    private URL newURL(String s) throws MalformedURLException {
        return new URL(s);
    }

    public Map<ObserverMapKeyWrapper, Observer> getObserversMap() {
        return observersMap;
    }

    public void attach(Observer ob) {
        try {
            URL url = newURL(ob.getUrl());
            ObserverMapKeyWrapper key = new ObserverMapKeyWrapper();
            key.setUrl(url);
            observersMap.put(key, ob);
        } catch (MalformedURLException e) {
            System.err.println("Could not add observer, malformed url");
            e.printStackTrace();
        }
    }

    public void setObserversMap(Map<ObserverMapKeyWrapper, Observer> observersMap) {
        this.observersMap = observersMap;
    }
}
