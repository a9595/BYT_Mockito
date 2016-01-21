/**
 * Created by AndriiKovalchuk on 12.01.2016.
 */
public class GazetaPLObserver extends Observer {

    public GazetaPLObserver(String url, PageMonitor pageMonitor) {
        this.pageMonitor = pageMonitor;
        this.url = url;
    }

    @Override
    public void update() {
        System.out.println("page " + url + " was updated");
    }
}
