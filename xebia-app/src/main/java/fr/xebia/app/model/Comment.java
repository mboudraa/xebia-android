package fr.xebia.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment implements Parcelable {

    public static final Parcelable.Creator<Comment> CREATOR
            = new Parcelable.Creator<Comment>() {

        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
    private long id;
    private String content;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;
    private String url;
    private String name;

    public Comment() {
    }

    Comment(long id, String content, Date date, String url, String name) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.url = url;
        this.name = name;
    }

    private Comment(Parcel in) {
        id = in.readLong();
        content = in.readString();
        date = (Date) in.readSerializable();
        url = in.readString();
        name = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(content);
        dest.writeSerializable(date);
        dest.writeString(url);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }

        Comment comment = (Comment) o;

        if (id != comment.id) {
            return false;
        }
        if (content != null ? !content.equals(comment.content) : comment.content != null) {
            return false;
        }
        if (date != null ? !date.equals(comment.date) : comment.date != null) {
            return false;
        }
        if (name != null ? !name.equals(comment.name) : comment.name != null) {
            return false;
        }
        if (url != null ? !url.equals(comment.url) : comment.url != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
