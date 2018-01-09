
package itg8.com.serviceapp.account.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class KnowledgeModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("brandid")
    @Expose
    private int brandid;
    @SerializedName("AppName")
    @Expose
    private String AppName;
    @SerializedName("AppLink")
    @Expose
    private String AppLink;
    @SerializedName("productfkid")
    @Expose
    private int productfkid;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<KnowledgeModel> CREATOR = new Creator<KnowledgeModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public KnowledgeModel createFromParcel(Parcel in) {
            KnowledgeModel instance = new KnowledgeModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.brandid = ((int) in.readValue((int.class.getClassLoader())));
            instance.AppName = ((String) in.readValue((String.class.getClassLoader())));
            instance.AppLink = ((String) in.readValue((String.class.getClassLoader())));
            instance.productfkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.time = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.domain = ((String) in.readValue((String.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public KnowledgeModel[] newArray(int size) {
            return (new KnowledgeModel[size]);
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
     *     The brandid
     */
    public int getBrandid() {
        return brandid;
    }

    /**
     * 
     * @param brandid
     *     The brandid
     */
    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    /**
     * 
     * @return
     *     The AppName
     */
    public String getAppName() {
        return AppName;
    }

    /**
     * 
     * @param AppName
     *     The AppName
     */
    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    /**
     * 
     * @return
     *     The AppLink
     */
    public String getAppLink() {
        return AppLink;
    }

    /**
     * 
     * @param AppLink
     *     The AppLink
     */
    public void setAppLink(String AppLink) {
        this.AppLink = AppLink;
    }

    /**
     * 
     * @return
     *     The productfkid
     */
    public int getProductfkid() {
        return productfkid;
    }

    /**
     * 
     * @param productfkid
     *     The productfkid
     */
    public void setProductfkid(int productfkid) {
        this.productfkid = productfkid;
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
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 
     * @param domain
     *     The domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 
     * @return
     *     The mid
     */
    public Object getMid() {
        return mid;
    }

    /**
     * 
     * @param mid
     *     The mid
     */
    public void setMid(Object mid) {
        this.mid = mid;
    }

    /**
     * 
     * @return
     *     The mdate
     */
    public Object getMdate() {
        return mdate;
    }

    /**
     * 
     * @param mdate
     *     The mdate
     */
    public void setMdate(Object mdate) {
        this.mdate = mdate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(brandid);
        dest.writeValue(AppName);
        dest.writeValue(AppLink);
        dest.writeValue(productfkid);
        dest.writeValue(time);
        dest.writeValue(description);
        dest.writeValue(domain);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return  0;
    }

}
