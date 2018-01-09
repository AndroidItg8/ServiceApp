
package itg8.com.serviceapp.feedback.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FeedbackListModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("ticket_fkid")
    @Expose
    private int ticketFkid;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String Description;
    public final static Parcelable.Creator<FeedbackListModel> CREATOR = new Creator<FeedbackListModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FeedbackListModel createFromParcel(Parcel in) {
            FeedbackListModel instance = new FeedbackListModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.ticketFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.rating = ((int) in.readValue((int.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.Description = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public FeedbackListModel[] newArray(int size) {
            return (new FeedbackListModel[size]);
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
     *     The ticketFkid
     */
    public int getTicketFkid() {
        return ticketFkid;
    }

    /**
     * 
     * @param ticketFkid
     *     The ticket_fkid
     */
    public void setTicketFkid(int ticketFkid) {
        this.ticketFkid = ticketFkid;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * 
     * @param Description
     *     The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(ticketFkid);
        dest.writeValue(rating);
        dest.writeValue(title);
        dest.writeValue(Description);
    }

    public int describeContents() {
        return  0;
    }

}
