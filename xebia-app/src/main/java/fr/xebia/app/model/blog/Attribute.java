package fr.xebia.app.model.blog;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute implements Parcelable {

    public static final Parcelable.Creator<Attribute> CREATOR
            = new Parcelable.Creator<Attribute>() {

        public Attribute createFromParcel(Parcel in) {
            return new Attribute(in);
        }

        public Attribute[] newArray(int size) {
            return new Attribute[size];
        }
    };
    private String key;
    private String value;

    public Attribute() {
    }

    Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private Attribute(Parcel in) {
        key = in.readString();
        value = in.readString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attribute)) {
            return false;
        }

        Attribute attribute = (Attribute) o;

        if (key != null ? !key.equals(attribute.key) : attribute.key != null) {
            return false;
        }
        if (value != null ? !value.equals(attribute.value) : attribute.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
