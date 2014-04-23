package fr.xebia.app.event;

import fr.xebia.app.model.Post;

public class PostHtmlRetrievedEvent {

    public final String htmlPost;

    public PostHtmlRetrievedEvent(String html) {
        htmlPost = html;
    }
}
