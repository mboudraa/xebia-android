package fr.xebia.app.job;

import android.content.Context;
import com.path.android.jobqueue.Job;
import fr.xebia.app.api.ApiProvider_;
import fr.xebia.app.api.BlogApi;
import fr.xebia.app.model.Post;

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
