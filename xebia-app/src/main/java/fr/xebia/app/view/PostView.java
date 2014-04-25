package fr.xebia.app.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.koushikdutta.ion.Ion;
import fr.xebia.app.R;
import fr.xebia.app.model.blog.Attribute;
import fr.xebia.app.model.blog.ContentItem;
import fr.xebia.app.model.blog.Post;
import fr.xebia.app.widget.HtmlTagHandler;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.SystemService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@EViewGroup
public class PostView extends LinearLayout {

    @SystemService
    LayoutInflater mLayoutInflater;

    Post mPost;

    public PostView(Context context) {
        super(context);
    }

    public PostView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PostView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @AfterInject
    void init() {
        setOrientation(VERTICAL);
    }

    public void setPost(Post post) {

        if (post == null) {
            removeAllViews();

        } else if (!post.equals(mPost)) {
            ArrayList<ContentItem> structuredContent = post.getStructuredContent();
            if (getChildCount() != structuredContent.size()) {
                removeAllViews();
                for (ContentItem item : structuredContent) {
                    addContentItem(item);
                }
            }
        }

        mPost = post;
    }

    public void addContentItem(ContentItem item) {
        final String type = item.getType();
        final String text = item.getText();

        TextView textView = inflateTextView(type);
        StringBuilder contentBuilder = new StringBuilder();

        contentBuilder.append("<").append(type);
        for (Attribute attribute : item.getAttributes()) {
            contentBuilder.append(" ")
                          .append(attribute.getKey())
                          .append("=")
                          .append("\"")
                          .append(attribute.getValue())
                          .append("\"");
        }
        contentBuilder.append(">");


        if (text != null) {
            contentBuilder.append(text.trim());
        }

        contentBuilder.append("</").append(type).append(">");


        Spanned content = Html.fromHtml(contentBuilder.toString(), new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                final double scale = 1;
                try {
                    Bitmap bmp = Ion.with(getContext()).load(source).asBitmap().get();
                    drawable = new BitmapDrawable(getResources(), bmp);
                    drawable.setBounds(0, 0, (int) (bmp.getWidth() * scale), (int) (bmp.getHeight() * scale));
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("ION", "" + e);
                }

                return drawable;
            }
        }, new HtmlTagHandler());

        textView.setText(setLinksEnabled(trimTrailingWhitespace(content)));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        addView(textView);
    }

    private TextView inflateTextView(String type) {
        TextView view = (TextView) mLayoutInflater.inflate(R.layout.simple_textview, null);
        view.setPadding(0, 0, 0, 0);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);

        int testSizeRes = R.dimen.p_text_size;

        switch (type.toLowerCase()) {
            case "p":
                break;

            case "img":
                view.setGravity(Gravity.CENTER);
                break;

            case "ul":
                layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.ul_margin),
                                        0,
                                        0,
                                        0);

                break;

            case "h1":
                layoutParams.setMargins(getDimensionPixel(R.dimen.h1_margin_left),
                                        getChildCount() > 0 ? getDimensionPixel(R.dimen.h1_margin_top) : 0,
                                        getDimensionPixel(R.dimen.h1_margin_right),
                                        getDimensionPixel(R.dimen.h1_margin_bottom));
                view.setTextColor(getResources().getColor(R.color.xebia));
                testSizeRes = R.dimen.h1_text_size;
                break;

            case "h2":
                layoutParams.setMargins(getDimensionPixel(R.dimen.h2_margin_left),
                                        getChildCount() > 0 ? getDimensionPixel(R.dimen.h2_margin_top) : 0,
                                        getDimensionPixel(R.dimen.h2_margin_right),
                                        getDimensionPixel(R.dimen.h2_margin_bottom));
                view.setTextColor(getResources().getColor(R.color.xebia));
                testSizeRes = R.dimen.h2_text_size;
                break;

            case "h3":
                layoutParams.setMargins(getDimensionPixel(R.dimen.h3_margin_left),
                                        getChildCount() > 0 ? getDimensionPixel(R.dimen.h3_margin_top) : 0,
                                        getDimensionPixel(R.dimen.h3_margin_right),
                                        getDimensionPixel(R.dimen.h3_margin_bottom));
                view.setTextColor(getResources().getColor(R.color.xebia));
                testSizeRes = R.dimen.h3_text_size;
                break;

            case "h4":
                layoutParams.setMargins(getDimensionPixel(R.dimen.h4_margin_left),
                                        getChildCount() > 0 ? getDimensionPixel(R.dimen.h4_margin_top) : 0,
                                        getDimensionPixel(R.dimen.h4_margin_right),
                                        getDimensionPixel(R.dimen.h4_margin_bottom));
                view.setTextColor(getResources().getColor(R.color.xebia));
                testSizeRes = R.dimen.h4_text_size;
                break;

        }

        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.p_text_size));
        view.setLayoutParams(layoutParams);
        return view;
    }

    private int getDimensionPixel(final int dimenRes) {
        return getResources().getDimensionPixelSize(dimenRes);
    }

    private CharSequence trimTrailingWhitespace(CharSequence source) {

        if (source == null) {
            return "";
        }

        int i = source.length();

        // loop back to the first non-whitespace character
        while (--i >= 0 && Character.isWhitespace(source.charAt(i))) {
        }

        return source.subSequence(0, i + 1);
    }

    private CharSequence setLinksEnabled(CharSequence cs) {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(cs);
        URLSpan[] urls = strBuilder.getSpans(0, cs.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }

        ImageSpan[] images = strBuilder.getSpans(0, cs.length(), ImageSpan.class);
        for (ImageSpan span : images) {
            makeImageZoomable(strBuilder, span);
        }
        return strBuilder;

    }


    private void makeImageZoomable(SpannableStringBuilder strBuilder, final ImageSpan span) {

    }

    private void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);

        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(span.getURL()));
                getContext().startActivity(intent);
            }
        };

        strBuilder.removeSpan(span);
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.xebia)), start, end, flags);
    }
}