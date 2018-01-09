package itg8.com.serviceapp.showcase.model;

/**
 * Created by USER-pc on 10/16/2017.
 */
import android.os.Parcel;
 import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName=BannerModel.TABLE_BANNER)
public class BannerModel implements Parcelable
{

     public static final String TABLE_BANNER ="TableBanner";
     public static final String FIELD_ID ="id";
     public static final String FIELD_PID ="pid";
     public static final String FIELD_NAME ="name";
     public static final String FIELD_IMAGE ="image";

    @DatabaseField(columnName = FIELD_PID , generatedId = true)
    @Expose
    private long tblId;

    @DatabaseField(columnName = FIELD_ID)
    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @DatabaseField(columnName = FIELD_NAME)
    @SerializedName("BrandName")
    @Expose
    private String brandName;
    @DatabaseField(columnName = FIELD_IMAGE)
    @SerializedName("BrandImage")
    @Expose
    private String brandImage;
    @SerializedName("lastmodified")
    @Expose
    private Object lastmodified;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<BannerModel> CREATOR = new Creator<BannerModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BannerModel createFromParcel(Parcel in) {
            return new BannerModel(in);
        }

        public BannerModel[] newArray(int size) {
            return (new BannerModel[size]);
        }

    }
            ;

    protected BannerModel(Parcel in) {
        this.pkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
        this.brandImage = ((String) in.readValue((String.class.getClassLoader())));
        this.lastmodified = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mid = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.tblId = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public BannerModel() {
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public Object getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Object lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Object getMid() {
        return mid;
    }

    public void setMid(Object mid) {
        this.mid = mid;
    }

    public Object getMdate() {
        return mdate;
    }

    public void setMdate(Object mdate) {
        this.mdate = mdate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(brandName);
        dest.writeValue(brandImage);
        dest.writeValue(lastmodified);
        dest.writeValue(mid);
        dest.writeValue(mdate);
        dest.writeValue(tblId);
    }

    public int describeContents() {
        return 0;
    }

}
