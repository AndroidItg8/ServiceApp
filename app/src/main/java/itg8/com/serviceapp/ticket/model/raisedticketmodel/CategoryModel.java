package itg8.com.serviceapp.ticket.model.raisedticketmodel;

/**
 * Created by USER-pc on 10/15/2017.
 */
 import android.os.Parcel;
 import android.os.Parcelable;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class CategoryModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @SerializedName("categoryname")
    @Expose
    private String categoryname;
    @SerializedName("lastmodified")
    @Expose
    private Object lastmodified;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        public CategoryModel[] newArray(int size) {
            return (new CategoryModel[size]);
        }

    }
            ;

    protected CategoryModel(Parcel in) {
        this.pkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.categoryname = ((String) in.readValue((String.class.getClassLoader())));
        this.lastmodified = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mid = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public CategoryModel() {
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
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
        dest.writeValue(categoryname);
        dest.writeValue(lastmodified);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return 0;
    }

}
