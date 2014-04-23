package fr.xebia.app.job;

import android.content.Context;
import com.path.android.jobqueue.Job;
import fr.xebia.app.api.ApiProvider_;
import fr.xebia.app.api.BlogApi;
import fr.xebia.app.model.Post;

import java.util.ArrayList;

public class GetPostListByTagJob extends GetPostListJob {

    private int mTagId;

    public GetPostListByTagJob(Context context, int tagId) {
        super(context);
        mTagId = tagId;
    }


    @Override
    public void onRun() throws Throwable {
        ArrayList<Post> posts = mBlogApi.getPostListByTag(mTagId).getPosts();
        onSuccess(posts);
    }
}
