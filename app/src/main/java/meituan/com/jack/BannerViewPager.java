package meituan.com.jack;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jack on 16-8-20.
 */
public class BannerViewPager extends ViewPager {
    private static final String TAG = "BannerViewPager";

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
//            Log.w(TAG, "onMeasure,child is " + child);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        height = height * 2;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.i(TAG, "onTouchEvent " + "getCurrentItem() is " + getCurrentItem());

//        PagerAdapter adapter = getAdapter();
//        Log.i(TAG, "onTouchEvent " + "adapter.getCount() is " + adapter.getCount());
//        if (getCurrentItem() == adapter.getCount() - 1){
//            return true;
//        }

        return super.onTouchEvent(ev);
    }
}
