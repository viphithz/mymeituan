package meituan.com.jack;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by jack on 16-8-19.
 */
public class BottomView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "BottomView";
    private LinearLayout mBottomHomeContainer = null;
    private LinearLayout mBottomBusinessmanContainer = null;
    private ImageView mBottomHomeImage = null;
    private HomeActivity mActivity = null;


    public BottomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.bottom_view, this);
        mBottomHomeContainer = (LinearLayout) findViewById(R.id.id_bottom_home_container);
        mBottomHomeContainer.setOnClickListener(this);

//        mBottomHomeImage = (ImageView) findViewById(R.id.id_home_image);
//        mBottomHomeImage.setOnClickListener(this);

        mBottomBusinessmanContainer = (LinearLayout) findViewById(R.id.id_bottom_businessman_container);
        mBottomBusinessmanContainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_bottom_home_container:
                Log.i(TAG, "onClick home");
                mBottomHomeContainer.setSelected(true);
                mBottomBusinessmanContainer.setSelected(false);

                if (mActivity != null){
                    mActivity.setCurrentPage(0);
                }
                break;
            case R.id.id_bottom_businessman_container:
                Log.i(TAG, "onClick businessman");
                mBottomBusinessmanContainer.setSelected(true);
                mBottomHomeContainer.setSelected(false);

                if (mActivity != null){
                    mActivity.setCurrentPage(1);
                }
                break;
            case R.id.id_home_image:
                Log.i(TAG, "onClick home image");
                mBottomHomeImage.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void setCurrentItem(int position) {
        switch (position){
            case 0:
                mBottomHomeContainer.setSelected(true);
                mBottomBusinessmanContainer.setSelected(false);
                break;
            case 1:
                mBottomBusinessmanContainer.setSelected(true);
                mBottomHomeContainer.setSelected(false);
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    public void setActivity(HomeActivity activity){
        mActivity = activity;
    }
}
