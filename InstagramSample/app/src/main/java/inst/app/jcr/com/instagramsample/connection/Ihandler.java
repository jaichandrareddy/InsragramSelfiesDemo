package inst.app.jcr.com.instagramsample.connection;

/**
 * Created by chandra on 3/2/15.
 */
public interface Ihandler
{
    public boolean handleDownloadResponse( String respData);

    public boolean handleDownloadException(Exception e);

    public void notifyError(String exception);
}
