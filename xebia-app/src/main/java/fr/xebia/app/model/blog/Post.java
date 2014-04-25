package fr.xebia.app.model.blog;


import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Parcelable {
    public static final Parcelable.Creator<Post> CREATOR
            = new Parcelable.Creator<Post>() {

        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
    private long id;
    private int commentCount;
    private ArrayList<Comment> comments;
    private ArrayList<Author> authors;
    private ArrayList<Tag> tags;
    private ArrayList<Category> categories;
    private String commentStatus;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date modified;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;
    private String excerpt;
    private ArrayList<ContentItem> structuredContent;
    private String content;
    private String titlePlain;
    private String title;
    private String status;
    private String url;


    public Post() {
    }

    Post(long id, int commentCount, ArrayList<Comment> comments, ArrayList<Author> authors, ArrayList<Tag> tags, ArrayList<Category> categories, String commentStatus, Date modified, Date date, String excerpt, ArrayList<ContentItem> structuredContent, String content, String titlePlain, String title, String status, String url) {
        this.id = id;
        this.commentCount = commentCount;
        this.comments = comments;
        this.authors = authors;
        this.tags = tags;
        this.categories = categories;
        this.commentStatus = commentStatus;
        this.modified = modified;
        this.date = date;
        this.excerpt = excerpt;
        this.structuredContent = structuredContent;
        this.content = content;
        this.titlePlain = titlePlain;
        this.title = title;
        this.status = status;
        this.url = url;
    }

    private Post(Parcel in) {
        id = in.readLong();
        commentCount = in.readInt();
        comments = new ArrayList<>();
        in.readTypedList(comments, Comment.CREATOR);

        authors = new ArrayList<>();
        in.readTypedList(authors, Author.CREATOR);

        tags = new ArrayList<>();
        in.readTypedList(tags, Tag.CREATOR);

        categories = new ArrayList<>();
        in.readTypedList(categories, Category.CREATOR);

        commentStatus = in.readString();
        modified = (Date) in.readSerializable();
        date = (Date) in.readSerializable();

        excerpt = in.readString();

        structuredContent = new ArrayList<>();
        in.readTypedList(structuredContent, ContentItem.CREATOR);

        content = in.readString();
        titlePlain = in.readString();
        title = in.readString();
        status = in.readString();
        url = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public ArrayList<ContentItem> getStructuredContent() {
        return structuredContent;
    }

    public void setStructuredContent(ArrayList<ContentItem> structuredContent) {
        this.structuredContent = structuredContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitlePlain() {
        return titlePlain;
    }

    public void setTitlePlain(String titlePlain) {
        this.titlePlain = titlePlain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(commentCount);
        dest.writeTypedList(comments);
        dest.writeTypedList(authors);
        dest.writeTypedList(tags);
        dest.writeTypedList(categories);
        dest.writeString(commentStatus);
        dest.writeSerializable(modified);
        dest.writeSerializable(date);
        dest.writeString(excerpt);
        dest.writeTypedList(structuredContent);
        dest.writeString(content);
        dest.writeString(titlePlain);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeString(url);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }

        Post post = (Post) o;

        if (commentCount != post.commentCount) {
            return false;
        }
        if (id != post.id) {
            return false;
        }
        if (authors != null ? !authors.equals(post.authors) : post.authors != null) {
            return false;
        }
        if (categories != null ? !categories.equals(post.categories) : post.categories != null) {
            return false;
        }
        if (commentStatus != null ? !commentStatus.equals(post.commentStatus) : post.commentStatus != null) {
            return false;
        }
        if (comments != null ? !comments.equals(post.comments) : post.comments != null) {
            return false;
        }
        if (content != null ? !content.equals(post.content) : post.content != null) {
            return false;
        }
        if (date != null ? !date.equals(post.date) : post.date != null) {
            return false;
        }
        if (excerpt != null ? !excerpt.equals(post.excerpt) : post.excerpt != null) {
            return false;
        }
        if (modified != null ? !modified.equals(post.modified) : post.modified != null) {
            return false;
        }
        if (status != null ? !status.equals(post.status) : post.status != null) {
            return false;
        }
        if (structuredContent != null ? !structuredContent.equals(
                post.structuredContent) : post.structuredContent != null) {
            return false;
        }
        if (tags != null ? !tags.equals(post.tags) : post.tags != null) {
            return false;
        }
        if (title != null ? !title.equals(post.title) : post.title != null) {
            return false;
        }
        if (titlePlain != null ? !titlePlain.equals(post.titlePlain) : post.titlePlain != null) {
            return false;
        }
        if (url != null ? !url.equals(post.url) : post.url != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + commentCount;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (commentStatus != null ? commentStatus.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (excerpt != null ? excerpt.hashCode() : 0);
        result = 31 * result + (structuredContent != null ? structuredContent.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (titlePlain != null ? titlePlain.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
