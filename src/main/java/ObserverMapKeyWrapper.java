import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * Created by AndriiKovalchuk on 12.01.2016.
 */
public class ObserverMapKeyWrapper implements Serializable{
    private URL url;
    private Date lastExportDate;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Date getLastExportDate() {
        return lastExportDate;
    }

    public void setLastExportDate(Date lastExportDate) {
        this.lastExportDate = lastExportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObserverMapKeyWrapper that = (ObserverMapKeyWrapper) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return !(lastExportDate != null ? !lastExportDate.equals(that.lastExportDate) : that.lastExportDate != null);

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (lastExportDate != null ? lastExportDate.hashCode() : 0);
        return result;
    }
}
