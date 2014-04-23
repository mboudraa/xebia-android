package fr.xebia.app.job;

import android.content.Context;
import de.greenrobot.event.EventBus;
import fr.xebia.app.event.PostListRetrievedEvent;
import fr.xebia.app.model.Post;

import java.util.ArrayList;

public class GetPostListJob extends AbstractPostJob {


    public GetPostListJob(Context context) {
        super(context);
    }

    @Override
    public void onRun() throws Throwable {
        ArrayList<Post> posts = mBlogApi.getPostList().getPosts();
        onSuccess(posts);
    }

    protected void onSuccess(ArrayList<Post> posts) {
        EventBus.getDefault().post(new PostListRetrievedEvent(posts));
    }

}
