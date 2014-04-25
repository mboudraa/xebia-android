package fr.xebia.app.model.blog;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag implements Parcelable {

    public static final Parcelable.Creator<Tag> CREATOR
            = new Parcelable.Creator<Tag>() {

        public Tag createFromParcel(Parcel in) {
            return new Tag(in);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
    private long id;
    private int postCount;
    private String description;
    private String title;

    public Tag() {
    }

    Tag(long id, int postCount, String description, String title) {
        this.id = id;
        this.postCount = postCount;
        this.description = description;
        this.title = title;
    }

    private Tag(android.os.Parcel in) {
        id = in.readLong();
        postCount = in.readInt();
        description = in.readString();
        title = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(postCount);
        dest.writeString(description);
        dest.writeString(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }

        Tag tag = (Tag) o;

        if (id != tag.id) {
            return false;
        }
        if (postCount != tag.postCount) {
            return false;
        }
        if (description != null ? !description.equals(tag.description) : tag.description != null) {
            return false;
        }
        if (title != null ? !title.equals(tag.title) : tag.title != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + postCount;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
