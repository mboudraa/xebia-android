package fr.xebia.app;


import android.app.ActionBar;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import fr.xebia.app.event.PostHtmlRetrievedEvent;
import fr.xebia.app.job.GetHtmlPostJob;
import org.androidannotations.annotations.*;

@EActivity(R.layout.activity_post_detail_web)
public class PostDetailWebActivity extends BaseActivity {


    @ViewById(R.id.post_webview)
    WebView mWebView;

    @Extra
    long mPostId;

    @Extra
    String mPostUrl;

    boolean mShouldLoadPage = true;

    GetHtmlPostJob mJob;

    @Bean
    JobManagerProvider mJobManagerProvider;

    @AfterViews
    void onPostCreate() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.logo_blog_xebia);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setJavaScriptEnabled(true);

        mJob = new GetHtmlPostJob(this, mPostId, mPostUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mShouldLoadPage) {
            mJobManagerProvider.jobManager.addJobInBackground(mJob);
        }

    }

    public void onEventMainThread(PostHtmlRetrievedEvent event) {
        mShouldLoadPage = false;
        mWebView.loadData(event.htmlPost, "text/html", "UTF-8");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mWebView.restoreState(savedInstanceState);
        mShouldLoadPage = false;
        super.onRestoreInstanceState(savedInstanceState);
    }
}


