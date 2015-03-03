package inst.app.jcr.com.instagramsample.data;

/**
 * Created by chandra on 3/2/15.
 */
public class InstaImgData extends  DataObject {
    String thumbnailImgUrl;
    String standardImgUrl;
    String lowResImgUrl;

    public InstaImgData() {
    }

    public InstaImgData(String thumbnailImgUrl, String standardImgUrl, String lowResImgUrl) {
        this.thumbnailImgUrl = thumbnailImgUrl;
        this.standardImgUrl = standardImgUrl;
        this.lowResImgUrl = lowResImgUrl;
    }

    public String getThumbnailImgUrl() {
        return thumbnailImgUrl;
    }

    public void setThumbnailImgUrl(String thumbnailImgUrl) {
        this.thumbnailImgUrl = thumbnailImgUrl;
    }

    public String getStandardImgUrl() {
        return standardImgUrl;
    }

    public void setStandardImgUrl(String standardImgUrl) {
        this.standardImgUrl = standardImgUrl;
    }

    public String getLowResImgUrl() {
        return lowResImgUrl;
    }

    public void setLowResImgUrl(String lowResImgUrl) {
        this.lowResImgUrl = lowResImgUrl;
    }

    @Override
    public String toString() {
        return "InstaImgData{" +
                "thumbnailImgUrl='" + thumbnailImgUrl + '\'' +
                ", standardImgUrl='" + standardImgUrl + '\'' +
                ", lowResImgUrl='" + lowResImgUrl + '\'' +
                '}';
    }
}
