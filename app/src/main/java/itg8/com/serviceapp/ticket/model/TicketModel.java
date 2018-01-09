
package itg8.com.serviceapp.ticket.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class TicketModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("Customer_fkid")
    @Expose
    private String CustomerFkid;
    @SerializedName("Invoice_fkid")
    @Expose
    private int InvoiceFkid;
    @SerializedName("AssignDate")
    @Expose
    private String AssignDate;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName("Assignedpersonname")
    @Expose
    private String Assignedpersonname;
    @SerializedName("AssignedContactno")
    @Expose
    private String AssignedContactno;
    @SerializedName("SolveDate")
    @Expose
    private String SolveDate;
    @SerializedName("lastModifieddate")
    @Expose
    private String lastModifieddate;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    @SerializedName("Problem_fkid")
    @Expose
    private int ProblemFkid;
    @SerializedName("OtherProblem")
    @Expose
    private String OtherProblem;
    @SerializedName("Product_fkid")
    @Expose
    private int ProductFkid;
    public final static Parcelable.Creator<TicketModel> CREATOR = new Creator<TicketModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TicketModel createFromParcel(Parcel in) {
            TicketModel instance = new TicketModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.CustomerFkid = ((String) in.readValue((String.class.getClassLoader())));
            instance.InvoiceFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.AssignDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.Description = ((String) in.readValue((String.class.getClassLoader())));
            instance.Status = ((String) in.readValue((String.class.getClassLoader())));
            instance.Assignedpersonname = ((String) in.readValue((String.class.getClassLoader())));
            instance.AssignedContactno = ((String) in.readValue((String.class.getClassLoader())));
            instance.SolveDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastModifieddate = ((String) in.readValue((String.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.ProblemFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.OtherProblem = ((String) in.readValue((String.class.getClassLoader())));
            instance.ProductFkid = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public TicketModel[] newArray(int size) {
            return (new TicketModel[size]);
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
     *     The CustomerFkid
     */
    public String getCustomerFkid() {
        return CustomerFkid;
    }

    /**
     * 
     * @param CustomerFkid
     *     The Customer_fkid
     */
    public void setCustomerFkid(String CustomerFkid) {
        this.CustomerFkid = CustomerFkid;
    }

    /**
     * 
     * @return
     *     The InvoiceFkid
     */
    public int getInvoiceFkid() {
        return InvoiceFkid;
    }

    /**
     * 
     * @param InvoiceFkid
     *     The Invoice_fkid
     */
    public void setInvoiceFkid(int InvoiceFkid) {
        this.InvoiceFkid = InvoiceFkid;
    }

    /**
     * 
     * @return
     *     The AssignDate
     */
    public String getAssignDate() {
        return AssignDate;
    }

    /**
     * 
     * @param AssignDate
     *     The AssignDate
     */
    public void setAssignDate(String AssignDate) {
        this.AssignDate = AssignDate;
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

    /**
     * 
     * @return
     *     The Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * 
     * @param Status
     *     The Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * 
     * @return
     *     The Assignedpersonname
     */
    public String getAssignedpersonname() {
        return Assignedpersonname;
    }

    /**
     * 
     * @param Assignedpersonname
     *     The Assignedpersonname
     */
    public void setAssignedpersonname(String Assignedpersonname) {
        this.Assignedpersonname = Assignedpersonname;
    }

    /**
     * 
     * @return
     *     The AssignedContactno
     */
    public String getAssignedContactno() {
        return AssignedContactno;
    }

    /**
     * 
     * @param AssignedContactno
     *     The AssignedContactno
     */
    public void setAssignedContactno(String AssignedContactno) {
        this.AssignedContactno = AssignedContactno;
    }

    /**
     * 
     * @return
     *     The SolveDate
     */
    public String getSolveDate() {
        return SolveDate;
    }

    /**
     * 
     * @param SolveDate
     *     The SolveDate
     */
    public void setSolveDate(String SolveDate) {
        this.SolveDate = SolveDate;
    }

    /**
     * 
     * @return
     *     The lastModifieddate
     */
    public String getLastModifieddate() {
        return lastModifieddate;
    }

    /**
     * 
     * @param lastModifieddate
     *     The lastModifieddate
     */
    public void setLastModifieddate(String lastModifieddate) {
        this.lastModifieddate = lastModifieddate;
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

    /**
     * 
     * @return
     *     The ProblemFkid
     */
    public int getProblemFkid() {
        return ProblemFkid;
    }

    /**
     * 
     * @param ProblemFkid
     *     The Problem_fkid
     */
    public void setProblemFkid(int ProblemFkid) {
        this.ProblemFkid = ProblemFkid;
    }

    /**
     * 
     * @return
     *     The OtherProblem
     */
    public String getOtherProblem() {
        return OtherProblem;
    }

    /**
     * 
     * @param OtherProblem
     *     The OtherProblem
     */
    public void setOtherProblem(String OtherProblem) {
        this.OtherProblem = OtherProblem;
    }

    /**
     * 
     * @return
     *     The ProductFkid
     */
    public int getProductFkid() {
        return ProductFkid;
    }

    /**
     * 
     * @param ProductFkid
     *     The Product_fkid
     */
    public void setProductFkid(int ProductFkid) {
        this.ProductFkid = ProductFkid;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(CustomerFkid);
        dest.writeValue(InvoiceFkid);
        dest.writeValue(AssignDate);
        dest.writeValue(Description);
        dest.writeValue(Status);
        dest.writeValue(Assignedpersonname);
        dest.writeValue(AssignedContactno);
        dest.writeValue(SolveDate);
        dest.writeValue(lastModifieddate);
        dest.writeValue(mid);
        dest.writeValue(mdate);
        dest.writeValue(ProblemFkid);
        dest.writeValue(OtherProblem);
        dest.writeValue(ProductFkid);
    }

    public int describeContents() {
        return  0;
    }

}
