package fr.xebia.app.api;

import fr.xebia.app.model.blog.Category;
import fr.xebia.app.model.blog.PostList;
import fr.xebia.app.model.blog.PostResponse;
import fr.xebia.app.model.blog.Tag;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.ArrayList;

public interface BlogApi {

    @GET("/posts/recent")
    PostList getPostList();

    @GET("/posts/{id}")
    PostResponse getPost(@Path("id") long id);

    @GET("/tags")
    ArrayList<Tag> getTags();

    @GET("/tags/{id}/posts")
    PostList getPostListByTag(@Path("id") long tagId);

    @GET("/categories")
    ArrayList<Category> getCategories();

    @GET("/categories/{id}/posts")
    PostList getPostListByCategories(@Path("id") long categoryId);
}
