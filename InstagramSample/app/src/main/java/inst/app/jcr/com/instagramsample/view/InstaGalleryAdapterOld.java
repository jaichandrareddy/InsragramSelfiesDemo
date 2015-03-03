package inst.app.jcr.com.instagramsample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.Vector;

import inst.app.jcr.com.instagramsample.R;
import inst.app.jcr.com.instagramsample.data.DataObject;
import inst.app.jcr.com.instagramsample.data.InstaImgData;
import inst.app.jcr.com.instagramsample.dataparser.JsonDataParser;
import inst.app.jcr.com.instagramsample.servicemanager.Constants;
import inst.app.jcr.com.instagramsample.servicemanager.DownloadImageTask;

/**
 * Created by chandra on 3/2/15.
 */
public class InstaGalleryAdapterOld extends BaseAdapter {
    public final String TAG = "InstaGalleryAdapter";
    private Context context;
    private LayoutInflater layoutInflater;
    Vector<DataObject> imgObjVect;
    private static int MAX_IMAGES_COUNT = 3;
    int offset = 1;
    private View.OnClickListener onClickListener;

    public InstaGalleryAdapterOld(Context context) {
        this.context = context;
        init();
    }

    private void init() {

        try {
            count = 0;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception in the init() :   " + e.getMessage());
            e.printStackTrace();
        }
    }

    static int count = 0;
    ImageView instaGalImgBig = null;
    ImageView instaGalImgSml1 = null;
    ImageView instaGalImgSml2 = null;

    public View getView(int position, View convertView, ViewGroup parent) {

        View currentView = convertView;
        System.out.println("in the getView of instaGallery Adapter :::::::::::::::::::::position:::" + position);


        try {
            if (currentView == null) {
                currentView = layoutInflater.inflate(R.layout.image_gallery_adapter, null);
            }
            instaGalImgBig = (ImageView) currentView.findViewById(R.id.evnImgBig);
            instaGalImgSml1 = (ImageView) currentView.findViewById(R.id.evnSmall1);
            instaGalImgSml2 = (ImageView) currentView.findViewById(R.id.evnSmall2);
            instaGalImgBig.setOnClickListener(onClickListener);
            instaGalImgSml1.setOnClickListener(onClickListener);
            instaGalImgSml2.setOnClickListener(onClickListener);

//          getImages();
//            getImages2(position);
            getImages3(position);

        } catch (Exception e) {
            System.out.println("Exception in the getView() : " + e.getMessage());
            e.printStackTrace();
        }
        return currentView;
    }

    private void getImages3(int position) {

        InstaImgData imgObj = null;

        if (count > 0) {
            position = (position * MAX_IMAGES_COUNT) + offset;
        }

        if (position < imgObjVect.size()) {
            if (imgObjVect != null) {
                imgObj = (InstaImgData) imgObjVect.get(position);
            }
            if (imgObj != null) {

                if (imgObj.getThumbnailImgUrl() != null && imgObj.getThumbnailImgUrl().length() > 0) {

                    downloadImage(instaGalImgBig, imgObj.getThumbnailImgUrl());
                    instaGalImgBig.setTag(imgObj.getStandardImgUrl());

                    String sml1Url = ((InstaImgData) imgObjVect.get((position + 1))).getThumbnailImgUrl();
                    downloadImage(instaGalImgSml1, sml1Url);
                    instaGalImgSml1.setTag(sml1Url);

                    String sml2Url = ((InstaImgData) imgObjVect.get((position + 2))).getThumbnailImgUrl();
                    downloadImage(instaGalImgSml2, sml2Url);
                    instaGalImgSml2.setTag(sml2Url);

                }
                ++count;
            }
        }
    }


    public void downloadImage(ImageView imgView, String imgUrl) {
        DownloadImageTask downloadImg = new DownloadImageTask(imgView);
        downloadImg.execute(new String[]{imgUrl});
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public DataObject getItem(int position) {
        // TODO Auto-generated method stub
        if ((imgObjVect != null) && ((position >= 0) && (position < imgObjVect.size()))) {
            return imgObjVect.get(position);
        }
        return null;
    }

    public int getCount() {
        // TODO Auto-generated method stub

        int count = (imgObjVect != null ? imgObjVect.size() : 0);
        return (count / MAX_IMAGES_COUNT);
    }

    public Vector<DataObject> getImgObjVect() {
        return imgObjVect;
    }

    public void setImgObjVect(Vector<DataObject> imgObjVect) {
        this.imgObjVect = imgObjVect;
    }

    public void refreshImgDataObj() {
        imgObjVect = JsonDataParser.getImagesObj();
    }


    public void setClickListener(View.OnClickListener listener) {
        onClickListener = listener;
    }
}
