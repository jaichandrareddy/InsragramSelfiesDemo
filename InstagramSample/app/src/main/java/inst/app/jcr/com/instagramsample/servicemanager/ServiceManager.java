package inst.app.jcr.com.instagramsample.servicemanager;

import android.os.AsyncTask;

import inst.app.jcr.com.instagramsample.connection.HttpConnectionHandler;
import inst.app.jcr.com.instagramsample.connection.Ihandler;
import inst.app.jcr.com.instagramsample.data.InstaPagingData;
import inst.app.jcr.com.instagramsample.dataparser.JsonDataParser;
import inst.app.jcr.com.instagramsample.view.IResponseNotifier;

/**
 * Created by chandra on 3/2/15.
 */
public class ServiceManager implements Ihandler {


    String accessToken = "1731662053.1677ed0.bdef3b9fe26341e69083981a3dc57729";
    String instagramUrl = "https://api.instagram.com/v1/tags/selfies/media/recent?access_token=";
    String count = "&count=30";
    private static ServiceManager _instance;
    public final String TAG = "ServiceManager";
    IResponseNotifier uiNotifier = null;

    public static ServiceManager getInstance() {
        if (_instance == null) {
            _instance = new ServiceManager();
        }
        return _instance;
    }

    public boolean processRequest(int reqType) {

        switch (reqType) {
            case Constants.NEW_REQUEST:
                sendNewDataRequest();
                break;
            case Constants.NEXT_PAGE_REQUEST:
                sendNextPageRequest();
                break;


            default:
                System.out.println(TAG + " : in the default case ::::: ");
                break;
        }

        return false;
    }

    HttpConnectionHandler httpConHandler;


    public void sendNewDataRequest() {

        execute(instagramUrl + accessToken + count);

    }

    public void sendNextPageRequest() {
        InstaPagingData pagingData = JsonDataParser.getPaginigData();
        execute(pagingData.getNext_url());
    }

    private void execute(String urlString) {
//        ServiceManagerTask request = new ServiceManagerTask(urlString,this);
//        request.execute();
        httpConHandler = new HttpConnectionHandler(urlString, this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpConHandler.executeHttpGET();
            }
        }).start();

    }

    @Override
    public boolean handleDownloadResponse(String respData) {

        JsonDataParser jsonParser = JsonDataParser.getInstance();
        jsonParser.setJsonData(respData);
        jsonParser.setUiNotifier(uiNotifier);
        jsonParser.parseJsonData();
        return false;
    }

    @Override
    public boolean handleDownloadException(Exception e) {
        return false;
    }

    @Override
    public void notifyError(String exception) {

    }

    public IResponseNotifier getUiNotifier() {
        return uiNotifier;
    }

    public void setUiNotifier(IResponseNotifier uiNotifier) {
        this.uiNotifier = uiNotifier;
    }

    private class ServiceManagerTask extends AsyncTask<Void, Void, Void> {
        private String url;
        private Ihandler handler;

        public ServiceManagerTask(String url, Ihandler handler) {
            super();
            this.url = url;
            this.handler = handler;
        }

        @Override
        protected Void doInBackground(Void... params) {
            System.out.println("in the doInBackground() ::::" + url);
            httpConHandler = new HttpConnectionHandler(url, handler);
            httpConHandler.executeHttpGET();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

        }

    }

}
