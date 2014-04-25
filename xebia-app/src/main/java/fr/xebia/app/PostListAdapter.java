package fr.xebia.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.xebia.app.model.blog.Post;
import fr.xebia.app.view.PostItemView;
import fr.xebia.app.view.PostItemView_;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

@EBean
public class PostListAdapter extends BaseAdapter {

    private ArrayList<Post> mPosts = new ArrayList<>();


    @RootContext
    Context mContext;

    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Post getItem(int position) {
        return mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PostItemView view;

        if(convertView == null){
            view = PostItemView_.build(mContext);
        }else{
            view = (PostItemView) convertView;
        }

        view.updatePost(getItem(position));

        return view;
    }


    public void setData(ArrayList<Post> posts) {
        mPosts.clear();
        if (posts != null && !posts.isEmpty()) {
            mPosts.addAll(posts);
        }
        notifyDataSetChanged();
    }

}
