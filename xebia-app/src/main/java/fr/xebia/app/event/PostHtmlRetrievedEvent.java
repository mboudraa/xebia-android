package fr.xebia.app.event;

public class PostHtmlRetrievedEvent {

    public final String htmlPost;

    public PostHtmlRetrievedEvent(String html) {
        htmlPost = html;
    }
}
