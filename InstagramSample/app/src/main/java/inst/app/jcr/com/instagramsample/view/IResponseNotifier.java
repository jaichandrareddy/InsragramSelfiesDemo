package inst.app.jcr.com.instagramsample.view;

/**
 * Created by chandra on 3/2/15.
 */
public interface IResponseNotifier {
    public void notifyResponse();

    public void notifyError(String errMessage);

}
