package meituan.com.jack;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "HomeActivity";
    private ViewPager mViewPager = null;
    private BottomView mBottomView = null;
    private long mPreviousBackTime = 0;
    private Toast mQuitToast = null;


    FragmentPagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new BusinessmanFragment();
                    break;
                default:
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = (ViewPager) findViewById(R.id.id_home_viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);

        mBottomView = (BottomView) findViewById(R.id.id_bottom_view);
        mBottomView.setCurrentItem(0);
        mBottomView.setActivity(this);
    }

    @Override
    public void onBackPressed() {
        long time = SystemClock.elapsedRealtime();
        if (time - mPreviousBackTime < 1000){
            mQuitToast.cancel();
            finish();
        }else {
            mPreviousBackTime = time;
            mQuitToast = Toast.makeText(this, R.string.quit_tip, Toast.LENGTH_SHORT);
            mQuitToast.show();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.i(TAG, "onPageScrolled " + position + ", " + positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected " + position);
        mBottomView.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Log.i(TAG, "onPageScrollStateChanged " + state);
    }

    public void setCurrentPage(int position) {
        int count = mViewPager.getAdapter().getCount();
        if (position < 0 || position >= count){
            Log.e(TAG, "setCurrentPage() position " + position + " is out of range");
            return;
        }

        mViewPager.setCurrentItem(position);
        return;
    }
}
