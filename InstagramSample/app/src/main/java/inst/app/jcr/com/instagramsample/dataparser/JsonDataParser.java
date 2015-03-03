package inst.app.jcr.com.instagramsample.dataparser;

import org.json.JSONObject;

import java.util.Vector;

import inst.app.jcr.com.instagramsample.data.DataObject;
import inst.app.jcr.com.instagramsample.data.InstaImgData;
import inst.app.jcr.com.instagramsample.data.InstaPagingData;
import inst.app.jcr.com.instagramsample.view.IResponseNotifier;

/**
 * Created by chandra on 3/2/15.
 */
public class JsonDataParser {
    private static JsonDataParser _instance;
    IResponseNotifier uiNotifier = null;

    private JSONObject jsonData = null;

    public static InstaPagingData paginigData = null;

    private JsonDataParser() {

    }

    public static Vector<DataObject> imagesObj = new Vector<DataObject>();

    public static JsonDataParser getInstance() {
        if (_instance == null) {
            _instance = new JsonDataParser();
        }
        return _instance;
    }

    public void parseJsonData() {

        if (jsonData != null) {
            try {

                paginigData = new InstaPagingData();
                paginigData.setNext_max_tag_id(jsonData.getJSONObject("pagination").getString("next_max_tag_id"));
                paginigData.setDeprecation_warning(jsonData.getJSONObject("pagination").getString("deprecation_warning"));
                paginigData.setNext_max_id(jsonData.getJSONObject("pagination").getString("next_max_id"));
                paginigData.setNext_min_id(jsonData.getJSONObject("pagination").getString("next_min_id"));
                paginigData.setMin_tag_id(jsonData.getJSONObject("pagination").getString("min_tag_id"));
                paginigData.setNext_url(jsonData.getJSONObject("pagination").getString("next_url"));


                int arrayCount = (int) jsonData.getJSONArray("data").length();
//                System.out.println("pagination  :" + jsonData.getJSONObject("pagination").getString("next_url"));

                for (int i = 0; i < arrayCount; ++i) {

                    JSONObject jsonObj = jsonData.getJSONArray("data").getJSONObject(i).getJSONObject("images");
                    InstaImgData instImgObj = new InstaImgData();
                    instImgObj.setLowResImgUrl(jsonObj.getJSONObject("low_resolution").getString("url"));
                    instImgObj.setThumbnailImgUrl(jsonObj.getJSONObject("thumbnail").getString("url"));
                    instImgObj.setStandardImgUrl(jsonObj.getJSONObject("standard_resolution").getString("url"));
                    imagesObj.add(instImgObj);

//                    System.out.println("instImgObj Obj == " + instImgObj.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (uiNotifier != null) {
                uiNotifier.notifyResponse();
            }
        }
    }

    public IResponseNotifier getUiNotifier() {
        return uiNotifier;
    }

    public void setUiNotifier(IResponseNotifier uiNotifier) {
        this.uiNotifier = uiNotifier;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonString) {
        try {
            jsonData = new JSONObject(jsonString);
        } catch (Exception e) {
            System.out.println("Exceptin in setJsonData() : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Vector<DataObject> getImagesObj() {
        return imagesObj;
    }

    public static void setImagesObj(Vector<DataObject> imagesObj) {
        JsonDataParser.imagesObj = imagesObj;
    }

    public static InstaPagingData getPaginigData() {
        return paginigData;
    }

    public static void setPaginigData(InstaPagingData paginigData) {
        JsonDataParser.paginigData = paginigData;
    }
}
