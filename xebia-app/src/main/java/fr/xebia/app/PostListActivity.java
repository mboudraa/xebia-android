package fr.xebia.app;

import android.app.ActionBar;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import fr.xebia.app.event.NetworkErrorEvent;
import fr.xebia.app.event.PostListRetrievedEvent;
import fr.xebia.app.job.GetPostListJob;
import fr.xebia.app.model.blog.Post;
import fr.xebia.app.view.KenBurnsView;
import org.androidannotations.annotations.*;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

import java.util.ArrayList;

@EActivity(R.layout.activity_post_list)
public class PostListActivity extends BaseActivity implements OnRefreshListener, AbsListView.OnScrollListener {

    @ViewById(R.id.blog_listview)
    ListView mListView;

    @ViewById(R.id.blog_ptr_layout)
    PullToRefreshLayout mPullToRefreshLayout;

    @Bean
    PostListAdapter mAdapter;

    @Bean
    JobManagerProvider mJobManagerProvider;

    GetPostListJob mJob;

    @InstanceState
    ArrayList<Post> mPosts;


    View mFakeHeader;

    @ViewById(R.id.header)
    FrameLayout mHeader;

    @ViewById(R.id.header)
    View mPlaceHolderView;


    @ViewById(R.id.header_picture)
    KenBurnsView mHeaderPicture;

    int mMinHeaderTranslation;

    Drawable mActionBarBackgroundDrawable;
    AccelerateDecelerateInterpolator mSmoothInterpolator;

    RectF mRect1 = new RectF();
    RectF mRect2 = new RectF();


    private void initActionBar() {
        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.action_bar_background);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
        }
        mActionBarBackgroundDrawable.setAlpha(0);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(mActionBarBackgroundDrawable);
        actionBar.setIcon(R.drawable.logo_blog_xebia);

    }

    @AfterViews
    protected void onPostCreate() {

        initActionBar();

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();

        int headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = getActionbarHeight() - headerHeight;


        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
                mAdapter);
        swingBottomInAnimationAdapter.setInitialDelayMillis(300);
        swingBottomInAnimationAdapter.setAbsListView(mListView);


        ActionBarPullToRefresh.from(this)
                              .options(new Options.Builder()
                                               .minimize(0)
                                               .headerTransformer(new PtrHeaderTransformer())
                                               .scrollDistance(0.3f)
                                               .build())
                              .allChildrenArePullable()
                              .listener(this)
                              .setup(mPullToRefreshLayout);


        mFakeHeader = getLayoutInflater().inflate(R.layout.fake_header, mListView, false);
        mListView.addHeaderView(mFakeHeader);

        mListView.setOnScrollListener(this);
        mListView.setAdapter(swingBottomInAnimationAdapter);

        mJob = new GetPostListJob(this);
    }

    @ItemClick(R.id.blog_listview)
    public void onPostItemClicked(Post post) {
        if (post != null) {
            PostDetailActivity_.intent(this)
                               .mPostId(post.getId())
                               .start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPosts == null) {
            mJobManagerProvider.jobManager.addJobInBackground(mJob);
        } else {
            mAdapter.setData(mPosts);
        }
    }

    @Override
    public void onRefreshStarted(View view) {
        mJobManagerProvider.jobManager.addJobInBackground(mJob);
    }

    public void onEventMainThread(PostListRetrievedEvent event) {
        mPosts = event.posts;
        mAdapter.setData(mPosts);
        setPullToRefreshComplete();

    }

    public void onEventMainThread(NetworkErrorEvent event) {
        super.onEventMainThread(event);
        setPullToRefreshComplete();
    }


    private void setPullToRefreshComplete() {
        if (mPullToRefreshLayout.isRefreshing()) {
            mPullToRefreshLayout.setRefreshComplete();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        int scrollPosition = getScrollPosition(firstVisibleItem);
        //sticky actionbar
        float translation = Math.max(-scrollPosition, mMinHeaderTranslation);
        mHeader.setTranslationY(translation);

        float ratio = clamp(translation / mMinHeaderTranslation, 0.0f, 1.0f);
        mActionBarBackgroundDrawable.setAlpha((int) (ratio * 255));

    }


    void setActionBarBackgroundDrawable(Drawable drawable) {
        getActionBar().setBackgroundDrawable(drawable);
    }

    public int getScrollPosition(int firstVisibleItem) {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisibleItem >= 1) {
            headerHeight = mPlaceHolderView.getHeight();
        }

        return -top + firstVisibleItem * c.getHeight() + headerHeight;
    }


    private float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }

    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            setActionBarBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
        }
    };


}


