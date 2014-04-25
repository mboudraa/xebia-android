package fr.xebia.app.model.blog;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author implements Parcelable {

    public static final Parcelable.Creator<Author> CREATOR
            = new Parcelable.Creator<Author>() {

        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
    private long id;
    private String description;
    private String nickname;
    private String slug;
    private String name;

    public Author() {
    }

    Author(long id, String description, String nickname, String slug, String name) {
        this.id = id;
        this.description = description;
        this.nickname = nickname;
        this.slug = slug;
        this.name = name;
    }

    private Author(Parcel in) {
        id = in.readLong();
        description = in.readString();
        nickname = in.readString();
        slug = in.readString();
        name = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(description);
        dest.writeString(nickname);
        dest.writeString(slug);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Author)) {
            return false;
        }

        Author author = (Author) o;

        if (id != author.id) {
            return false;
        }
        if (description != null ? !description.equals(author.description) : author.description != null) {
            return false;
        }
        if (name != null ? !name.equals(author.name) : author.name != null) {
            return false;
        }
        if (nickname != null ? !nickname.equals(author.nickname) : author.nickname != null) {
            return false;
        }
        if (slug != null ? !slug.equals(author.slug) : author.slug != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

