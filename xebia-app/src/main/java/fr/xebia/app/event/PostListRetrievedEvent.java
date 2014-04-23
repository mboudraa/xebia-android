package fr.xebia.app.event;

import fr.xebia.app.model.Post;

import java.util.ArrayList;

public class PostListRetrievedEvent {

    public final ArrayList<Post> posts;

    public PostListRetrievedEvent(ArrayList<Post> posts){
        this.posts = new ArrayList<>();
        if(posts != null){
            this.posts.addAll(posts);
        }
    }
}
