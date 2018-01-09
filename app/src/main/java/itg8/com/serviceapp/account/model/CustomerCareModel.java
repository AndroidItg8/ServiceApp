
package itg8.com.serviceapp.account.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CustomerCareModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;

    public int getBrandfkid() {
        return brandfkid;
    }

    public void setBrandfkid(int brandfkid) {
        this.brandfkid = brandfkid;
    }

    @SerializedName("brand_fkid")
    @Expose
    private int brandfkid;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("phone2")
    @Expose
    private String phone2;
    @SerializedName("email1")
    @Expose
    private String email1;
    @SerializedName("email2")
    @Expose
    private String email2;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<CustomerCareModel> CREATOR = new Creator<CustomerCareModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CustomerCareModel createFromParcel(Parcel in) {
            CustomerCareModel instance = new CustomerCareModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.brandfkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.phone1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone2 = ((String) in.readValue((String.class.getClassLoader())));
            instance.email1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.email2 = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public CustomerCareModel[] newArray(int size) {
            return (new CustomerCareModel[size]);
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
     *     The phone1
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * 
     * @param phone1
     *     The phone1
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * 
     * @return
     *     The phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * 
     * @param phone2
     *     The phone2
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * 
     * @return
     *     The email1
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * 
     * @param email1
     *     The email1
     */
    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    /**
     * 
     * @return
     *     The email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * 
     * @param email2
     *     The email2
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
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
        dest.writeValue(brandfkid);
        dest.writeValue(phone1);
        dest.writeValue(phone2);
        dest.writeValue(email1);
        dest.writeValue(email2);
        dest.writeValue(description);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return  0;
    }

}
