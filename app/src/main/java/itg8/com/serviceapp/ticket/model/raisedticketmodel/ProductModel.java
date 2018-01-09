package itg8.com.serviceapp.ticket.model.raisedticketmodel;

/**
 * Created by USER-pc on 10/15/2017.
 */
 import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @SerializedName("brandid")
    @Expose
    private Integer brandid;
    @SerializedName("categoryid")
    @Expose
    private Integer categoryid;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("group_fkid")
    @Expose
    private Integer groupFkid;
    @SerializedName("Unit")
    @Expose
    private Integer unit;
    @SerializedName("openStock")
    @Expose
    private String openStock;
    @SerializedName("Salesprice")
    @Expose
    private Double salesprice;
    @SerializedName("purchaseprice")
    @Expose
    private Double purchaseprice;
    @SerializedName("MRP")
    @Expose
    private Double mRP;
    @SerializedName("min_salesprice")
    @Expose
    private Double minSalesprice;
    @SerializedName("self_price")
    @Expose
    private Object selfPrice;
    @SerializedName("HSNCode")
    @Expose
    private String hSNCode;
    @SerializedName("lastModifieddate")
    @Expose
    private Object lastModifieddate;
    @SerializedName("prodctImage")
    @Expose
    private Object prodctImage;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<ProductModel> CREATOR = new Creator<ProductModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        public ProductModel[] newArray(int size) {
            return (new ProductModel[size]);
        }

    }
            ;

    protected ProductModel(Parcel in) {
        this.pkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.brandid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.categoryid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        this.groupFkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.unit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.openStock = ((String) in.readValue((String.class.getClassLoader())));
        this.salesprice = ((Double) in.readValue((Double.class.getClassLoader())));
        this.purchaseprice = ((Double) in.readValue((Double.class.getClassLoader())));
        this.mRP = ((Double) in.readValue((Double.class.getClassLoader())));
        this.minSalesprice = ((Double) in.readValue((Double.class.getClassLoader())));
        this.selfPrice = ((Object) in.readValue((Object.class.getClassLoader())));
        this.hSNCode = ((String) in.readValue((String.class.getClassLoader())));
        this.lastModifieddate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.prodctImage = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mid = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public ProductModel() {
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getGroupFkid() {
        return groupFkid;
    }

    public void setGroupFkid(Integer groupFkid) {
        this.groupFkid = groupFkid;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getOpenStock() {
        return openStock;
    }

    public void setOpenStock(String openStock) {
        this.openStock = openStock;
    }

    public Double getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(Double salesprice) {
        this.salesprice = salesprice;
    }

    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Double getMRP() {
        return mRP;
    }

    public void setMRP(Double mRP) {
        this.mRP = mRP;
    }

    public Double getMinSalesprice() {
        return minSalesprice;
    }

    public void setMinSalesprice(Double minSalesprice) {
        this.minSalesprice = minSalesprice;
    }

    public Object getSelfPrice() {
        return selfPrice;
    }

    public void setSelfPrice(Object selfPrice) {
        this.selfPrice = selfPrice;
    }

    public String getHSNCode() {
        return hSNCode;
    }

    public void setHSNCode(String hSNCode) {
        this.hSNCode = hSNCode;
    }

    public Object getLastModifieddate() {
        return lastModifieddate;
    }

    public void setLastModifieddate(Object lastModifieddate) {
        this.lastModifieddate = lastModifieddate;
    }

    public Object getProdctImage() {
        return prodctImage;
    }

    public void setProdctImage(Object prodctImage) {
        this.prodctImage = prodctImage;
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
        dest.writeValue(brandid);
        dest.writeValue(categoryid);
        dest.writeValue(itemName);
        dest.writeValue(groupFkid);
        dest.writeValue(unit);
        dest.writeValue(openStock);
        dest.writeValue(salesprice);
        dest.writeValue(purchaseprice);
        dest.writeValue(mRP);
        dest.writeValue(minSalesprice);
        dest.writeValue(selfPrice);
        dest.writeValue(hSNCode);
        dest.writeValue(lastModifieddate);
        dest.writeValue(prodctImage);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return 0;
    }

}