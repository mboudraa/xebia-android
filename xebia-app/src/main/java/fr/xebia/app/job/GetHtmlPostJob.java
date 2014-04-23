package fr.xebia.app.job;

import android.content.Context;
import de.greenrobot.event.EventBus;
import fr.xebia.app.event.PostHtmlRetrievedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetHtmlPostJob extends AbstractPostJob {

    private long mPostId;
    private String mPostUrl;

    public GetHtmlPostJob(Context context, long id, String url) {
        super(context);
        mPostId = id;
        mPostUrl = url;
    }


    @Override
    public void onRun() throws Throwable {
        Document doc = Jsoup.connect(mPostUrl).get();
        String selector = new StringBuilder()
                .append("#post-")
                .append(mPostId)
                .append(" .post-body .entry")
                .toString();
        String html = doc.select(selector).get(0).html();
        EventBus.getDefault().post(new PostHtmlRetrievedEvent(html));
    }

}
