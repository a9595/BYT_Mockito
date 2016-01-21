import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by AndriiKovalchuk on 21.01.2016.
 */
public class PageMonitorTest {

    @Test
    public void checkUrlForModificationTest() throws Exception {
//        URL url = new URL("http://www.test.pl");
        Date lastModified = new Date();
        URL mockUrl = prepareUrl(lastModified);
        ObserverMapKeyWrapper ob = new ObserverMapKeyWrapper();
        ob.setUrl(mockUrl);
        ob.setLastExportDate(null);
        PageMonitor pm = new PageMonitor();
        Map<ObserverMapKeyWrapper, Observer> map = new HashMap<ObserverMapKeyWrapper, Observer>();
        PJObserver pj = Mockito.mock(PJObserver.class);
        map.put(ob, pj);
        pm.setObserversMap(map);

        pm.checkUrlForModification(ob);
        assertEquals(lastModified, ob.getLastExportDate());
    }

    @Test
    public void checkUrlForModificationNoChangeTest() throws Exception {
        Date lastModified = new Date();
        URL mockUrl = prepareUrl(lastModified);
        ObserverMapKeyWrapper ob = new ObserverMapKeyWrapper();
        ob.setUrl(mockUrl);
        Date later = (new Date());
        ob.setLastExportDate(later);
        PageMonitor pm = new PageMonitor();
        Map<ObserverMapKeyWrapper, Observer> map = new HashMap<ObserverMapKeyWrapper, Observer>();
        PJObserver pj = Mockito.mock(PJObserver.class);
        map.put(ob, pj);
        pm.setObserversMap(map);

        pm.checkUrlForModification(ob);
        assertEquals(later, ob.getLastExportDate());
    }
    @Test
    public void attachExceptionTest() {
        PageMonitor pm = new PageMonitor();
        String url = "www.test.pl";
        Observer ob = new PJObserver(url, pm);
        pm.attach(ob);
        assertNotNull(pm.getObserversMap());
        assertEquals(0, pm.getObserversMap().size());
    }
    @Test
    public void attachTest() {
        PageMonitor pm = new PageMonitor();
        String url = "http://www.test.pl";
        Observer ob = new PJObserver(url, pm);
        pm.attach(ob);
        assertNotNull(pm.getObserversMap());
        assertEquals(1, pm.getObserversMap().size());
        Set<Map.Entry<ObserverMapKeyWrapper, Observer>> set = pm.getObserversMap().entrySet();
        set.forEach(e -> {
            assertEquals(e.getValue(), ob);
            assertNotNull(e.getKey().getUrl());
            assertEquals(e.getKey().getLastExportDate(), null);
        });
    }

    private URL prepareUrl(Date lastModified) throws MalformedURLException {
        final URLConnection mockConn = Mockito.mock(URLConnection.class);
        when(mockConn.getLastModified()).thenReturn(lastModified.getTime());
        URLStreamHandler stubUrlHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return mockConn;
            }
        };
        URL url = new URL("http", "test", 99, "/test", stubUrlHandler);
        return url;
    }
}

