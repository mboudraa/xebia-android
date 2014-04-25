package fr.xebia.app;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.greenrobot.event.EventBus;
import fr.xebia.app.event.NetworkErrorEvent;
import org.androidannotations.annotations.EActivity;

@EActivity
public class BaseActivity extends FragmentActivity {

    protected EventBus mEventBus = EventBus.getDefault();

    int mActionBarHeight;

    @Override
    protected void onResume() {
        super.onResume();
        mEventBus.register(this);
    }

    @Override
    protected void onPause() {
        mEventBus.unregister(this);
        super.onPause();
    }


    public void onEventMainThread(NetworkErrorEvent event) {
        Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_LONG).show();
    }


//    protected TextView getActionBarTitleView() {
//        int id = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
//        return (TextView) findViewById(id);
//    }


    protected ImageView getActionBarIconView() {
        return (ImageView) findViewById(android.R.id.home);
    }

    protected int getActionbarHeight() {

        if (mActionBarHeight == 0) {
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                mActionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            }
        }

        return mActionBarHeight;
    }
//
//    protected void setTitleAlpha(float alpha) {
////        mAlphaForegroundColorSpan.setAlpha(alpha);
////        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0, mSpannableString.length(),
////                                 Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////        getActionBar().setTitle(mSpannableString);
//        getActionBarTitleView().setAlpha(alpha);
//    }

}
