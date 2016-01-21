/**
 * Created by AndriiKovalchuk on 12.01.2016.
 */
public class PJObserver extends Observer {

    public PJObserver(String url, PageMonitor pageMonitor) {
        this.pageMonitor = pageMonitor;
        this.url = url;
    }
    @Override
    public void update() {
        System.out.println("page " + url + " was updated");
    }
}
