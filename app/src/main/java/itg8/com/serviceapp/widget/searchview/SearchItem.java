package itg8.com.serviceapp.widget.searchview;



import itg8.com.serviceapp.R;


@SuppressWarnings("WeakerAccess")
public class SearchItem  {
    //implements Parcelable

    private int icon;
    private Object list;
    private int type;
    private CharSequence text;
    private CharSequence textSecond;
    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public CharSequence getTextSecond() {
        return textSecond;
    }

    public void setTextSecond(CharSequence textSecond) {
        this.textSecond = textSecond;
    }


    public SearchItem() {
    }

    public SearchItem(CharSequence text) {
        this(R.drawable.ic_search_black_24dp, text);
    }

    public SearchItem(int icon, CharSequence text) {
        this.icon = icon;
        this.text = text;
    }

    public SearchItem(int icon, CharSequence text, CharSequence textSecond) {
        this.icon = icon;
        this.text = text;
        this.textSecond = textSecond;
    }

    public int get_icon() {
        return this.icon;
    }

    public void set_icon(int icon) {
        this.icon = icon;
    }

    public CharSequence get_text() {
        return this.text;
    }

    public void set_text(CharSequence text) {
        this.text = text;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }



    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.icon);
//        dest.writeInt(this.type);
//        dest.write(this.text, flags);
//        dest.writeParcelable(this.textSecond, flags);
//    }
//
//    protected SearchItem(Parcel in) {
//        this.icon = in.readInt();
//        this.list = in.readParcelable(Object.class.getClassLoader());
//        this.type = in.readInt();
//        this.text = in.readParcelable(CharSequence.class.getClassLoader());
//        this.textSecond = in.readParcelable(CharSequence.class.getClassLoader());
//    }
//
//    public static final Creator<SearchItem> CREATOR = new Creator<SearchItem>() {
//        @Override
//        public SearchItem createFromParcel(Parcel source) {
//            return new SearchItem(source);
//        }
//
//        @Override
//        public SearchItem[] newArray(int size) {
//            return new SearchItem[size];
//        }
//    };
}