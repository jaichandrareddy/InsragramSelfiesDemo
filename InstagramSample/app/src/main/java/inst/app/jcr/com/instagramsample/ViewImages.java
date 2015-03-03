package inst.app.jcr.com.instagramsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import inst.app.jcr.com.instagramsample.servicemanager.DownloadImageTask;

/**
 * Created by chandra on 3/2/15.
 */
public class ViewImages extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer);
        Intent i = getIntent();
        String url = i.getExtras().getString("std_url");

        if (url!=null && url.length() > 0) {
            imageView = (ImageView) findViewById(R.id.fullscreen_imgViewer);
            DownloadImageTask downloadImg = new DownloadImageTask(imageView);
            downloadImg.execute(new String[]{url});

        }
    }
}
