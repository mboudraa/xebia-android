package fr.xebia.app.job;

import android.content.Context;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import de.greenrobot.event.EventBus;
import fr.xebia.app.api.ApiProvider_;
import fr.xebia.app.api.BlogApi;
import fr.xebia.app.event.NetworkErrorEvent;

public abstract class AbstractPostJob extends Job {

    protected BlogApi mBlogApi;

    public AbstractPostJob(Context context) {
        super(new Params(Priority.MIDDLE).requireNetwork());
        mBlogApi = ApiProvider_.getInstance_(context).getService(BlogApi.class);
    }

    @Override
    public void onAdded() {

    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        EventBus.getDefault().post(new NetworkErrorEvent(throwable));
        return false;
    }
}
