package fr.xebia.app.event;

import fr.xebia.app.model.blog.Post;

public class PostRetrievedEvent {

    public final Post post;

    public PostRetrievedEvent(Post post) {
        this.post = post;
    }
}
