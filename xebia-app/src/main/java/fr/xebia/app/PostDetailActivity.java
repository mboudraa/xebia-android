package fr.xebia.app;

import android.app.ActionBar;
import fr.xebia.app.event.PostRetrievedEvent;
import fr.xebia.app.job.GetPostJob;
import fr.xebia.app.model.Post;
import fr.xebia.app.view.PostView;
import org.androidannotations.annotations.*;

@EActivity(R.layout.activity_post_detail)
public class PostDetailActivity extends BaseActivity {

    @ViewById(R.id.post_detail_container)
    PostView mPostDetailContainer;

    @Bean
    JobManagerProvider mJobManagerProvider;

    @Extra
    long mPostId;

    GetPostJob mJob;

    @InstanceState
    Post mPost;

    @AfterViews
    void onPostCreate() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logo_blog_xebia);

        mJob = new GetPostJob(this, mPostId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPost == null) {
            mJobManagerProvider.jobManager.addJobInBackground(mJob);
        } else {
            updateView(mPost);
        }
    }

    public void updateView(Post post) {
        mPostDetailContainer.setPost(post);
    }

    public void onEventMainThread(PostRetrievedEvent event) {
        mPost = event.post;
        updateView(mPost);
    }

}
