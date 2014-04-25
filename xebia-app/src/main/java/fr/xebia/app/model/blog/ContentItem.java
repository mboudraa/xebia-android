package fr.xebia.app.model.blog;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentItem implements Parcelable {

    public static final Parcelable.Creator<ContentItem> CREATOR
            = new Parcelable.Creator<ContentItem>() {

        public ContentItem createFromParcel(Parcel in) {
            return new ContentItem(in);
        }

        public ContentItem[] newArray(int size) {
            return new ContentItem[size];
        }
    };
    private String type;
    private ArrayList<Attribute> attributes;
    private String text;


    public ContentItem() {
    }

    ContentItem(String type, ArrayList<Attribute> attributes, String text) {
        this.type = type;
        this.attributes = attributes;
        this.text = text;
    }

    private ContentItem(Parcel in) {
        type = in.readString();

        attributes = new ArrayList<>();
        in.readTypedList(attributes, Attribute.CREATOR);

        text = in.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeTypedList(attributes);
        dest.writeString(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentItem)) {
            return false;
        }

        ContentItem that = (ContentItem) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) {
            return false;
        }
        if (text != null ? !text.equals(that.text) : that.text != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
