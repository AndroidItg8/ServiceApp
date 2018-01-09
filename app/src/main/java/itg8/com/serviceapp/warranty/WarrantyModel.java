
package itg8.com.serviceapp.warranty;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class WarrantyModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("party_fkid")
    @Expose
    private int partyFkid;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("voucherNUmber")
    @Expose
    private String voucherNUmber;
    @SerializedName("Purchase_type")
    @Expose
    private int PurchaseType;
    @SerializedName("Series")
    @Expose
    private String Series;
    @SerializedName("FInalAmtwithGST")
    @Expose
    private int FInalAmtwithGST;
    @SerializedName("FInalAmtwithoutGST")
    @Expose
    private double FInalAmtwithoutGST;
    @SerializedName("WarrantyInMonth")
    @Expose
    private int WarrantyInMonth;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private String mdate;
    @SerializedName("InvoiceType")
    @Expose
    private int InvoiceType;
    @SerializedName("InvoiceBill")
    @Expose
    private String InvoiceBill;
    @SerializedName("InvoiceNumber")
    @Expose
    private String InvoiceNumber;
     private boolean isProgress =false;

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    private  boolean isDownload=false;

    public boolean isProgress() {
        return isProgress;
    }

    public void setProgress(boolean progress) {
        isProgress = progress;
    }

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
     *     The partyFkid
     */
    public int getPartyFkid() {
        return partyFkid;
    }

    /**
     * 
     * @param partyFkid
     *     The party_fkid
     */
    public void setPartyFkid(int partyFkid) {
        this.partyFkid = partyFkid;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The voucherNUmber
     */
    public String getVoucherNUmber() {
        return voucherNUmber;
    }

    /**
     * 
     * @param voucherNUmber
     *     The voucherNUmber
     */
    public void setVoucherNUmber(String voucherNUmber) {
        this.voucherNUmber = voucherNUmber;
    }

    /**
     * 
     * @return
     *     The PurchaseType
     */
    public int getPurchaseType() {
        return PurchaseType;
    }

    /**
     * 
     * @param PurchaseType
     *     The Purchase_type
     */
    public void setPurchaseType(int PurchaseType) {
        this.PurchaseType = PurchaseType;
    }

    /**
     * 
     * @return
     *     The Series
     */
    public String getSeries() {
        return Series;
    }

    /**
     * 
     * @param Series
     *     The Series
     */
    public void setSeries(String Series) {
        this.Series = Series;
    }

    /**
     * 
     * @return
     *     The FInalAmtwithGST
     */
    public int getFInalAmtwithGST() {
        return FInalAmtwithGST;
    }

    /**
     * 
     * @param FInalAmtwithGST
     *     The FInalAmtwithGST
     */
    public void setFInalAmtwithGST(int FInalAmtwithGST) {
        this.FInalAmtwithGST = FInalAmtwithGST;
    }

    /**
     * 
     * @return
     *     The FInalAmtwithoutGST
     */
    public double getFInalAmtwithoutGST() {
        return FInalAmtwithoutGST;
    }

    /**
     * 
     * @param FInalAmtwithoutGST
     *     The FInalAmtwithoutGST
     */
    public void setFInalAmtwithoutGST(double FInalAmtwithoutGST) {
        this.FInalAmtwithoutGST = FInalAmtwithoutGST;
    }

    /**
     * 
     * @return
     *     The WarrantyInMonth
     */
    public int getWarrantyInMonth() {
        return WarrantyInMonth;
    }

    /**
     * 
     * @param WarrantyInMonth
     *     The WarrantyInMonth
     */
    public void setWarrantyInMonth(int WarrantyInMonth) {
        this.WarrantyInMonth = WarrantyInMonth;
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

    /**
     * 
     * @return
     *     The InvoiceType
     */
    public int getInvoiceType() {
        return InvoiceType;
    }

    /**
     * 
     * @param InvoiceType
     *     The InvoiceType
     */
    public void setInvoiceType(int InvoiceType) {
        this.InvoiceType = InvoiceType;
    }

    /**
     * 
     * @return
     *     The InvoiceBill
     */
    public String getInvoiceBill() {
        return InvoiceBill;
    }

    /**
     * 
     * @param InvoiceBill
     *     The InvoiceBill
     */
    public void setInvoiceBill(String InvoiceBill) {
        this.InvoiceBill = InvoiceBill;
    }

    /**
     * 
     * @return
     *     The InvoiceNumber
     */
    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    /**
     * 
     * @param InvoiceNumber
     *     The InvoiceNumber
     */
    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    public WarrantyModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pkid);
        dest.writeInt(this.partyFkid);
        dest.writeString(this.date);
        dest.writeString(this.voucherNUmber);
        dest.writeInt(this.PurchaseType);
        dest.writeString(this.Series);
        dest.writeInt(this.FInalAmtwithGST);
        dest.writeDouble(this.FInalAmtwithoutGST);
        dest.writeInt(this.WarrantyInMonth);
        dest.writeParcelable((Parcelable) this.mid, flags);
        dest.writeString(this.mdate);
        dest.writeInt(this.InvoiceType);
        dest.writeString(this.InvoiceBill);
        dest.writeString(this.InvoiceNumber);
        dest.writeByte(this.isProgress ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDownload ? (byte) 1 : (byte) 0);
    }

    protected WarrantyModel(Parcel in) {
        this.pkid = in.readInt();
        this.partyFkid = in.readInt();
        this.date = in.readString();
        this.voucherNUmber = in.readString();
        this.PurchaseType = in.readInt();
        this.Series = in.readString();
        this.FInalAmtwithGST = in.readInt();
        this.FInalAmtwithoutGST = in.readDouble();
        this.WarrantyInMonth = in.readInt();
        this.mid = in.readParcelable(Object.class.getClassLoader());
        this.mdate = in.readString();
        this.InvoiceType = in.readInt();
        this.InvoiceBill = in.readString();
        this.InvoiceNumber = in.readString();
        this.isProgress = in.readByte() != 0;
        this.isDownload = in.readByte() != 0;
    }

    public static final Creator<WarrantyModel> CREATOR = new Creator<WarrantyModel>() {
        @Override
        public WarrantyModel createFromParcel(Parcel source) {
            return new WarrantyModel(source);
        }

        @Override
        public WarrantyModel[] newArray(int size) {
            return new WarrantyModel[size];
        }
    };
}
