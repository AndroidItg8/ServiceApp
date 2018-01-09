package itg8.com.serviceapp.feedback.model;

/**
 * Created by USER-pc on 10/15/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackModel implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("flag")
    @Expose
    private Boolean flag;
    public final static Parcelable.Creator<FeedbackModel> CREATOR = new Creator<FeedbackModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FeedbackModel createFromParcel(Parcel in) {
            return new FeedbackModel(in);
        }

        public FeedbackModel[] newArray(int size) {
            return (new FeedbackModel[size]);
        }

    }
            ;

    protected FeedbackModel(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.flag = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public FeedbackModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(flag);
    }

    public int describeContents() {
        return 0;
    }

}