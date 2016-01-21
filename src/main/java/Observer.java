import java.io.Serializable;

/**
 * Created by AndriiKovalchuk on 11.01.2016.
 */
public abstract class Observer implements Serializable{
    protected PageMonitor pageMonitor;

    protected String url;

    public String getUrl() {
        return url;
    }

    public abstract void update();
}
