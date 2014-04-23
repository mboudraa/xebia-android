package fr.xebia.app.job;

import android.content.Context;
import de.greenrobot.event.EventBus;
import fr.xebia.app.event.PostRetrievedEvent;
import fr.xebia.app.model.Post;

public class GetPostJob extends AbstractPostJob {

    private long mPostId;

    public GetPostJob(Context context, long id) {
        super(context);
        mPostId = id;
    }


    @Override
    public void onRun() throws Throwable {
        Post post = mBlogApi.getPost(mPostId).getPost();
        EventBus.getDefault().post(new PostRetrievedEvent(post));
    }

}
