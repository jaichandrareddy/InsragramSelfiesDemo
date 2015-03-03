package inst.app.jcr.com.instagramsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import inst.app.jcr.com.instagramsample.servicemanager.Constants;
import inst.app.jcr.com.instagramsample.view.IResponseNotifier;
import inst.app.jcr.com.instagramsample.view.UITasker;

/**
 * Created by chandra on 3/2/15.
 */
public class SplashScreen extends Activity implements IResponseNotifier {
    private String nextScreen = "inst.app.jcr.com.instagramsample.MainSelfieActivity";
    private final int SPLASH_DISPLAY_LENGHT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        UITasker uiTasker = new UITasker(Constants.NEW_REQUEST, this);
        uiTasker.execute();
    }

    @Override
    public void notifyResponse() {
        System.out.println("inside the notifyResponse () :::SPLASH SCREEN::::::::::");
        showNextScreen();
    }

    @Override
    public void notifyError(String errMessage) {

    }

    public void showNextScreen() {
        try {
            Intent mainIntent = new Intent(this, Class.forName(nextScreen));
            startActivity(mainIntent);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
