package fr.xebia.app.job;

import android.content.Context;
import fr.xebia.app.model.blog.Post;

import java.util.ArrayList;

public class GetPostListByCategoryJob extends GetPostListJob {

    private int mCategoryId;

    public GetPostListByCategoryJob(Context context, int categoryId) {
        super(context);
        mCategoryId = categoryId;
    }


    @Override
    public void onRun() throws Throwable {
        ArrayList<Post> posts = mBlogApi.getPostListByCategories(mCategoryId).getPosts();
        onSuccess(posts);
    }


}
