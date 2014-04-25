package fr.xebia.app.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import fr.tkeunebr.gravatar.Gravatar;
import fr.xebia.app.R;
import fr.xebia.app.model.blog.Author;
import fr.xebia.app.model.blog.Category;
import fr.xebia.app.model.blog.Post;
import fr.xebia.app.widget.JustifiedTextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@EViewGroup(R.layout.post_list_item)
public class PostItemView extends LinearLayout {

    @ViewById(R.id.post_category_container)
    LinearLayout mPostCategoriesContainer;

    @ViewById(R.id.post_date)
    TextView mPostDateTextView;

//    @ViewById(R.id.post_comment_count)
//    TextView mPostCommentCountTextView;

    @ViewById(R.id.post_title)
    TextView mPostTitleTextView;

    @ViewById(R.id.post_content)
    JustifiedTextView mPostContentTextView;

    @ViewById(R.id.post_authors_image_container)
    LinearLayout mPostAuthorsImageContainer;

    @ViewById(R.id.post_authors_names)
    TextView mPostAuthorsNamesTextView;


    Post mPost;


    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public PostItemView(Context context) {
        super(context);
    }

    public PostItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PostItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void updatePost(Post post) {
        mPostDateTextView.setText(mSimpleDateFormat.format(post.getDate()));
//        mPostCommentCountTextView.setText(String.valueOf(post.getCommentCount()));
        mPostTitleTextView.setText(post.getTitle());
        mPostContentTextView.setText(Html.fromHtml(post.getExcerpt()));

        mPostAuthorsImageContainer.removeAllViews();
        mPostCategoriesContainer.removeAllViews();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        for (Category category : post.getCategories()) {
            TextView textView = (TextView) layoutInflater.inflate(R.layout.post_category_textview, null);
            textView.setText(category.getTitle());
            mPostCategoriesContainer.addView(textView);
        }

        StringBuilder authorStringBuilder = new StringBuilder();
        ArrayList<Author> authors = post.getAuthors();
        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.simple_imageview, null);
            mPostAuthorsImageContainer.addView(imageView);

            if ("xebiafrance".equals(author.getSlug())) {
                Picasso.with(getContext())
                       .load(R.drawable.xebia)
                       .resizeDimen(R.dimen.avatar_size, R.dimen.avatar_size)
                       .into(imageView);
            } else {
                String gravatarUrl = Gravatar.init()
                                             .with(author.getSlug() + "@xebia.fr")
                                             .size(getResources().getDimensionPixelSize(R.dimen.avatar_size))
                                             .build();
                Picasso.with(getContext())
                       .load(gravatarUrl)
                       .into(imageView);

                authorStringBuilder.append(author.getName());

                if (i < authors.size() - 1) {
                    authorStringBuilder.append(", ");
                }
            }

        }

        mPostAuthorsNamesTextView.setText(authorStringBuilder.toString());

    }

}
