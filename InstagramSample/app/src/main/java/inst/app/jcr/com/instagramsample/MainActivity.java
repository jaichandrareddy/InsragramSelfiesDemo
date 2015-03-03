package inst.app.jcr.com.instagramsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import org.json.JSONObject;

import inst.app.jcr.com.instagramsample.dataparser.JsonDataParser;
import inst.app.jcr.com.instagramsample.servicemanager.ServiceManager;
import inst.app.jcr.com.instagramsample.view.IResponseNotifier;
import inst.app.jcr.com.instagramsample.view.InstaGalleryAdapterOld;

/**
 * Created by chandra on 3/2/15.
 */
public class MainActivity extends Activity implements IResponseNotifier, View.OnClickListener {

    private JSONObject imageData;
    private GridView gridView;
    ServiceManager sManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        gridView = (GridView) findViewById(R.id.image_grid_view);
        InstaGalleryAdapterOld galleryAdapter = new InstaGalleryAdapterOld(this);
        galleryAdapter.setImgObjVect(JsonDataParser.getInstance().getImagesObj());
        galleryAdapter.setClickListener(this);
        gridView.setAdapter(galleryAdapter);
        System.out.println("in the inti() of main activity ::::::::::::");

    }

    @Override
    public void notifyResponse() {
//        JsonDataParser jsonParser = JsonDataParser.getInstance();
//        System.out.println("in the notifyResponse() :: " + jsonParser.getPaginigData().toString());
//        System.out.println("in the notifyResponse() :: " + jsonParser.getImagesObj());

    }

    @Override
    public void notifyError(String errMessage) {

    }


    @Override
    public void onClick(View v) {
        System.out.println("in the onClick() :::" + v.getTag().toString());
        Intent imgViewer = new Intent(MainActivity.this, ViewImages.class);
        imgViewer.putExtra("std_url", v.getTag().toString());
        startActivity(imgViewer);

    }
}
