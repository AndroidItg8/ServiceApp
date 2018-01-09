
package itg8.com.serviceapp.account.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class VideoModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("productid")
    @Expose
    private int productid;
    @SerializedName("brandfkid")
    @Expose
    private int brandfkid;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("defination")
    @Expose
    private String defination;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("mdate")
    @Expose
    private String mdate;
    public final static Parcelable.Creator<VideoModel> CREATOR = new Creator<VideoModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VideoModel createFromParcel(Parcel in) {
            VideoModel instance = new VideoModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.productid = ((int) in.readValue((int.class.getClassLoader())));
            instance.brandfkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.time = ((String) in.readValue((String.class.getClassLoader())));
            instance.defination = ((String) in.readValue((String.class.getClassLoader())));
            instance.path = ((String) in.readValue((String.class.getClassLoader())));
            instance.mid = ((String) in.readValue((String.class.getClassLoader())));
            instance.mdate = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public VideoModel[] newArray(int size) {
            return (new VideoModel[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The pkid
     */
    public int getPkid() {
        return pkid;
    }

    /**
     * 
     * @param pkid
     *     The pkid
     */
    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    /**
     * 
     * @return
     *     The productid
     */
    public int getProductid() {
        return productid;
    }

    /**
     * 
     * @param productid
     *     The productid
     */
    public void setProductid(int productid) {
        this.productid = productid;
    }

    /**
     * 
     * @return
     *     The brandfkid
     */
    public int getBrandfkid() {
        return brandfkid;
    }

    /**
     * 
     * @param brandfkid
     *     The brandfkid
     */
    public void setBrandfkid(int brandfkid) {
        this.brandfkid = brandfkid;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The defination
     */
    public String getDefination() {
        return defination;
    }

    /**
     * 
     * @param defination
     *     The defination
     */
    public void setDefination(String defination) {
        this.defination = defination;
    }

    /**
     * 
     * @return
     *     The path
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param path
     *     The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * @return
     *     The mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * 
     * @param mid
     *     The mid
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * 
     * @return
     *     The mdate
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 
     * @param mdate
     *     The mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(productid);
        dest.writeValue(brandfkid);
        dest.writeValue(time);
        dest.writeValue(defination);
        dest.writeValue(path);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return  0;
    }

}
