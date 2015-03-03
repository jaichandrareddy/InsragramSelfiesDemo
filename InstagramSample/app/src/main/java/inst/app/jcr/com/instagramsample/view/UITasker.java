package inst.app.jcr.com.instagramsample.view;

import android.os.AsyncTask;

import inst.app.jcr.com.instagramsample.servicemanager.ServiceManager;

/**
 * Created by chandra on 3/2/15.
 */
public class UITasker extends AsyncTask<Void, Void, Void> {
    int requestType;
    ServiceManager sManager = null;
    IResponseNotifier respNotifier;

    public UITasker(int reqType, IResponseNotifier respNotifier) {
        requestType = reqType;
        this.respNotifier = respNotifier;
    }

    @Override
    protected Void doInBackground(Void... params) {
        getInstagramData(requestType);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (this.respNotifier != null) {
            respNotifier.notifyResponse();
        }
    }


    private void getInstagramData(int reqType) {
        try {
            sManager = ServiceManager.getInstance();
            sManager.setUiNotifier(this.respNotifier);
            sManager.processRequest(reqType);
        } catch (Exception e) {
            System.out.println("Exception in getInstagramData() :: " + e.getMessage());
            e.printStackTrace();
        }
    }


}