package fr.xebia.app;

import android.app.ActionBar;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import fr.xebia.app.event.NetworkErrorEvent;
import fr.xebia.app.event.PostListRetrievedEvent;
import fr.xebia.app.job.GetPostListJob;
import fr.xebia.app.model.Post;
import fr.xebia.app.view.KenBurnsView;
import org.androidannotations.annotations.*;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
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

    @ViewById(R.id.header_logo)
    ImageView mHeaderLogo;

    int mMinHeaderTranslation;
    AccelerateDecelerateInterpolator mSmoothInterpolator;

    RectF mRect1 = new RectF();
    RectF mRect2 = new RectF();

    @AfterViews
    void onPostCreate() {


        mSmoothInterpolator = new AccelerateDecelerateInterpolator();

        int headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = getActionbarHeight() - headerHeight;

        mHeaderPicture.setResourceIds(R.drawable.blog_background_xebia);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mAdapter);
        swingBottomInAnimationAdapter.setInitialDelayMillis(300);
        swingBottomInAnimationAdapter.setAbsListView(mListView);

        mFakeHeader = getLayoutInflater().inflate(R.layout.fake_header, mListView, false);
        mListView.addHeaderView(mFakeHeader);


        ActionBarPullToRefresh.from(this)
                              .options(new Options.Builder()
                                               .minimize(0)
                                               .scrollDistance(0.6f)
                                               .build())
                              .allChildrenArePullable()
                              .listener(this)
                              .setup(mPullToRefreshLayout);


        mListView.setOnScrollListener(this);
        mListView.setAdapter(swingBottomInAnimationAdapter);


//        setTitleAlpha(0f);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setIcon(R.drawable.ic_transparent);

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


    private RectF getOnScreenRect(RectF paramRectF, View paramView) {
        paramRectF.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
        return paramRectF;
    }

    private void interpolate(View view1, View view2, float interpolation) {
        getOnScreenRect(mRect1, view1);
        getOnScreenRect(mRect2, view2);
        float f1 = 1.5F + interpolation * (mRect2.width() / mRect1.width() - 0.5F);
        float f2 = 1.5F + interpolation * (mRect2.height() / mRect1.height() - 0.5F);
        float f3 = 0.5F * (interpolation * (mRect2.left + mRect2.right - mRect1.left - mRect1.right));
        float f4 = 0.5F * (interpolation * (mRect2.top + mRect2.bottom - mRect1.top - mRect1.bottom));
        view1.setTranslationX(f3);
        view1.setTranslationY(f4 - mHeader.getTranslationY());
        view1.setScaleX(f1);
        view1.setScaleY(f2);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        int scrollY = getScrollY();
        //sticky actionbar
        mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));

        float ratio = Clamp.clamp(mHeader.getTranslationY() / mMinHeaderTranslation, 0.0f, 1.5f);
        interpolate(mHeaderLogo, getActionBarIconView(), mSmoothInterpolator.getInterpolation(ratio));
    }


    public int getScrollY() {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mPlaceHolderView.getHeight();
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }


}
